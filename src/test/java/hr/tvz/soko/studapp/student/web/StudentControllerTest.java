package hr.tvz.soko.studapp.student.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hr.tvz.soko.studapp.student.StudentCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    private static final String TEST_JMBAG = "6666666666";
    private static final String TEST_FIRST_NAME = "Ivan";
    private static final String TEST_LAST_NAME = "Ivic";

    @Autowired
    private MockMvc mockMvc;




    @Test
    void findAllInECTSRange() {
    }

    @Test
    void getAllStudents() throws Exception {

        this.mockMvc
                .perform(get("/student")
                        .with(user("admin")
                                .password("test")
                                .roles("ADMIN")
                        )
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0]['firstName']").isNotEmpty());
    }

    @Test
    void getAllStudentsByJMBAG() throws Exception {
        String testJmbag="0000000000";
        this.mockMvc
                .perform(get("/student" )
                        .param("JMBAG",testJmbag)
                        .with(user("admin")
                                .password("test")
                                .roles("ADMIN")
                        )
                        .with(csrf())
                )
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.jmbag").value(testJmbag));
    }

    @Test
    void getAllStudentsByJMBAG2() throws Exception {
        String testJmbag="0000000000";
        this.mockMvc
                .perform(get("/student/" + testJmbag )
                        .with(user("admin")
                                .password("test")
                                .roles("ADMIN")
                        )
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.jmbag").value(testJmbag));
    }

    @Test
    void getStudentsByName() {
    }

    @DirtiesContext
    @Test
    void save() throws Exception {
        ObjectMapper objectMapper =new ObjectMapper();

        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        String datumstr="15.12.1995.";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        LocalDate datum = LocalDate.parse(datumstr,df);
        StudentCommand studentCommand = new StudentCommand();
        studentCommand.setFirstName(TEST_FIRST_NAME);
        studentCommand.setLastName(TEST_LAST_NAME);
        studentCommand.setJmbag(TEST_JMBAG);
        studentCommand.setNumberOfECTS(44);
        studentCommand.setDateOfBirth(datum);

        this.mockMvc.perform(
                post("/student")
                        .with(user("admin")
                                .password("test")
                                .roles("ADMIN")
                        )
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentCommand))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.jmbag").value(TEST_JMBAG))
                .andExpect(jsonPath("$.firstName").value(TEST_FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(TEST_LAST_NAME));
    }


    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void getStudentWithNonExistentJMBAG() throws Exception {
        String testJmbag="9999999999";
        this.mockMvc
                .perform(get("/student" )
                        .param("JMBAG",testJmbag)
                        .with(user("admin")
                                .password("test")
                                .roles("ADMIN")
                        )
                        .with(csrf())
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void getStudentWithNonExistentJMBAG2() throws Exception {
        String testJmbag="9999999999";
        this.mockMvc
                .perform(get("/student/" + testJmbag )
                        .with(user("admin")
                                .password("test")
                                .roles("ADMIN")
                        )
                        .with(csrf())
                )
                .andExpect(status().isNotFound());
    }
}