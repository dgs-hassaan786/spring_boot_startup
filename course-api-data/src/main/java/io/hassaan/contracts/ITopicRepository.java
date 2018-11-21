package io.hassaan.contracts;

import org.springframework.data.repository.CrudRepository;

import io.hassaan.topic.Topic;

public interface ITopicRepository extends CrudRepository<Topic,String> {

	//getAllTopics()
	//getTopic(String id)
	//updateTopic(Topic t)
	//removeTopic(String id)
	
	//List<Topic> getAllTopics();
	//Topic getTopic(String id);
	//boolean updateTopic(Topic t);
	//boolean removeTopic(String id);
	
	
}
