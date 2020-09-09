package hr.tvz.soko.studapp.student;

import lombok.Data;

@Data
public class CourseDTO {
    private String name;
    private Integer numberOfECTS;

    public CourseDTO(String name, Integer numberOfECTS) {
        this.name=name;
        this.numberOfECTS=numberOfECTS;
    }
}
