package com.github.matsior.summerframework.core;

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
        seedHolder.addSeed(aClass.getName(), aClass.getConstructor().newInstance());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

}
