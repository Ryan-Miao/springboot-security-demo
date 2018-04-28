package com.test.springsecuritydemo.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 如果FindBug报错，比如EI_EXPOSE_REP，这种返回一个可变对象造成的错误。如果你认为没有影响，可以通过本注解忽略. 但是，也不要滥用。
 */
@Retention(RetentionPolicy.CLASS)
public @interface SuppressFBWarnings {

    /**
     * The set of FindBugs warnings that are to be suppressed in annotated element. The value can be
     * a bug category, kind or pattern.
     */
    String[] value() default {};

    /**
     * Optional documentation of the reason why the warning is suppressed.
     */
    String justification() default "";
}
