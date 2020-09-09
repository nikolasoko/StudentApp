package hr.tvz.soko.studapp.student;

import org.springframework.security.access.annotation.Secured;

import java.util.List;
import java.util.Optional;


public interface StudentService {
    List<StudentDTO> findAll();

    Optional<StudentDTO> findStudentByJMBAG(String JMBAG);

    List<StudentDTO> findStudentByName(String name);

    @Secured({"ROLE_CREATOR","ROLE_ADMIN"})
    Optional<StudentDTO> save(StudentCommand command);

    boolean deleteByJMBAG(String JMBAG);

    Optional<StudentDTO> update(String JMBAG, StudentCommandUpdate updateStudentCommand);
}
