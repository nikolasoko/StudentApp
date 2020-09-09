package hr.tvz.soko.studapp.student;

import lombok.*;

@Data
public class StudentDTO {
    private String firstName;
    private String lastName;
    private String JMBAG;
    private Integer numberOfECTS;
    private boolean tuitionShouldBePaid;

    public StudentDTO(String firstName, String lastName,String JMBAG, Integer numberOfECTS, boolean tuitionShouldBePaid) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.JMBAG = JMBAG;
        this.numberOfECTS = numberOfECTS;
        this.tuitionShouldBePaid = tuitionShouldBePaid;
    }


}
