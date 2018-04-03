package space.polarfish.musicmeta.data.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target(value = {METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {GenreValidator.class})
public @interface GenreIsValid {
    String message() default "{space.polarfish.musicmeta.data.validation.GenreIsValid.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
