package io.hassaan.topic;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.hassaan.vm.RestMessage;
import io.hassaan.vm.StatusCodeEnum;

@RestController
public class TopicController {

	private RestMessage message;
	
	@Autowired
	private TopicService topicService;
	
	@RequestMapping(method=RequestMethod.GET, value="/topics")
	public RestMessage getAllTopics() {				
		try {
		
			List<Topic> topic = topicService.getAllTopics();		
			message = new RestMessage(topic,StatusCodeEnum.OK);		
			return message;
			
		} catch (Exception e) {
		
			message = new RestMessage(e.getMessage(),StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);
			
			return message;		 	
		}		
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/topics/{id}")
	public RestMessage getAllTopics(@PathVariable String id) {					
		try {
			
			Topic topic = topicService.getTopic(id);
			if(topic == null) {
				message = new RestMessage(id,StatusCodeEnum.NOT_FOUND);
			}else {
				message = new RestMessage(topic,StatusCodeEnum.OK);		
			}
			return message;
			
		} catch (Exception e) {
		
			message = new RestMessage(e.getMessage(),StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);
			
			return message;		 	
		}		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/topics")
	public RestMessage addTopic(@RequestBody Topic topic) {				
		try {	
			
			Topic t = topicService.getTopic(topic.getId());
			if(t == null) {				
				topicService.addTopic(topic);			
				message = new RestMessage(true,StatusCodeEnum.OK);								
			}else {
				message = new RestMessage("Already exists",StatusCodeEnum.BAD_REQUEST);								
			}
			return message;
			
		} catch (Exception e) {
			message = new RestMessage(e.getMessage(),StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);		
			return message;		 	
		}		
				
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{id}")
	public RestMessage updateTopic(@RequestBody Topic topic, @PathVariable String id) {				
		try {		
			topicService.updateTopic(id, topic);
			message = new RestMessage(true,StatusCodeEnum.OK);						
			return message;
			
		} catch (Exception e) {
			message = new RestMessage(e.getMessage(),StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);		
			return message;
		}
				
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{id}")
	public RestMessage deleteTopic(@PathVariable String id) {				
		try {
			boolean result = topicService.removeTopic(id);			
			message = new RestMessage(result,result ? StatusCodeEnum.OK : StatusCodeEnum.NOT_FOUND);		
			return message;
		} catch (Exception e) {
			message = new RestMessage(e.getMessage(),StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);		
			return message;
		}
	}
}
