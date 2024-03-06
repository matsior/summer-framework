package com.github.matsior.summerframework.core;

/**
 * Class which holds application configuration and seeds.
 *
 * @author Mateusz Krajewski
 */
public class Context {

  private final SeedHolder seedHolder;

  public Context() {
    this.seedHolder = new SeedHolder();
  }

}
