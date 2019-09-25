package com.alexandersolovey.restapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alexandersolovey.restapi.model.Course;
import com.alexandersolovey.restapi.model.Student;
import com.alexandersolovey.restapi.model.StudentCourseRegistration;
import com.alexandersolovey.restapi.service.StudentsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api/student/courses")
@Api(value = "StudentCourseRegistratonsControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentCourseRegistratonsController {

    private StudentsService studentsService;

    private Logger LOG = LoggerFactory.getLogger(StudentCourseRegistratonsController.class);

    @Autowired
    public void setStudentsService(StudentsService studentsService) {
        this.studentsService = studentsService;
    }
    
    @RequestMapping(path = "", method = RequestMethod.GET)
    @ApiOperation("Lists all student course registrations")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    public Iterable<StudentCourseRegistration> listCourses() {
        return studentsService.listStudentCourseRegistrations();
    }
    
    

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ApiOperation("Gets the student course registration with specific id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Student.class)})
    public StudentCourseRegistration getStudentCourseRegistration(@PathVariable(name = "id") String id) {
        return studentsService.findStudentCourseRegistration(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentCourseRegistration saveStudent(@RequestBody StudentCourseRegistration studentCourseRegistrationToSave) {
        return studentsService.saveStudentCourseRegistration(studentCourseRegistrationToSave);
    }
/*
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Student updateStudent(@RequestBody Student studentToUpdate, @PathVariable(name = "id") String id) {
        return studentsService.updateStudent(studentToUpdate, id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable(name = "id") String id) {
        studentsService.deleteStudent(id);
    }
*/
}
