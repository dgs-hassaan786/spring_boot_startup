package hassaan.khan.quickstart.topic;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

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
		return topics;
	}
	
	public Topic getTopic(String id) {
	 Topic topic = topics.stream().filter(t-> t.getId().equals(id)).findFirst().orElse(null);
	 return topic;
	}

	public void addTopic(Topic topic) {						
		topics.add(topic);		
	}

	public void updateTopic(String id, Topic topic) {
		int i = 0;
		for (Topic t : topics) {
			if(t.getId().equals(id)) {
				topics.set(i,topic);				
				break;
			}
			i++;
		}		
	}

	public boolean removeTopic(String id) {		
		return topics.removeIf(t->t.getId().equals(id));		
	}
	
}
