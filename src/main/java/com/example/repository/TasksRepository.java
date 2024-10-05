package com.example.repository;

import com.example.entity.Task;
import com.example.enums.TaskStatus;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class TasksRepository {

  private List<Task> currentTasks;
  private ObjectMapper objectMapper;
  private File file;

  public TasksRepository() throws IOException {
    file = new File("src/main/java/com/example/data/tasks.json");
    file.createNewFile();
    objectMapper = new ObjectMapper();
    currentTasks = objectMapper.readValue(file, new TypeReference<>() {});
  }

  public int getMaxTaskId(){
    return currentTasks.stream().max(Comparator.comparingInt(Task::getId)).map(Task::getId).orElse(0) + 1;
  }

  public void addTask(Task task){
    currentTasks.add(task);
  }

  public void updateTask(int taskId, String description){
    Task taskToUpdate = currentTasks.stream().filter(task -> task.getId() == taskId).findFirst()
        .orElse(null);
    if(taskToUpdate == null){
      throw new NoSuchElementException("Task not found");
    }
    taskToUpdate.setDescription(description);
  }

  public void updateTaskStatus(int taskId, TaskStatus taskStatus){
    Task taskToUpdate = currentTasks.stream().filter(task -> task.getId() == taskId).findFirst()
        .orElse(null);
    if(taskToUpdate == null){
      throw new NoSuchElementException("Task not found");
    }
    taskToUpdate.setStatus(taskStatus);
  }

  public void deleteTask(int taskId){
    currentTasks = currentTasks.stream().filter(task -> taskId != task.getId()).collect(Collectors.toList());
  }

  public List<Task> getCurrentTasks() {
    return currentTasks;
  }

  public void writeCurrentTasksToFile() throws IOException {
    objectMapper.writeValue(file, currentTasks);
  }
}
