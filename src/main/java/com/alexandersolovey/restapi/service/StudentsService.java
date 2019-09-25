package com.alexandersolovey.restapi.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexandersolovey.restapi.model.Course;
import com.alexandersolovey.restapi.model.Student;
import com.alexandersolovey.restapi.model.StudentCourseRegistration;
import com.alexandersolovey.restapi.repository.CourseRepository;
import com.alexandersolovey.restapi.repository.StudentCourseRegistrationRepository;
import com.alexandersolovey.restapi.repository.StudentRepository;

@Service
public class StudentsService {

    private Logger LOG = LoggerFactory.getLogger(StudentsService.class);

    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
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

    public Student findStudent(String id) {
        LOG.info("Getting the student with given id:" + id);
        return studentRepository.findOne(id);
    }

    @Transactional
    public Student saveStudent(Student student) {
    	Student savedStudent = studentRepository.save(student);
    	if (student.getNewCourseIds() != null) {
	    	for (String courseId : student.getNewCourseIds()) {
	    		Course course = findCourse(courseId);
	    		StudentCourseRegistration scr = new StudentCourseRegistration();
	    		scr.setCourse(course);
	    		scr.setStudent(student);
	    		scr = saveStudentCourseRegistration(scr);
	    	}
    	}
        return savedStudent;
    }
    
    @Transactional
    public void deleteStudent(String id) {
    	for(StudentCourseRegistration scr : listStudentCourses(id)) {
    		deleteStudentCourse(scr);
    	}
    	studentRepository.delete(id);
    }    

    public Student updateStudent(Student studentToUpdate, String id) {
        Student foundStudent = studentRepository.findOne(id);
        if (foundStudent != null) {
        	foundStudent.setName(studentToUpdate.getName());
        	return studentRepository.save(foundStudent);
        }
        return studentToUpdate;
    }

	public Iterable<Student> listStudents() {
		return studentRepository.findAll();
	}
	
	public Iterable<Course> listCourses() {
		return courseRepository.findAll();
	}
	
	public Iterable<StudentCourseRegistration> listStudentCourseRegistrations() {
		return studentCourseRegistrationRepository.findAll();
	}
	
	public StudentCourseRegistration findStudentCourseRegistration(String id) {
		return studentCourseRegistrationRepository.findOne(id);
	}

	public Course findCourse(String courseId) {
		return courseRepository.findOne(courseId);
	}

	public StudentCourseRegistration saveStudentCourseRegistration(StudentCourseRegistration scr) {
		return studentCourseRegistrationRepository.save(scr);
	}

	public Course saveCourse(Course courseToSave) {
		return courseRepository.save(courseToSave);
	}

	public Course updateCourse(Course courseToUpdate, String id) {
		Course foundCourse = courseRepository.getOne(id);
		if (foundCourse != null) {
			foundCourse.setName(courseToUpdate.getName());
			return courseRepository.save(foundCourse);
		}
		return courseToUpdate;
	}
	
	public Iterable<StudentCourseRegistration> listStudentCourses(String studentId) {
		return studentCourseRegistrationRepository.findAllForStudent(studentId);
	}

	public void deleteCourse(String id) {
		courseRepository.delete(id);
	}

	public void deleteStudentCourse(StudentCourseRegistration scr) {
		studentCourseRegistrationRepository.delete(scr.getId());
	}

	public Iterable<Student> listCourseStudents(String name) {
		return studentRepository.listCourseStudents(name);
	}	
}
