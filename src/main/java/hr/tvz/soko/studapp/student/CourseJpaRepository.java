package hr.tvz.soko.studapp.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseJpaRepository extends JpaRepository<Course,Integer> {

    List<Course> findCoursesByStudents_JMBAG(String student);
}
