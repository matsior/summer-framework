package com.github.matsior.summerframework.core;


import java.util.HashMap;
import java.util.Map;

/**
 * Class which holds seed definitions and allows basic operations on them.
 *
 * @author Mateusz Krajewski
 */
public class SeedFactory {

  /**
   * Map containing all seeds.
   * <br>Key - seed name
   * <br>Value - seed object
   */
  private final Map<String, Object> seeds;

  public SeedFactory() {
    this.seeds = new HashMap<>();
  }

  /**
   * Adds seed to context. If seed with given name already exists it will be overwritten.
   * @param name seed name
   * @param seed seed object
   */
  public void addSeed(String name, Object seed) {
    // TODO
    throw new IllegalStateException("Not implemented yet!");
  }

  /**
   * Allows to get seed by type.
   * @param type seed type to be found
   * @return seed retrieved from context
   * @throws NullPointerException if bean with given type is not found
   */
  public <T> T getSeed(Class<T> type) {
    // TODO
    throw new IllegalStateException("Not implemented yet!");
  }

  /**
   * Allows to get seed by type.
   * @param name seed name to be found
   * @return seed retrieved from context
   * @throws NullPointerException if bean with given name is not found
   */
  public Object getSeed(String name) {
    // TODO
    throw new IllegalStateException("Not implemented yet!");
  }

  /**
   * Allows to check if seed with given type exists in context.
   * @param type type of seed to check
   * @return true if seed exists in context, false if it not exists
   */
  public boolean containsSeed(Class<?> type) {
    // TODO
    throw new IllegalStateException("Not implemented yet!");
  }

}
