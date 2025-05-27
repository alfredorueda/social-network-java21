package com.example.exceptions;

/**
 * Exception thrown when attempting to create a connection between users that already exists.
 * <p>
 * This is an unchecked exception as it represents a programming error that should be handled at development time.
 * </p>
 * 
 * <pre>
 * Example usage:
 *     if (connectionExists(userId1, userId2)) {
 *         throw new ConnectionAlreadyExistsException("Connection between users " + userId1 + " and " + userId2 + " already exists");
 *     }
 * </pre>
 */
public class ConnectionAlreadyExistsException extends RuntimeException {
    
    /**
     * Constructs a new ConnectionAlreadyExistsException with the specified detail message.
     *
     * @param message the detail message
     */
    public ConnectionAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructs a new ConnectionAlreadyExistsException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public ConnectionAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}