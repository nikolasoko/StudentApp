package hr.tvz.soko.studapp.student;

import java.util.List;

public interface CourseService {
    List<CourseDTO> findAll();
    List<CourseDTO> findCoursesByStudents_JMBAG(String JMBAG);

}
