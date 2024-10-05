package com.example.service;

import com.example.enums.TaskStatus;
import java.io.IOException;
import java.util.NoSuchElementException;

public interface TasksService {


  void addTaskWithDescription(String description);

  void printTasks();

  void deleteTask(Integer taskId);

  void updateTask(Integer taskId, String description) throws NoSuchElementException;

  void updateTaskStatus(Integer taskId, TaskStatus taskStatus) throws NoSuchElementException;

  void persistTasks() throws IOException;
}
