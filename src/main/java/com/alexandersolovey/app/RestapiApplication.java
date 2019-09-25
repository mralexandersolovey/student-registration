package com.alexandersolovey.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.alexandersolovey.restapi.model.Course;
import com.alexandersolovey.restapi.model.Student;
import com.alexandersolovey.restapi.model.StudentCourseRegistration;
import com.alexandersolovey.restapi.repository.CourseRepository;
import com.alexandersolovey.restapi.repository.StudentCourseRegistrationRepository;
import com.alexandersolovey.restapi.repository.StudentRepository;

@SpringBootApplication
@ComponentScan({"com.alexandersolovey"})
@EnableJpaRepositories("com.alexandersolovey.restapi.repository")
@EntityScan("com.alexandersolovey.restapi.model")
public class RestapiApplication implements CommandLineRunner {

	private StudentRepository studentRepository;

	@Autowired
	public void studentRepository(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	private CourseRepository courseRepository;

	@Autowired
	public void courseRepository(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	private StudentCourseRegistrationRepository studentCourseRegistrationRepository;

	@Autowired
	public void studentCourseRegistrationRepository(StudentCourseRegistrationRepository studentCourseRegistrationRepository) {
		this.studentCourseRegistrationRepository = studentCourseRegistrationRepository;
	}		

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		Student student = new Student();
		student.setName("Test Student");
		studentRepository.save(student);
		
		Course course = new Course();
		course.setName("Test course");
		courseRepository.save(course);
		
		StudentCourseRegistration scr = new StudentCourseRegistration();
		scr.setStudent(student);
		scr.setCourse(course);
		studentCourseRegistrationRepository.save(scr);
	}
}
