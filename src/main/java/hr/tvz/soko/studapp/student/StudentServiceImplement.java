package hr.tvz.soko.studapp.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImplement implements StudentService {

    private static final int AGE_FOR_TUITION = 26;

    private final StudentRepository studentRepository;

    public StudentServiceImplement(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(this::mapStudentToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<StudentDTO> findStudentByJMBAG(String JMBAG) {
        //return studentRepository.findStudentByJMBAG(JMBAG).map(this::mapStudentToDTO).orElse(null);
        return studentRepository.findStudentByJMBAG(JMBAG).map(this::mapStudentToDTO);
    }

    @Override
    public List<StudentDTO> findStudentByName(String name) {
        return studentRepository.findStudentByName(name).stream().map(this::mapStudentToDTO).collect(Collectors.toList());
    }

    private StudentDTO mapStudentToDTO(Student student) {
        return new StudentDTO(student.getName(),student.getLastname(),student.getJMBAG(), student.getNumberOfECTS(), payTuition(student.getDateOfBirth()));
    }

    private boolean payTuition(LocalDate dateOfBirth) {
        return dateOfBirth.plusYears(AGE_FOR_TUITION).isBefore(LocalDate.now());
    }

    @Override
    public Optional<StudentDTO> save(StudentCommand command) {
        return studentRepository.save(command).map(this::mapStudentToDTO);
    }

    @Override
    public boolean deleteByJMBAG(String JMBAG) {
        if (studentRepository.findStudentByJMBAG(JMBAG).isPresent()) {
            studentRepository.deleteByJmbag(JMBAG);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<StudentDTO> update(String JMBAG, StudentCommandUpdate updateStudentCommand) {
        return studentRepository.update(JMBAG,updateStudentCommand).map(this::mapStudentToDTO);
    }


}
