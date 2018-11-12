package com.marcobehler.quartzspringboot;

import org.quartz.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuartzSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuartzSpringbootApplication.class, args);
	}

	@Bean
	public JobDetail greatestJobDetail() {
		return JobBuilder.newJob(TheGreatestJobInTheWorld.class)
				.usingJobData("name", "Slim Shady!!")
				.storeDurably()
				.build();
	}

	@Bean
	public Trigger greatestTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
				.simpleSchedule()
				.withIntervalInSeconds(1)
				.repeatForever();

		return TriggerBuilder
				.newTrigger()
				.forJob(greatestJobDetail())
				.withSchedule(scheduleBuilder)
				.build();
	}
}
