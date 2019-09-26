package com.alexandersolovey.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alexandersolovey.restapi.model.StudentCourseRegistration;

@Repository
public interface StudentCourseRegistrationRepository extends JpaRepository<StudentCourseRegistration, String> {

	@Query("SELECT scr FROM StudentCourseRegistration scr WHERE scr.student.id = :studentId")
	public Iterable<StudentCourseRegistration> findAllForStudent(@Param("studentId") String studentId);
	
	@Modifying
	@Query("DELETE FROM StudentCourseRegistration scr WHERE scr.student.id = :studentId")
	public void deleteStudentRegistrations(@Param("studentId") String studentId);
}
