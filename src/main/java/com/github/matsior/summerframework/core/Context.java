package com.github.matsior.summerframework.core;

import java.util.Arrays;
import java.util.Set;

/**
 * Class which holds application configuration and seeds.
 *
 * @author Mateusz Krajewski
 */
public class Context {

  private final SeedHolder seedHolder;

  public Context(Class<?> sourceClass) {
    this.seedHolder = new SeedHolder();
    initialize(sourceClass);
  }

  public SeedHolder getSeedHolder() {
    return seedHolder;
  }

  private void initialize(Class<?> sourceClass) {
    ClassScanner classScanner = new ClassScanner();
    Set<Class<?>> classes = classScanner.scanSeedsIn(sourceClass.getPackage());
    System.out.println("Found seeds:");
    classes.forEach(seed -> System.out.println("\t" + seed));

    for (final Class<?> aClass : classes) {
      try {
        autowire(aClass);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

  private void autowire(Class<?> aClass) {
    if (hasMultipleAutowireStrategy(aClass)) {
      throw new RuntimeException("Only one autowire strategy per type is allowed"); // TODO add custom exception
    }
  }

  private boolean hasMultipleAutowireStrategy(Class<?> aClass) {
    int numerOfAnnotations = 0;
    if (Arrays.stream(aClass.getConstructors()).anyMatch(constructor -> constructor.isAnnotationPresent(Autowired.class))) {
      numerOfAnnotations++;
    }
    if (Arrays.stream(aClass.getMethods()).anyMatch(method -> method.isAnnotationPresent(Autowired.class))) {
      numerOfAnnotations++;
    }
    if (Arrays.stream(aClass.getDeclaredFields()).anyMatch(field -> field.isAnnotationPresent(Autowired.class))) {
      numerOfAnnotations++;
    }
    return numerOfAnnotations > 1;
  }

  private void autowireByConstructor(Class<?> aClass) {
    // TODO
  }

  private void autowireBySetter(Class<?> aClass) {
    // TODO
  }

  private void autowireByField(Class<?> aClass) {
    // TODO
  }

}
