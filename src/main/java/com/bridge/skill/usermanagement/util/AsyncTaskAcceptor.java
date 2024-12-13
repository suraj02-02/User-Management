package com.bridge.skill.usermanagement.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Slf4j
@Component
public class AsyncTaskAcceptor {

    private final ExecutorService executorService;

    public AsyncTaskAcceptor(@Qualifier("virtualThreadPerTaskExecutor") final ExecutorService executorService) {
        this.executorService = executorService;
    }

    /**
     * Method is used to submit task to executor service to process it through a virtual thread
     * @param runnable Task
     */
    public void submit(final Runnable runnable) {

        try{
            log.info("Submitting task to executor service");
            this.executorService.submit(runnable);
        }catch (Exception e) {
            throw new RuntimeException("Error while submitting task to executor service", e);
        }

    }
}
