package com.example.mdc;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.Map;
import java.util.Random;

@Slf4j
public class MdcThreadRunner implements Runnable{

    private Map<String, String> contextMap = MDC.getCopyOfContextMap();

    public void run() {
        MDC.setContextMap(contextMap);
        MDC.put("value", String.valueOf(new Random().nextInt(100)));

        log.info("Preparing to assign IP address");
        log.info("Assigning IP...");
        log.info("Assigning IP success!");
    }
}
