package base.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {
    int id() default 99;

    String name() default "zdw";

    char sex() default 'M';
}
