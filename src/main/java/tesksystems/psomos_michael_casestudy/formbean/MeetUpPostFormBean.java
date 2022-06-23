package tesksystems.psomos_michael_casestudy.formbean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@ToString
@Getter
@Setter
public class MeetUpPostFormBean {

    private Integer id;

    private Date createAt;

    @NotBlank(message = "Meetup Post Message Needed")
    @Lob
    private String meetupMessage;

    @NotBlank(message = "Meetup Location Message Needed")
    private String location;

    private Integer userId;

    private String meetupDate;

    private String meetupTime;

}