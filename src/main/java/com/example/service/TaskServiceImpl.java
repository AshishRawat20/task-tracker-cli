package com.example.service;

import com.example.entity.Task;
import com.example.enums.TaskStatus;
import com.example.repository.TasksRepository;
import java.io.IOException;
import java.util.NoSuchElementException;

public class TaskServiceImpl implements TasksService {


  private TasksRepository tasksRepository;

  public TaskServiceImpl(TasksRepository tasksRepository) {
    this.tasksRepository = tasksRepository;
  }

  @Override
  public void addTaskWithDescription(String description) {
    int lastId =  tasksRepository.getMaxTaskId();
    Task taskToCreate = new Task(lastId, description, TaskStatus.TODO);
    tasksRepository.addTask(taskToCreate);
  }

  @Override
  public void printTasks() {
    for(Task task : tasksRepository.  getCurrentTasks()){
      System.out.println(task);
    }
  }

  @Override
  public void deleteTask(Integer taskId) {
    tasksRepository.deleteTask(taskId);
  }

  @Override
  public void updateTask(Integer taskId, String description) throws NoSuchElementException{
    tasksRepository.updateTask(taskId, description);
  }

  @Override
  public void updateTaskStatus(Integer taskId, TaskStatus taskStatus) throws NoSuchElementException {
    tasksRepository.updateTaskStatus(taskId, taskStatus);
  }

  @Override
  public void persistTasks() throws IOException {
    tasksRepository.writeCurrentTasksToFile();
  }
}
