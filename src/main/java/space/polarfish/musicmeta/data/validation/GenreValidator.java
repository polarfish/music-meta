package space.polarfish.musicmeta.data.validation;

import space.polarfish.musicmeta.data.model.Genre;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenreValidator implements ConstraintValidator<GenreIsValid, Genre> {
    @Override
    public void initialize(GenreIsValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(Genre value, ConstraintValidatorContext context) {
        return value != Genre.NONE;
    }
}
