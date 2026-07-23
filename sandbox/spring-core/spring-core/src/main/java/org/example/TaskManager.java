package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskManager {
    private final Task task;


    @Autowired           // если конструктор один то аннотация не обяхательна
    public TaskManager(Task task){   // внедрение зависимости через конструктор
        this.task = task;
    }

    public void printTask(Task task){
        System.out.println("current task: " + task.toString());
    }


}
