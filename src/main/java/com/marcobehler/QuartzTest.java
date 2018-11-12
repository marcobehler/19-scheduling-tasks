package com.marcobehler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Hello world!
 *
 */
public class QuartzTest
{
    public static void main( String[] args ) throws SchedulerException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        scheduler.start();

        JobDetail job = newJob(BankTransferJob.class)
                        .withIdentity("bank-transfer")
                        .build();

        SimpleTrigger trigger = newTrigger().withIdentity("trigger1")
                .startNow()
                .withSchedule(simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .build();

        scheduler.scheduleJob(job, trigger );
    }

    public static class BankTransferJob implements Job {
        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            System.out.println("I am transferring money to my girlfriend.....");
        }
    }
}
