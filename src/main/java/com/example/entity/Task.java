package com.example.entity;

import com.example.enums.TaskStatus;
import java.lang.*;

public class Task {
  private int id;
  private String description;
  private TaskStatus status;

  public Task(){

  }

  public Task(int id, String description, TaskStatus status){
    this.id = id;
    this.description = description;
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }

  @Override
  public java.lang.String toString() {
    return "Task{" +
        "id=" + id +
        ", description='" + description + '\'' +
        ", status=" + status +
        '}';
  }
}