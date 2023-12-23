package de.ingenhaag.getmydeck.config;

import org.springframework.boot.task.ThreadPoolTaskSchedulerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulingConfiguration {

    @Bean
    ThreadPoolTaskSchedulerCustomizer taskSchedulerCustomizer() {
        return taskScheduler -> {
            taskScheduler.setAcceptTasksAfterContextClose(false);
            taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
            taskScheduler.setAwaitTerminationSeconds(10);
        };
    }

}
