package com.github.matsior.summerframework.core;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.Set;

/**
 * Class scanner with basic methods for class scanning.
 *
 * @author Mateusz Krajewski
 */
public class ClassScanner {

  /**
   * Scans selected package to found seed candidates.
   * Inner packages are also scanned.
   *
   * @param packageToScan package to scan
   * @return set of found seeds
   */
  public Set<Class<?>> scanSeedsIn(Package packageToScan) {
    Reflections reflections = new Reflections(packageToScan.getName());
    return reflections.getTypesAnnotatedWith(Seed.class);
  }

}
