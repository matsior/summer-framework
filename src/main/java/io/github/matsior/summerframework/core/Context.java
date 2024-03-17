package io.github.matsior.summerframework.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Field;
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

  private static final Logger LOGGER = LoggerFactory.getLogger(Context.class);

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
    LOGGER.info("Found seed candidates: {}", classes);

    for (final Class<?> aClass : classes) {
      try {
        autowire(aClass);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }

    System.out.println("Context initialized with seeds:");
    seedHolder.getAllSeedNames().forEach(seed -> System.out.println("\t" + seed));
    LOGGER.info("Context initialized with seeds: {}", seedHolder.getAllSeedNames());
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
              .filter(method -> method.isAnnotationPresent(Autowired.class))
              .filter(this::isSetter)
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
    T instance = getInstance(aClass);

    Field[] dependencies = Arrays.stream(aClass.getDeclaredFields())
            .filter(field -> field.isAnnotationPresent(Autowired.class))
            .toArray(Field[]::new);

    for (Field dependency : dependencies) {
      try {
        Class<?> type = dependency.getType();
        dependency.setAccessible(true);
        dependency.set(instance, autowire(type));
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e); // TODO add custom exception
      }
    }

    return instance;
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
    return method.getName().startsWith("set") && method.getParameterCount() == 1 && method.getReturnType().equals(Void.TYPE);
  }

}
