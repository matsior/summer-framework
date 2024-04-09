package io.github.matsior.summerframework.web;

public enum HttpStatus {
  OK(200, "OK"),
  NO_CONTENT(204, "No Content"),
  BAD_REQUEST(400, "Bad Request"),
  NOT_FOUND(404, "Not Found");

  private final int statusCode;
  private final String description;

  HttpStatus(int statusCode, String description) {
    this.statusCode = statusCode;
    this.description = description;
  }

}
