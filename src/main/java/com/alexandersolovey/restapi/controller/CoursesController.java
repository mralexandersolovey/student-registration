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
import com.alexandersolovey.restapi.service.StudentsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api/courses")
@Api(value = "CoursesControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class CoursesController {

    private StudentsService studentsService;

    private Logger LOG = LoggerFactory.getLogger(CoursesController.class);

    @Autowired
    public void setStudentsService(StudentsService studentsService) {
        this.studentsService = studentsService;
    }
    
    @RequestMapping(path = "", method = RequestMethod.GET)
    @ApiOperation("Lists all courses")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
    public Iterable<Course> listCourses() {
        return studentsService.listCourses();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ApiOperation("Gets the course with specific id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Student.class)})
    public Course getCourse(@PathVariable(name = "id") String id) {
        return studentsService.findCourse(id);
    }
    
    @RequestMapping(path = "/{name}/students", method = RequestMethod.GET)
    @ApiOperation("Gets the students registered with the course with specific name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Student.class)})
    public Iterable<Student> getCourseStudents(@PathVariable(name = "name") String name) {
        return studentsService.listCourseStudents(name);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Course saveCourse(@RequestBody Course courseToSave) {
        return studentsService.saveCourse(courseToSave);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Course updateCourse(@RequestBody Course courseToUpdate, @PathVariable(name = "id") String id) {
        return studentsService.updateCourse(courseToUpdate, id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteCourse(@PathVariable(name = "id") String id) {
        studentsService.deleteCourse(id);
    }
}
