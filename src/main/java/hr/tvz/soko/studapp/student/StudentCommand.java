package hr.tvz.soko.studapp.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCommand {

    @JsonProperty("firstName")
    @NotBlank(message = "First name must not be empty")
    private String firstName;

    @JsonProperty("lastName")
    @NotBlank(message = "Last name must not be empty")
    private String lastName;

    @JsonProperty("jmbag")
    @NotBlank(message = "JMBAG must not be empty")
    @Pattern(message = "JMBAG must have 10 digits", regexp="[\\d]{10}")
    private String jmbag;

    @JsonProperty("dateOfBirth")
    @JsonFormat(pattern = "dd.MM.yyyy.")
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @NotNull(message = "Date of birth must be entered")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @JsonProperty("numberOfECTS")
    @NotNull(message = "Number of ECTS points must be entered")
    @PositiveOrZero(message = "Number of ECTS must be entered as a positive integer")
    @Max(message = "Number of ECTS can not be higher than 480", value = 480)
    private Integer numberOfECTS;

    /*public StudentCommand(String firstName, String lastName, String jmbag, String dateOfBirth, Integer numberOfECTS) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbag = jmbag;
        //DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

        LocalDate praviDatum = LocalDate.parse(dateOfBirth,df);
        this.dateOfBirth = praviDatum;
        this.numberOfECTS = numberOfECTS;
    }*/
    /*public StudentCommand(String firstName, String lastName, String jmbag, LocalDate dateOfBirth, Integer numberOfECTS) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbag = jmbag;
        this.dateOfBirth = dateOfBirth;
        this.numberOfECTS = numberOfECTS;
    }
    public StudentCommand() {};*/
}
