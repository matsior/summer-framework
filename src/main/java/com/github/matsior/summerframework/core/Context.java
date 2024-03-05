package com.github.matsior.summerframework.core;

/**
 * Class which holds application configuration and seeds.
 *
 * @author Mateusz Krajewski
 */
public class Context {

  private final SeedFactory seedFactory;

  public Context() {
    this.seedFactory = new SeedFactory();
  }

}
