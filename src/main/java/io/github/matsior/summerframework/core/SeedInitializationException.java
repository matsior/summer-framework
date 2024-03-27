package io.github.matsior.summerframework.core;

public class SeedInitializationException extends RuntimeException {

  public SeedInitializationException() {
  }

  public SeedInitializationException(String message) {
    super(message);
  }

  public SeedInitializationException(String message, Throwable cause) {
    super(message, cause);
  }

  public SeedInitializationException(Throwable cause) {
    super(cause);
  }
}
