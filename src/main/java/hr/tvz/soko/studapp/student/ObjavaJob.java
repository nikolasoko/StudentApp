package hr.tvz.soko.studapp.student;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class ObjavaJob extends QuartzJobBean {

    @Autowired
    private StudentService studentService;



    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<StudentDTO> studenti = studentService.findAll();
        System.out.println("Ovo su trenutno upisani studenti:");
        System.out.println("--------------------");
        for (StudentDTO trenutni:studenti) {
            System.out.println(trenutni.getJMBAG() + " - " + trenutni.getFirstName() + " " + trenutni.getLastName());
        }
        System.out.println("--------------------");

    }

}
