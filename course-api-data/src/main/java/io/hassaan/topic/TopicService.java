package io.hassaan.topic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.hassaan.contracts.ITopicRepository;

@Service
public class TopicService {

	@Autowired
	private ITopicRepository topicRepository;
	
	private List<Topic> topics;
	
	public TopicService() {
		initializeTopics();
	}
	
	private void initializeTopics() {
		
//		topics = new ArrayList<>(Arrays.asList(				
//				new Topic("spring", "Spring Framework", "Spring Framework Description"),
//				new Topic("java", "Core Java", "Core Java Description"),
//				new Topic("javascript", "JavaScript", "JavaScript Description")				
//				));
		
		topics = new ArrayList<Topic>();
		topics.add(new Topic("spring", "Spring Framework", "Spring Framework Description"));
		topics.add(new Topic("java", "Core Java", "Core Java Description"));
		topics.add(new Topic("javascript", "JavaScript", "JavaScript Description"));
	}
	
	public List<Topic> getAllTopics(){
		
		List<Topic> t = new ArrayList<Topic>();
		topicRepository.findAll().forEach(t::add);		
		return t;
	}
	
	public Topic getTopic(String id) {		
	 
	 Topic t = topicRepository.findById(id).orElse(null);
	 return t;
		
		//Topic topic = topics.stream().filter(t-> t.getId().equals(id)).findFirst().orElse(null);
	 //return topic;
	}

	public void addTopic(Topic topic) {						
		
		topicRepository.save(topic);
		
		//topics.add(topic);		
	}

	public void updateTopic(String id, Topic topic) {
				
		
		topicRepository.save(topic);
		
		/*int i = 0;
		for (Topic t : topics) {
			if(t.getId().equals(id)) {
				topics.set(i,topic);				
				break;
			}
			i++;
		}	*/	
	}

	public boolean removeTopic(String id) {		
		
		topicRepository.deleteById(id);
		return true;
		
		//return topics.removeIf(t->t.getId().equals(id));		
	}
	
}
