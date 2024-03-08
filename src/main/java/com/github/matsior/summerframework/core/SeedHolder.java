package com.github.matsior.summerframework.core;


import java.util.HashMap;
import java.util.Map;

/**
 * Class which holds seed definitions and allows basic operations on them.
 *
 * @author Mateusz Krajewski
 */
public class SeedHolder {

  /**
   * Map containing all seeds.
   * <br>Key - seed name
   * <br>Value - seed object
   */
  private final Map<String, Object> seeds;

  public SeedHolder() {
    this.seeds = new HashMap<>();
  }

  /**
   * Adds seed to context. If seed with given name already exists it will be overwritten.
   * @param name seed name
   * @param seed seed object
   */
  public void addSeed(String name, Object seed) {
    seeds.put(name, seed);
  }

  /**
   * Allows to get seed by type.
   * @param type seed type to be found
   * @return seed retrieved from context
   * @throws NullPointerException if bean with given type is not found
   */
  @SuppressWarnings("unchecked")
  public <T> T getSeed(Class<T> type) {
    return (T) seeds.values().stream()
        .filter(value -> value.getClass().equals(type))
        .findAny()
        .orElse(null);
  }

  /**
   * Allows to get seed by type.
   * @param name seed name to be found
   * @return seed retrieved from context
   * @throws NullPointerException if bean with given name is not found
   */
  public Object getSeed(String name) {
   return seeds.get(name);
  }

  /**
   * Allows to check if seed with given type exists in context.
   * @param type type of seed to check
   * @return true if seed exists in context, false if it not exists
   */
  public boolean containsSeed(Class<?> type) {
    return seeds.values().stream()
        .anyMatch(value -> value.getClass().equals(type));
  }

}
