package com.framework.helix.exception;

/**
 * Exception wrapper for the module.
 *
 * @author  Nuwan.N.Panane
 * @see Exception
 * @version 20104.2.0
 * @since   Version 2014.2.0
 */
public class HelixServiceException extends Exception {

    /**
     * NichproSchedulerException class constructor
     */
    public HelixServiceException() {
    }

    /**
     * NichproSchedulerException class constructor
     * @param message
     */
    public HelixServiceException(String message) {
        super(message);
    }

    /**
     * NichproSchedulerException class constructor
     * @param throwable
     */
    public HelixServiceException(Throwable throwable) {
        super(throwable);
    }

    /**
     * NichproSchedulerException class constructor
     * @param message
     * @param throwable
     */
    public HelixServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
