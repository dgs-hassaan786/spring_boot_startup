package io.hassaan.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.hassaan.contracts.ICourseRepository;

@Service
public class CourseService {

	@Autowired
	private ICourseRepository courseRepository;
		
	public CourseService() {
	
	}
		
	public List<Course> getAllCourses(String topicId){		
		List<Course> c = new ArrayList<Course>();
		courseRepository.findByTopicId(topicId).forEach(c::add);		
		return c;
	}
	
	public Course getCourse(String id) {			 
	 Course c = courseRepository.findById(id).orElse(null);
	 return c;
	}

	public void addCourse(Course course) {								
		courseRepository.save(course);		
	}

	public void updateCourse(Course course) {				
		courseRepository.save(course);	
	}

	public boolean removeCourse(String id) {				
		courseRepository.deleteById(id);
		return true;				
	}
	
}
