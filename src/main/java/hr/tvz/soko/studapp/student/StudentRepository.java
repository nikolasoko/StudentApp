package hr.tvz.soko.studapp.student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> findAll();

    Optional<Student> findStudentByJMBAG(String JMBAG);

    List<Student> findStudentByName(String name);

    Optional<Student> save(StudentCommand command);

    void deleteByJmbag(String JMBAG);

    Optional<Student> update(String jmbag, StudentCommandUpdate updateStudentCommand);

}
