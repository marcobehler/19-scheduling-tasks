package com.marcobehler.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Thanks for watching this episode! If you want to, drop me a line to info@marcobehler.com.
 */
@Component
public class HelloJob implements Job {

    @Autowired
    private UserService service;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("Jooo");
        service.sendTextUser();
    }


    public void someMethod() {
        System.out.println("Jooo");
        service.sendTextUser();
    }
}
