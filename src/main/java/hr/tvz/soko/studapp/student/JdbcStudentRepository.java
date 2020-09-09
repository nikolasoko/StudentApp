package hr.tvz.soko.studapp.student;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
@Repository
public class JdbcStudentRepository implements StudentRepository {

    private JdbcTemplate jdbc;
    private SimpleJdbcInsert studentInserter;

    public JdbcStudentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.studentInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("STUDENT");
    }

    @Override
    public List<Student> findAll() {
        List<Student> lista= jdbc.query("select JMBAG, name, lastName, dateOfBirth, numberOfEcts from STUDENT",
                this::mapRowToStudent);
        return jdbc.query("select JMBAG, name, lastName, dateOfBirth, numberOfEcts from STUDENT",
                this::mapRowToStudent);
    }

    @Override
    public Optional<Student> findStudentByJMBAG(String JMBAG) {
        try {
            Student student = jdbc.queryForObject("select JMBAG, name, lastName, dateOfBirth, numberOfEcts from STUDENT WHERE JMBAG =" + "'" + JMBAG + "'",
                    this::mapRowToStudent);
            return Optional.of(student);
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    public List<Student> findAllInECTSRange(Integer lowerBound, Integer upperBound) {
        return jdbc.query("select JMBAG, name, lastName, dateOfBirth, numberOfEcts from STUDENT where numberOfEcts between " + lowerBound + " and " + upperBound,
                this::mapRowToStudent);
    }


    @Override
    public List<Student> findStudentByName(String name) {
        return null;
    }

    @Override
    public Optional<Student> save(StudentCommand command) {
        Map<String, Object> values = new HashMap<>();
        values.put("JMBAG", command.getJmbag());
        values.put("name", command.getFirstName());
        values.put("lastName", command.getLastName());
        values.put("dateOfBirth", command.getDateOfBirth());
        values.put("numberOfEcts", command.getNumberOfECTS());
        if (findStudentByJMBAG(command.getJmbag()).isPresent()) {
            return Optional.empty();
        } else {
            Student novi = new Student(command.getFirstName(),command.getLastName(),command.getDateOfBirth(),command.getJmbag(),command.getNumberOfECTS());
            studentInserter.execute(values);
            return Optional.of(novi);
        }
    }

    @Override
    public void deleteByJmbag(String JMBAG) {
        jdbc.update("DELETE FROM STUDENT WHERE JMBAG='" + JMBAG + "'");

        return;
    }

    @Override
    public Optional<Student> update(String jmbag, StudentCommandUpdate updateStudentCommand) {
        Student novi = new Student(updateStudentCommand.getFirstName(),updateStudentCommand.getLastName(),updateStudentCommand.getDateOfBirth(),jmbag,updateStudentCommand.getNumberOfECTS());
        jdbc.update("UPDATE STUDENT SET name='" + updateStudentCommand.getFirstName() + "',lastName='" + updateStudentCommand.getLastName() + "',dateOfBirth='" + updateStudentCommand.getDateOfBirth() + "',numberofects=" + updateStudentCommand.getNumberOfECTS() + " WHERE JMBAG='" + jmbag + "'");
        return Optional.of(novi);
    }
    private Student mapRowToStudent(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setName(rs.getString("name"));
        student.setLastname(rs.getString("lastName"));
        student.setNumberOfECTS(rs.getInt("numberOfEcts"));
        student.setJMBAG(rs.getString("JMBAG"));
        student.setDateOfBirth(rs.getDate("dateOfBirth").toLocalDate());
        return student;
    }
}
