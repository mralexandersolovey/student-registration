CREATE TABLE student (
    id varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    UNIQUE (id)
);


CREATE TABLE course (
    id varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    UNIQUE (id)
);


CREATE TABLE student_course_registration (
    student_course_registration_id varchar(255) NOT NULL,
    student_id varchar(255) NOT NULL,
    course_id varchar(255) NOT NULL,
    UNIQUE (student_course_registration_id),
    UNIQUE (student_id,course_id)
);