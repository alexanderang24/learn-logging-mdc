package com.example.mdc;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.MDC;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class MdcController {

    private ExecutorService executorService = Executors.newFixedThreadPool(3);

    @PostMapping("/logginglevel")
    public void printLoggingLevel(String userId, String email) {

        MDC.put("userId", userId);
        MDC.put("email", email);

        log.trace("Trace Message!");
        log.debug("Debug Message!");
        log.info("Info Message!");
        log.warn("Warn Message!");
        log.error("Error Message!");
    }

    @PostMapping("/multithreading")
    public void multiThreading(String userId, String email) {

        MDC.put("userId", userId);
        MDC.put("email", email);

        for (int i = 1; i <= 10; i++) {
            MDC.put("processId", String.valueOf(i));
            executorService.execute(new MdcThreadRunner());
        }

        MDC.put("userId", "");
        MDC.put("email", "");
        MDC.put("processId", "");
        MDC.put("value", "");

        try {
            executorService.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("Executor service process interrupted.");
            Thread.currentThread().interrupt();
        }

        log.info("All task has been executed.");
    }
}
