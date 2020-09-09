
INSERT INTO STUDENT (JMBAG,name, lastName, dateOfBirth,numberOfEcts) VALUES
  ('0123456789','Nikola', 'America', '1992-11-11',165),
  ('9876543210','Igor', 'Vori', '2000-11-11',60),
  ('0000000000','Marko', 'Markic', '1990-11-11',186);

INSERT INTO COURSE (name, numberofects) VALUES
  ('Web aplikacije u Javi',6),
  ('Završni rad',8),
  ('Uvod u Unix',4);

INSERT INTO STUDENT_COURSE (students_JMBAG,courses_id) VALUES
  ('0000000000',1),
  ('9876543210',2),
  ('0123456789',3);

insert into USER (id, username, password, first_name, last_name)
    values (1, 'admin', '$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy', 'Admin', 'Admin');
insert into USER (id, username, password, first_name, last_name)
    values (2, 'user', '$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy', 'user', 'user');
insert into USER (id, username, password, first_name, last_name)
    values (3, 'creator', '$2a$10$TYExkmI7uVXXVadrdTTa0OQTOorVV32jTjK.Py2BPQjEojbAx96yy', 'creator', 'creator');
insert into authority (id, name)
    values (1, 'ROLE_ADMIN');
insert into authority (id, name)
    values (2, 'ROLE_USER');
insert into authority (id, name)
    values (3, 'ROLE_CREATOR');
insert into user_authority (user_id, authority_id)
    values (1, 1);
insert into user_authority (user_id, authority_id)
    values (2, 2);
insert into user_authority (user_id, authority_id)
    values (3, 3);