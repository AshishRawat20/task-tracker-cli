package com.example.enums;

public enum TaskStatus {
  TODO(1, "TODO"),
  INPROGRESS(2, "IN-PROGESS"),
  DONE(3, "DONE");

  private final int status;
  private final String description;

  private TaskStatus(int status, String description) {
    this.status = status;
    this.description = description;
  }

}