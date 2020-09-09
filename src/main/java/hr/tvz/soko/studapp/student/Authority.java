package hr.tvz.soko.studapp.student;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name="AUTHORITY")
public class Authority {

    @Id
    @Column(name="id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(targetEntity = User.class, mappedBy = "authorities")
    private List<User> users;
}
