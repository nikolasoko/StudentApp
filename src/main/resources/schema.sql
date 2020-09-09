DROP TABLE IF EXISTS STUDENT;
DROP TABLE IF EXISTS COURSE;
DROP TABLE IF EXISTS STUDENT_COURSE;

CREATE TABLE STUDENT (
  JMBAG VARCHAR(10) PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  lastName VARCHAR(250) NOT NULL,
  dateOfBirth DATE not null,
  numberOfEcts int not null
);

CREATE TABLE COURSE (
  id int auto_increment PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  numberofects int not null
);

CREATE TABLE STUDENT_COURSE(
  students_JMBAG VARCHAR(10),
  courses_id int,
  PRIMARY KEY (students_JMBAG,courses_id),
  CONSTRAINT fk_student
  FOREIGN KEY (students_JMBAG) REFERENCES STUDENT(JMBAG) ON DELETE CASCADE,
  CONSTRAINT FK_course
  FOREIGN KEY (courses_id) REFERENCES COURSE(id) ON DELETE CASCADE
);
create table if not exists USER (
    id identity,
    username varchar(100) not null,
    password varchar(250) not null,
    first_name varchar(250) not null,
    last_name varchar(250) not null
);
create table if not exists AUTHORITY (
    id identity,
    name varchar(100) not null
);
create table if not exists USER_AUTHORITY (
    user_id bigint not null,
    authority_id bigint not null,
    constraint fk_user foreign key (user_id) references user(id),
    constraint fk_authority foreign key (authority_id) references authority(id)
);

