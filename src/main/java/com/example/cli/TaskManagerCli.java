package com.example.cli;

import com.example.enums.TaskStatus;
import com.example.service.TasksService;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class TaskManagerCli {

  private TasksService tasksService;

  public TaskManagerCli(TasksService tasksService){
    this.tasksService = tasksService;
  }

  private void printMenu(){
    System.out.println("------------- TASK TRACKER ---------------");
    System.out.println("List of commands");
    System.out.println("1. add 'task description' - add task with 'task description'");
    System.out.println("2. update task-id 'task description' - update task with task-id with 'task description'");
    System.out.println("3. delete task-id - delete task with task-id");
    System.out.println("4. list - list all tasks");
    System.out.println("5. mark-in-progress task-id - mark progress task with task-id");
    System.out.println("6. mark-done task-id - mark done task with task-id");
    System.out.println("Enter 'X' to exit");
    System.out.println("------------- TASK TRACKER ---------------");
  }

  public void run() throws IOException {
    printMenu();
    Scanner scanner = new Scanner(System.in);
    String command = scanner.nextLine();

    while(!Objects.equals(command, "X") && !Objects.equals(command, "x")){

      switch (command.split(" ")[0]) {
        case "add" : {
          String[] commands = command.split(" ", 2);
          if(commands.length == 1) {
            System.out.println("No task given.");
            break;
          }
          tasksService.addTaskWithDescription(commands[1]);
          System.out.println("Task added successfully.");
          break;
        }
        case "update" : {
          String[] commands = command.split(" ", 3);
          if(commands.length < 3) {
            System.out.println("Invalid arguments. Give task id and description.");
            break;
          }
          try{
            tasksService.updateTask(Integer.valueOf(commands[1]), commands[2]);
            System.out.println("Task updated successfully.");
          }
          catch (NoSuchElementException exc) {
            System.out.println("Task not found with given task id.");
          }
          break;
        }
        case "delete" : {
          String[] commands = command.split(" ", 2);
          if(commands.length < 2) {
            System.out.println("Invalid arguments. Give task id.");
            break;
          }
          tasksService.deleteTask(Integer.valueOf(commands[1]));
          System.out.println("Task deleted successfully.");
          break;
        }
        case "list" : {
          tasksService.printTasks();
          break;
        }
        case "mark-in-progress", "mark-done": {
          String[] commands = command.split(" ", 3);
          if(commands.length < 2) {
            System.out.println("Invalid arguments. Give task id.");
            break;
          }
          try{
            TaskStatus taskStatus = Objects.equals(commands[0], "mark-in-progress") ? TaskStatus.INPROGRESS : TaskStatus.DONE;
            tasksService.updateTaskStatus(Integer.valueOf(commands[1]), taskStatus);
            System.out.println("Task updated successfully.");
          }
          catch (NoSuchElementException exc) {
            System.out.println("Task not found with given task id.");
          }
          break;
        }
        default: {
          System.out.println("Invalid arguments.");
        }
      }
      System.out.println("Enter 'X' to exit");
      command = scanner.nextLine();
    }
    tasksService.persistTasks();
  }

}
