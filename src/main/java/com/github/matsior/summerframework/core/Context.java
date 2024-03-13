package com.github.matsior.summerframework.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
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
    System.out.println("Found seed candidates:");
    classes.forEach(seed -> System.out.println("\t" + seed.getName()));

    for (final Class<?> aClass : classes) {
      try {
        autowire(aClass);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }

    System.out.println("Context initialized with seeds:");
    seedHolder.getAllSeedNames().forEach(seed -> System.out.println("\t" + seed));
  }

  private <T> T autowire(Class<T> aClass) {
    if (hasMultipleAutowireStrategy(aClass)) {
      throw new RuntimeException("Only one autowire strategy per type is allowed"); // TODO add custom exception
    }

    if (Arrays.stream(aClass.getConstructors()).anyMatch(constructor -> constructor.isAnnotationPresent(Autowired.class))) {
      return autowireByConstructor(aClass);
    }

    if (Arrays.stream(aClass.getMethods()).anyMatch(method -> method.isAnnotationPresent(Autowired.class))) {
      return autowireBySetter(aClass);
    }

    return autowireByField(aClass);
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

  private <T> T autowireByConstructor(Class<T> aClass) {
    return null; // TODO
  }

  private <T> T autowireBySetter(Class<T> aClass) {
    try {
      T instance = getInstance(aClass);

      Method[] dependencies = Arrays.stream(aClass.getMethods())
              .filter(this::isSetter)
              .filter(method -> method.isAnnotationPresent(Autowired.class))
              .toArray(Method[]::new);

      for (Method dependency : dependencies) {
        Class<?> type = dependency.getParameters()[0].getType();
        dependency.setAccessible(true);
        dependency.invoke(instance, autowire(type));
      }

      return instance;
    } catch (InvocationTargetException | IllegalAccessException e) {
      throw new RuntimeException(e); // TODO add custom exception
    }
  }

  private <T> T autowireByField(Class<T> aClass) {
      return getInstance(aClass); // TODO inject all fields in class
  }

  private <T> T getInstance(Class<T> aClass) {
    if (Objects.isNull(seedHolder.getSeed(aClass))) {
      try {
        T instance = aClass.newInstance();
        seedHolder.addSeed(instance.getClass().getName(), instance);
        return instance;
      } catch (InstantiationException | IllegalAccessException e) {
        throw new RuntimeException(e); // TODO add custom exception
      }
    }
    return seedHolder.getSeed(aClass);
  }

  private boolean isSetter(Method method) {
    return method.getName().startsWith("set") && method.getParameterCount() == 1;
  }

}
