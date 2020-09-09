package hr.tvz.soko.studapp.student;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="STUDENT")
public class Student implements Serializable {

    private static final long serialVersionUID = 3524778129541351542L;

    @Column(name="name")
    private String name;

    @Column(name="lastName")
    private String lastname;

    @Column(name="dateOfBirth")
    private LocalDate dateOfBirth;

    @Id
    @Column(name="JMBAG")
    private String JMBAG;

    @Column(name="numberOfEcts")
    private Integer numberOfECTS;

    @ManyToMany(targetEntity = Course.class)
    @JoinTable(
            name = "STUDENT_COURSE",
            joinColumns = { @JoinColumn(name = "students_JMBAG") },
            inverseJoinColumns = { @JoinColumn(name = "courses_id") }
    )
    private List<Course> courses;

    public Student(String name, String lastname, LocalDate dateOfBirth, String JMBAG, Integer numberOfECTS) {
        this.name = name;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.JMBAG = JMBAG;
        this.numberOfECTS = numberOfECTS;
}
}
