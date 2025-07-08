package eldoce.com.ar.ABMexternos.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MinEdadValidator implements ConstraintValidator<MinEdad, LocalDate> {

    private int edadMinima;

    @Override
    public void initialize(MinEdad constraintAnnotation) {
        this.edadMinima = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(LocalDate fechaNacimiento, ConstraintValidatorContext context) {
        if (fechaNacimiento == null) return true;
        return ChronoUnit.YEARS.between(fechaNacimiento, LocalDate.now()) >= edadMinima;
    }
}

