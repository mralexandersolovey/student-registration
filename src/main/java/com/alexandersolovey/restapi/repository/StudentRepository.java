package com.alexandersolovey.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alexandersolovey.restapi.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

	@Query("SELECT scr.student FROM StudentCourseRegistration scr WHERE scr.course.name = :name order by scr.student.name")
	Iterable<Student> listCourseStudents(@Param("name") String name);
}
