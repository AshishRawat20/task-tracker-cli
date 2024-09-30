package com.example;

import com.example.entity.*;
import com.example.enums.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */

public class App 
{
    public static void main( String[] args ) throws IOException {

        File file = new File("src/main/java/com/example/data/tasks.json");
        file.createNewFile();
        ObjectMapper objectMapper = new ObjectMapper();

        List<Task> currentTasks =  objectMapper.readValue(file, new TypeReference<>() {});
        System.out.println("------------- TASK TRACKER ---------------");
        System.out.println("Enter 'X' to exit");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();


        while(!Objects.equals(command, "X")){
            System.out.println(command);
            String[] commands = command.split(" ");
            switch (commands[0]) {
                case "add" : {
                    if(commands.length == 1) {
                        System.out.println("No task given.");
                        break;
                    }
                    int lastId = currentTasks.stream().max(Comparator.comparingInt(Task::getId)).map(Task::getId).orElse(0) + 1;
                    Task taskToCreate = new Task(lastId, commands[1], TaskStatus.TODO);
                    currentTasks.add(taskToCreate);
                    System.out.println("Task added successfully.");
                    break;
                }
                case "update" : {
                    if(commands.length < 3) {
                        System.out.println("Invalid arguments. Give task id and description.");
                        break;
                    }
                    Task currrentTask = currentTasks.stream().filter(task -> String.valueOf(
                        task.getId()).equals(commands[1])).findFirst().orElse(null);
                    if (currrentTask == null){
                        System.out.println("No task found with given id");
                    }
                    else {
                        currrentTask.setDescription(commands[2]);
                    }
                    break;
                }
                case "delete" : {
                    if(commands.length < 2) {
                        System.out.println("Invalid arguments. Give task id.");
                        break;
                    }
                    currentTasks = currentTasks.stream().filter(task -> !String.valueOf(
                        task.getId()).equals(commands[1])).collect(Collectors.toList());
                    break;
                }
                case "list" : {
                    for(Task task : currentTasks){
                        System.out.println(task);
                    }
                    break;
                }
                case "mark-in-progress", "mark-done": {
                    if(commands.length < 2) {
                        System.out.println("Invalid arguments. Give task id.");
                        break;
                    }
                    Task currrentTask = currentTasks.stream().filter(task -> String.valueOf(
                        task.getId()).equals(commands[1])).findFirst().orElse(null);
                    if (currrentTask == null){
                        System.out.println("No task found with given id");
                    }
                    else {
                        TaskStatus newStatus = Objects.equals("mark-in-progress", commands[0]) ? TaskStatus.INPROGRESS : TaskStatus.DONE;
                        currrentTask.setStatus(newStatus);
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
        objectMapper.writeValue(file, currentTasks);
    }
}
