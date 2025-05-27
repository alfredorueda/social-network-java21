package com.example.exceptions;

/**
 * Exception thrown when attempting to access a user that does not exist in the social network.
 * <p>
 * This is an unchecked exception as it represents a programming error that should be handled at development time.
 * </p>
 * 
 * <pre>
 * Example usage:
 *     if (!userExists(userId)) {
 *         throw new UserNotFoundException("User with ID " + userId + " not found");
 *     }
 * </pre>
 */
public class UserNotFoundException extends RuntimeException {
    
    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new UserNotFoundException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}