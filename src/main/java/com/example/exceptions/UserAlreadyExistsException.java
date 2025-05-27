package com.example.exceptions;

/**
 * Exception thrown when attempting to register a user with an ID that already exists in the social network.
 * <p>
 * This is an unchecked exception as it represents a programming error that should be handled at development time.
 * </p>
 * 
 * <pre>
 * Example usage:
 *     if (userExists(userId)) {
 *         throw new UserAlreadyExistsException("User with ID " + userId + " already exists");
 *     }
 * </pre>
 */
public class UserAlreadyExistsException extends RuntimeException {
    
    /**
     * Constructs a new UserAlreadyExistsException with the specified detail message.
     *
     * @param message the detail message
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructs a new UserAlreadyExistsException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}