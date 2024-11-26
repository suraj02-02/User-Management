package com.bridge.skill.usermanagement.util;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@Component
public class AsyncTaskAcceptor {

    private final ExecutorService executorService;

    public AsyncTaskAcceptor(@Qualifier("virtualThreadPerTaskExecutor") final ExecutorService executorService) {
        this.executorService = executorService;
    }

    /**
     * Method is used to submit task to executor service
     * @param runnable Task
     */
    public void submit(final Runnable runnable) {
        try{
            log.info("Submitting task to executor service");
            this.executorService.submit(runnable);
            log.info("Submitted task to executor service");
        }catch (Exception e) {
            throw new RuntimeException("Error while submitting task to executor service", e);
        }

    }
}
