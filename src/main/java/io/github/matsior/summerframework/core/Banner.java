package io.github.matsior.summerframework.core;

/**
 * Application banner printed at application startup.
 *
 * @author Mateusz Krajewski
 */
public class Banner {

  private static final String BANNER = "\n"
      + "                                          \n"
      + "  ___ _   _ _ __ ___  _ __ ___   ___ _ __ \n"
      + " / __| | | | '_ ` _ \\| '_ ` _ \\ / _ \\ '__|\n"
      + " \\__ \\ |_| | | | | | | | | | | |  __/ |   \n"
      + " |___/\\__,_|_| |_| |_|_| |_| |_|\\___|_|   \n"
      + "                                          \n"
      + "                                          ";

  /**
   * Prints banner into console.
   */
  public void printBanner() {
    System.out.println(BANNER);
  }

}
