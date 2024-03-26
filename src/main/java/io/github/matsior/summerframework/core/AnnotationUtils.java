package io.github.matsior.summerframework.core;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * Utility class for basic annotation operations
 *
 * @author Mateusz Krajewski
 */
public final class AnnotationUtils {

  private AnnotationUtils() {
  }

  /**
   * Counts number of types which are annotated with specific annotation
   *
   * @param aClass class to inspect
   * @param annotation annotation to check
   * @return number of annotation occurrences on constructor, field or method
   */
  public static int countAnnotationOccurrences(Class<?> aClass, Class<? extends Annotation> annotation) {
    int numerOfAnnotations = 0;
    if (Arrays.stream(aClass.getConstructors()).anyMatch(constructor -> constructor.isAnnotationPresent(annotation))) {
      numerOfAnnotations++;
    }
    if (Arrays.stream(aClass.getMethods()).anyMatch(method -> method.isAnnotationPresent(annotation))) {
      numerOfAnnotations++;
    }
    if (Arrays.stream(aClass.getDeclaredFields()).anyMatch(field -> field.isAnnotationPresent(annotation))) {
      numerOfAnnotations++;
    }
    return numerOfAnnotations;
  }

}
