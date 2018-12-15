package io.hassaan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import io.hassaan.configs.ConfigurationManager;

@SpringBootApplication
public class CourseApiDataApplication {

	

	public static void main(String[] args) {
		SpringApplication.run(CourseApiDataApplication.class, args);
	}

	@Configuration
	@EnableWebMvc
	public class WebConfig implements WebMvcConfigurer {

		@Override
		public void addCorsMappings(CorsRegistry registry) {

			ConfigurationManager instance = ConfigurationManager.Instance();
			
			CorsRegistration r = registry.addMapping("/**");

			for (String domain : instance.CorsDomains) {
				r.allowedOrigins(domain);
			}

			for (String method : instance.CorsMethods) {
				r.allowedMethods(method);
			}

			for (String header : instance.CorsHeaders) {
				r.allowedHeaders(header);
			}

			for (String exposedHeader : instance.CorsExposedHeaders) {
				r.exposedHeaders(exposedHeader);
			}

			r.allowCredentials(instance.CorsAllowCredentials);
			r.maxAge(instance.CorsMaxAge);
			
			
/*			registry.addMapping("/info/**")
		   	  .allowedOrigins("http://localhost:8585", "http://localhost:8787")
			  .allowedMethods("POST", "GET",  "PUT", "OPTIONS", "DELETE")
			  .allowedHeaders("X-Auth-Token", "Content-Type")
			  .exposedHeaders("custom-header1", "custom-header2")
			  .allowCredentials(false)
			  .maxAge(4800);*/
			
		}
	}

}
