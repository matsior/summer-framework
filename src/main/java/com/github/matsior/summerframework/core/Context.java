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

  private void initialize(Class<?> sourceClass) {
    ClassScanner classScanner = new ClassScanner();
    Set<Class<?>> classes = classScanner.scanSeedsIn(sourceClass.getPackage());
    System.out.println("Found seeds:");
    classes.forEach(seed -> System.out.println("\t" + seed));

  }

}
