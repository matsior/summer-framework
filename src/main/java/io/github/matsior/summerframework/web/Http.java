package io.github.matsior.summerframework.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks method as HTTP requests handler
 *
 * @author Mateusz Krajewski
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Http {

  /**
   * Indicates what HTTP method should be handling
   *
   * @return HttpMethod to handle
   */
  HttpMethod method();

  /**
   * Indicates request path to handle
   *
   * @return request path
   */
  String path() default "/";

  enum HttpMethod {
    GET,
    POST,
    PUT,
    PATCH,
    DELETE
  }

}
