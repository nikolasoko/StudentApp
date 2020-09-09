package hr.tvz.soko.studapp.student;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private Set<String> Authorities;
}
