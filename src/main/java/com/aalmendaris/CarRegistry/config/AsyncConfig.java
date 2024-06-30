package com.aalmendaris.CarRegistry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
public class AsyncConfig {
    @Bean(name = "taskExecutor")
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //Maximo de hilos que hay en total y se podran ejecutar
        executor.setCorePoolSize(10);
        //Maximo de hilos que se ejecutan a la vez
        executor.setMaxPoolSize(5);
        //muestra la capacidad de la cola
        executor.setQueueCapacity(100);
        //nombra el hilo
        executor.setThreadNamePrefix("demoThread-");
        executor.initialize();
        return executor;
    }
}
