package hr.tvz.soko.studapp.student;

import java.util.Optional;

public interface UserService {

    Optional<UserDTO> findByUsername(String username);
}
