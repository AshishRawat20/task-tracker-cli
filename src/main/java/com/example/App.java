package com.example;

import com.example.cli.TaskManagerCli;
import com.example.entity.*;
import com.example.enums.*;
import com.example.repository.TasksRepository;
import com.example.service.TaskServiceImpl;
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
        new TaskManagerCli(new TaskServiceImpl(new TasksRepository())).run();

    }
}
