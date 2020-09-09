package hr.tvz.soko.studapp.student.web;

import hr.tvz.soko.studapp.student.CourseDTO;
import hr.tvz.soko.studapp.student.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.findAll();
    }

    @GetMapping("/{JMBAG}")
    public List<CourseDTO> getAllCoursesByStudent(@PathVariable final String JMBAG) {
        return courseService.findCoursesByStudents_JMBAG(JMBAG);
    }

    @GetMapping(params="jmbag")
    public List<CourseDTO> getAllCoursesByStudentJMBAG(@RequestParam final String jmbag) {
        return courseService.findCoursesByStudents_JMBAG(jmbag);
    }


}
