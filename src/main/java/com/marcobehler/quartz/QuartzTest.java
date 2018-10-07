package com.marcobehler.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Properties;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Thanks for watching this episode! If you want to, drop me a line to info@marcobehler.com.
 */
public class QuartzTest {

    public static void main(String[] args) {

        Properties properties = new Properties();

        properties.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        properties.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        properties.put("org.quartz.jobStore.dataSource", "quartzDataSource");

        properties.put("org.quartz.dataSource.quartzDataSource.driver","org.h2.Driver");
        properties.put("org.quartz.dataSource.quartzDataSource.URL","jdbc:h2:~/quartz");
        properties.put("org.quartz.dataSource.quartzDataSource.user","sa");
        properties.put("org.quartz.dataSource.quartzDataSource.password","");
        properties.put("org.quartz.threadPool.threadCount","5");

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = new StdSchedulerFactory(properties).getScheduler();


            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(HelloJob.class)
                    .withIdentity("job1", "group1")
                    .build();

            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger simpleTrigger = (SimpleTrigger) newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(10)
                            .repeatForever())
                    .build();

            Trigger cronTrigger = newTrigger()
                    .withIdentity("cronTrigger")
                    .withSchedule(cronSchedule("0 0 12 ? * WED"))
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, simpleTrigger);

            // and start it off
            scheduler.start();


        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
