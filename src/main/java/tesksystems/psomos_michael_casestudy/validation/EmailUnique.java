package tesksystems.psomos_michael_casestudy.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailUniqueImpl.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)

public @interface EmailUnique {

    String message() default "{UserEMailUnique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}