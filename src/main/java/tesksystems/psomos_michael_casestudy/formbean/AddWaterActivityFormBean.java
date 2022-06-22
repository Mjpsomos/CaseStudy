package tesksystems.psomos_michael_casestudy.formbean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
public class AddWaterActivityFormBean {

    private Integer id;

    @NotBlank(message = "You must name your water activity")
    private String waterActivity;

    private Integer userId;

    private String image;

    private String description;

}