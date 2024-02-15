package com.atm.feign.listener.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Customer logger for Feign Client logging
 */
public class FeignClientLogger extends feign.Logger {
    public static Logger logger = LoggerFactory.getLogger(FeignClientLogger.class);

    @Override
    protected void log(String configKey, String format, Object... args) {
        String logMessage = String.format(configKey + " - " + format, args);
        logger.debug(logMessage);
    }
}
