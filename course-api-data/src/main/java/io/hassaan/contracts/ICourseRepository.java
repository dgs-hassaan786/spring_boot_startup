package io.hassaan.contracts;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.hassaan.course.Course;

public interface ICourseRepository extends CrudRepository<Course,String> {

	public List<Course> findByTopicId(String topicId);
	
}
