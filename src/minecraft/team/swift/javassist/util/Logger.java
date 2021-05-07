package team.swift.javassist.util;

import org.apache.logging.log4j.LogManager;

public class Logger {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger("Swift-Transformer");

    public static void info(Object message) {
        logger.info(message);
    }

    public static void warn(Object message) {
        logger.warn(message);
    }

    public static void error(Object message) {
        logger.error(message);
    }

}
