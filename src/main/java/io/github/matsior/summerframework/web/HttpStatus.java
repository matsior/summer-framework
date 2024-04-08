package io.github.matsior.summerframework.web;

public enum HttpStatus {
  OK(200),
  NO_CONTENT(204),
  BAD_REQUEST(400),
  NOT_FOUND(404);

  private final int statusCode;

  HttpStatus(int statusCode) {
    this.statusCode = statusCode;
  }

}
