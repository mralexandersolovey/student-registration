# student registration documentation
Student registration - Spring boot application

1. Import as a Maven project.
2. Run RestapiApplication.java to start the Spring Boot application.
3. Navigate to Swagger API docuentation at: http://localhost:8080/swagger-ui.html
4. List students: http://localhost:8080/api/students
5. List courses: http://localhost:8080/api/courses
6. Create a new student along with his/her courses by making a POST request to http://localhost:8080/api/students with the following request body:
{
   "name": "John Smith",
  "newCourseIds": [
    "<courseID>"
  ]
}
7. Delete a student along with the course registrations by making this DELETE request: http://localhost:8080/api/students/<studentId> 
4. List students registered for a course named "Test course" : http://localhost:8080/api/courses/Test%20course/students

