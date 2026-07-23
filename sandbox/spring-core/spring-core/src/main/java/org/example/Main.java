package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    static void main() {
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskConfiguration.class);
        Task task = context.getBean(Task.class);
        Task task2 = (Task) context.getBean("task");
        System.out.println(task);
        System.out.println(task2);
        System.out.println(task == task2); //тот же самый объект
        TaskManager taskMgr =  context.getBean(TaskManager.class);
        taskMgr.printTask(task);

    }
}

