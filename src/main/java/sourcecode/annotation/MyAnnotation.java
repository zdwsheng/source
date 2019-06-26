package sourcecode.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {
    public int id() default 99;

    public String name() default "zdw";

    public char sex() default 'M';
}
