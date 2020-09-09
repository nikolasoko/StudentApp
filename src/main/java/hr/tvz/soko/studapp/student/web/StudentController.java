package hr.tvz.soko.studapp.student.web;

import hr.tvz.soko.studapp.student.StudentCommand;
import hr.tvz.soko.studapp.student.StudentCommandUpdate;
import hr.tvz.soko.studapp.student.StudentDTO;
import hr.tvz.soko.studapp.student.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }



    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {

        //return studentService.findAll();
        List<StudentDTO> studenti = studentService.findAll();
        return new ResponseEntity<>(studenti, HttpStatus.OK);


    }

    @GetMapping(params = "JMBAG")
    public ResponseEntity<StudentDTO> getAllStudentsByJMBAG(@RequestParam final String JMBAG) {
        return studentService.findStudentByJMBAG(JMBAG)
                .map(
                        studentDTO -> ResponseEntity
                                .status(HttpStatus.FOUND)
                                .body(studentDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .build()
                );
    }

    @GetMapping("/{JMBAG}")
    public ResponseEntity<StudentDTO> getAllStudentsByJMBAG2(@PathVariable final String JMBAG) {
        return studentService.findStudentByJMBAG(JMBAG)
                .map(
                        studentDTO -> ResponseEntity
                                .status(HttpStatus.OK)
                                .body(studentDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .build()
                );
    }

    @GetMapping(params = "name")
    public List<StudentDTO> getStudentsByName(@RequestParam final String name) {
        return studentService.findStudentByName(name);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> save(@Valid @RequestBody final StudentCommand command) {
        return studentService.save(command)
                .map(
                        studentDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(studentDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{JMBAG}")
    public void delete(@PathVariable String JMBAG) {
        studentService.deleteByJMBAG(JMBAG);
    }

    @PutMapping("/{JMBAG}")
    public ResponseEntity<StudentDTO> update(@PathVariable String JMBAG, @Valid @RequestBody final StudentCommandUpdate updateStudentCommand) {
        return studentService.update(JMBAG, updateStudentCommand)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }
}
