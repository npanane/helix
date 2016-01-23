package com.framework.helix.exception;

/**
 * Exception wrapper for the module.
 *
 * @author  Nuwan.N.Panane
 * @see Exception
 * @version 20104.2.0
 * @since   Version 2014.2.0
 */
public class HelixDaoException extends Exception {

    /**
     * NichproSchedulerException class constructor
     */
    public HelixDaoException() {
    }

    /**
     * NichproSchedulerException class constructor
     * @param message
     */
    public HelixDaoException(String message) {
        super(message);
    }

    /**
     * NichproSchedulerException class constructor
     * @param throwable
     */
    public HelixDaoException(Throwable throwable) {
        super(throwable);
    }

    /**
     * NichproSchedulerException class constructor
     * @param message
     * @param throwable
     */
    public HelixDaoException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
