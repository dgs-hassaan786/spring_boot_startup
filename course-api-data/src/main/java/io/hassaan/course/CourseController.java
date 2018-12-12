package io.hassaan.course;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.hassaan.topic.Topic;
import io.hassaan.vm.RestMessage;
import io.hassaan.vm.StatusCodeEnum;

@RestController
public class CourseController {

	private RestMessage message;

	@Autowired
	private CourseService courseService;

	@RequestMapping(method = RequestMethod.GET, value = "/topics/{topicId}/courses")
	public RestMessage getAllCourses(@PathVariable String topicId) {
		try {

			List<Course> course = courseService.getAllCourses(topicId);
			message = new RestMessage(course, StatusCodeEnum.OK);
			return message;

		} catch (Exception e) {

			message = new RestMessage(e.getMessage(), StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);

			return message;
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/topics/{topicId}/courses/{id}")
	public RestMessage getAllCourse(@PathVariable String id) {
		try {

			Course course = courseService.getCourse(id);
			if (course == null) {
				message = new RestMessage(id, StatusCodeEnum.NOT_FOUND);
			} else {
				message = new RestMessage(course, StatusCodeEnum.OK);
			}
			return message;

		} catch (Exception e) {

			message = new RestMessage(e.getMessage(), StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);

			return message;
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/topics/{topicId}/courses")
	public RestMessage addCourse(@RequestBody Course course, @PathVariable String topicId) {
		try {

			Course t = courseService.getCourse(course.getId());
			if (t == null) {
				course.setTopic(new Topic(topicId, "", ""));
				courseService.addCourse(course);
				message = new RestMessage(true, StatusCodeEnum.OK);
			} else {
				message = new RestMessage("Already exists", StatusCodeEnum.BAD_REQUEST);
			}
			return message;

		} catch (Exception e) {
			message = new RestMessage(e.getMessage(), StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);
			return message;
		}

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/topics/{topicId}/courses/{id}")
	public RestMessage updateTopic(@RequestBody Course course, @PathVariable String topicId, @PathVariable String id) {
		try {
			course.setTopic(new Topic(topicId, "", ""));
			courseService.updateCourse(course);
			message = new RestMessage(true, StatusCodeEnum.OK);
			return message;

		} catch (Exception e) {
			message = new RestMessage(e.getMessage(), StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);
			return message;
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/topics/{topicId}/courses/{id}")
	public RestMessage deleteTopic(@PathVariable String id) {
		try {
			boolean result = courseService.removeCourse(id);
			message = new RestMessage(result, result ? StatusCodeEnum.OK : StatusCodeEnum.NOT_FOUND);
			return message;
		} catch (Exception e) {
			message = new RestMessage(e.getMessage(), StatusCodeEnum.INTERNAL_SERVER_ERROR);
			message.setException(e);
			return message;
		}
	}
}
