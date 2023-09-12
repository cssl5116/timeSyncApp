package com.timeSync.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author fishx
 * @version 1.0
 * @description: 声明Java线程池
 * @date 2023/9/5 10:05
 */
@Configuration
public class ThreadPoolConfig {
  @Bean("AsyncTaskExecutor")
  public AsyncTaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    // 设置核心线程数
    executor.setCorePoolSize(8);
    // 设置最大线程数
    executor.setMaxPoolSize(16);
    // 设置队列容量
    executor.setQueueCapacity(32);
    // 设置默认线程名
    executor.setThreadNamePrefix("task-");
    // 设置拒绝策略
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    executor.initialize();
    return executor;
  }
}
