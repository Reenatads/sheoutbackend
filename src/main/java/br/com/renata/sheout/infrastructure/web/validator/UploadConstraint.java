
package br.com.renata.sheout.infrastructure.web.validator;


import br.com.renata.sheout.util.FileType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
@Constraint(validatedBy = UploadValidator.class)
public @interface UploadConstraint {

	String message() default "Invalid file";
	FileType[] acceptedTypes();
	
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
