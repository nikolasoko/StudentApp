package hr.tvz.soko.studapp.student;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="COURSE")
public class Course implements Serializable {

    private static final long serialVersionUID = -5201859693420074521L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "numberofects")
    private Integer numberofects;

    @ManyToMany(targetEntity = Student.class, mappedBy = "courses")
    private List<Student> students;

    /*public Course(Long id, String name, Integer numberofects){
        this.id=id;
        this.name=name;
        this.numberofects=numberofects;
    }*/
}
