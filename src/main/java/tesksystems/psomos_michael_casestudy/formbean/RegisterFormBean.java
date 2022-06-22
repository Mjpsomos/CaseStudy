package tesksystems.psomos_michael_casestudy.formbean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import tesksystems.psomos_michael_casestudy.validation.EmailUnique;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ToString
@Getter
@Setter
public class RegisterFormBean {

    private Integer id;

    @EmailUnique(message = "Email is already in use")
    @NotBlank(message = "You must provide an email")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message="Email format invalid")
    private String email;

    @NotBlank(message = "First Name is required")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    private String lastName;

    @Length(min = 3, max = 15, message = "Password must be between 3 and 15 characters")
    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Confirm Password is required")
    private String confirmPassword;

    @NotBlank(message = "Town and State is required")
    private String townState;

    private String profileDescription;

    private String favoriteMeetUps;

    private String profileImg;

    @AssertTrue(message = "**** Must read terms of use")

    @AssertTrue(message="**** Must read privacy policy")
    private boolean checkboxPrivacy;

}
