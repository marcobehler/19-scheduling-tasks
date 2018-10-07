package com.marcobehler.quartz;

import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan
@EnableScheduling
@EnableAsync
public class MyApplication {

    @Bean
    public HelloJob job() {
        return new HelloJob();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyApplication.class);
        HelloJob job = ctx.getBean(HelloJob.class);
        job.execute(null);
        System.out.println(job);
    }
}
