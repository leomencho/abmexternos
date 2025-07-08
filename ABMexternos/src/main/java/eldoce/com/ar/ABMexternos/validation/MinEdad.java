package eldoce.com.ar.ABMexternos.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MinEdadValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinEdad {
    String message() default "Debe tener al menos {value} años";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int value();
}
