package com.example.exceptions;

/**
 * Exception thrown when no path exists between two users in the social network.
 * <p>
 * This is an unchecked exception as it represents a programming error that should be handled at development time.
 * </p>
 * 
 * <pre>
 * Example usage:
 *     if (!pathExists(userId1, userId2)) {
 *         throw new NoPathException("No path exists between user " + userId1 + " and user " + userId2);
 *     }
 * </pre>
 */
public class NoPathException extends RuntimeException {
    
    /**
     * Constructs a new NoPathException with the specified detail message.
     *
     * @param message the detail message
     */
    public NoPathException(String message) {
        super(message);
    }

    /**
     * Constructs a new NoPathException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public NoPathException(String message, Throwable cause) {
        super(message, cause);
    }
}