package io.github.matsior.summerframework.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to indicate how to autowire "seeds".
 * <p>When used on constructor, constructor based dependency injection will be used.
 * When used on fields, field based dependency injection will be used.
 * When used on setters, setter dependency injection will be used.
 *
 * <p>Note that only one dependency injection strategy can work in class.
 * Don't mix it in order to avoid unexpected results.
 *
 * @author Mateusz Krajewski
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {

}
