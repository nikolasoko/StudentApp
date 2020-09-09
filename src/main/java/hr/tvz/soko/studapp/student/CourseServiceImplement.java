package hr.tvz.soko.studapp.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImplement implements CourseService{

    private final CourseJpaRepository courseJpaRepository;
    private final JdbcStudentRepository jdbcStudentRepository;

    public CourseServiceImplement(CourseJpaRepository courseJpaRepository, JdbcStudentRepository jdbcStudentRepository) {
        this.courseJpaRepository = courseJpaRepository;
        this.jdbcStudentRepository=jdbcStudentRepository;
    }

    @Override
    public List<CourseDTO> findAll() {
        return courseJpaRepository.findAll().stream().map(this::mapCourseToDTO).collect(Collectors.toList());
    }


    public List<CourseDTO> findCoursesByStudents_JMBAG(String JMBAG) {

        Optional<Student> student = jdbcStudentRepository.findStudentByJMBAG(JMBAG);
        return courseJpaRepository.findCoursesByStudents_JMBAG(student.orElse(new Student()).getJMBAG()).stream().map(this::mapCourseToDTO).collect(Collectors.toList());
    }

    private CourseDTO mapCourseToDTO(Course course) {
        return new CourseDTO(course.getName(), course.getNumberofects());
    }
}
