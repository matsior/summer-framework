package com.github.matsior.summerframework.core;

/**
 * Class used to run Summer Application and initialize context.
 *
 * @author Mateusz Krajewski
 */
public class SummerApplication {

  /**
   * Static method used to initialize Summer Application
   *
   * @param mainClass main class to load
   * @return {@link Context}
   */
  public static Context start(Class<?> mainClass)  {
    long start = System.currentTimeMillis();
    new Banner().printBanner();
    Context context = new Context();
    long end = System.currentTimeMillis();
    System.out.println(mainClass.getSimpleName() + " started successfully. Startup took: " + (end - start) + "ms");
    return context;
  }

}
