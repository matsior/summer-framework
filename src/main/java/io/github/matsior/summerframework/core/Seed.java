package io.github.matsior.summerframework.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to mark class as "seed".
 * Marked classes will be automatically discovered during scanning and
 * injected to context.
 *
 * @author Mateusz Krajewski
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Seed {

}
