package com.marcobehler.quartzspringboot;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class TheGreatestJobInTheWorld extends QuartzJobBean {

    private String name;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Hi, my name is: " + name);
    }

    public void setName(String name) {
        this.name = name;
    }
}
