package hassaan.khan.quickstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//we need to tell spring this is the server application
@SpringBootApplication
public class CourseApiApp {

	public static void main(String[] args) {
		
		//This is starting the spring server 
		//we need to pass the class name and args
		SpringApplication.run(CourseApiApp.class, args);
		
		
	}

}
