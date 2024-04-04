package io.github.matsior.summerframework.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Http {

  HttpMethod method();

  String path() default "/";

  enum HttpMethod {
    GET,
    POST,
    PUT,
    PATCH,
    DELETE
  }

}
