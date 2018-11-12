package com.marcobehler.quartz;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
@ComponentScan
@EnableScheduling
@EnableAsync
public class MyApplication {

    /*@Bean
    public JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean job = new JobDetailFactoryBean();
        job.setJobClass(HelloJob.class);
        job.setDescription("This is the best job in the world!");
        job.setDurability(true);
        return job;
    }*/

    @Bean
    public MethodInvokingJobDetailFactoryBean jobDetail() {
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setTargetBeanName("helloJob");
        bean.setTargetMethod("someMethod");
        return bean;
    }

    @Bean
    public SimpleTriggerFactoryBean trigger(JobDetail job) {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(job);
        trigger.setRepeatInterval(SimpleTrigger.REPEAT_INDEFINITELY);
        trigger.setRepeatInterval(1000);
        return trigger;
    }

    @Bean
    public SchedulerFactoryBean scheduler(Trigger trigger, JobDetail job) {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setJobDetails(job);
        scheduler.setTriggers(trigger);
        return scheduler;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyApplication.class);
        HelloJob job = ctx.getBean(HelloJob.class);
        System.out.println(job);

    }
}
