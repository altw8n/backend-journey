package org.example;

import org.springframework.context.annotation.Bean;

public class TaskConfiguration {
    @Bean
    public Task task(){
        return new Task();
    }

    @Bean
    public TaskManager taskManager(Task task){
        return new TaskManager(task);
    }
}
