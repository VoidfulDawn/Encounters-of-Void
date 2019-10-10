package voidful.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
    private static final Logger log = LoggerFactory.getLogger("Encounters_Of_Void");

    public static final void logInfo(String message) {
	log.info(message);
    }

    public static final void logInfo(String message, Object... objects) {
	log.info(message, objects);
    }

    public static final void logWarn(String message) {
	log.warn(message);
    }

    public static final void logWarn(String message, Object... objects) {
	log.warn(message, objects);
    }

    public static final void logError(String message) {
	log.error(message);
    }

    public static final void logError(String message, Object... objects) {
	log.error(message, objects);
    }

}
