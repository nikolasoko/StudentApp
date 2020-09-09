package hr.tvz.soko.studapp.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories("hr")
@ComponentScan({"hr.tvz.soko.studapp.student", "hr.tvz.soko.studapp.student.web","hr.tvz.soko.studapp.course","hr.tvz.soko.studapp.user","hr.tvz.soko.studapp.security"})
@EntityScan("hr")
public class StudappApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudappApplication.class, args);
    }

}
