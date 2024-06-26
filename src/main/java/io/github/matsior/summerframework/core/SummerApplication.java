package io.github.matsior.summerframework.core;

import io.github.matsior.summerframework.web.WebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class used to run Summer Application and initialize context.
 *
 * @author Mateusz Krajewski
 */
public class SummerApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(SummerApplication.class);

  /**
   * Static method used to initialize Summer Application
   *
   * @param mainClass main class to load
   * @return {@link Context}
   */
  public static Context start(Class<?> mainClass)  {
    long start = System.currentTimeMillis();

    Thread mainThread = Thread.currentThread();

    new Banner().printBanner();
    Context context = new Context(mainClass);

    WebServer webServer = new WebServer(8080);
    Thread serverThread = new Thread(webServer);
    serverThread.start();
    Runtime.getRuntime().addShutdownHook(new Thread(webServer::shutdown));

    long end = System.currentTimeMillis();
    LOGGER.info("{} started successfully. Startup took: {}ms", mainClass.getSimpleName(), (end - start));
    return context;
  }

}
