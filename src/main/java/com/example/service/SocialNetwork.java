package com.example.service;

import com.example.domain.Persona;
import com.example.exceptions.ConnectionAlreadyExistsException;
import com.example.exceptions.NoPathException;
import com.example.exceptions.UserAlreadyExistsException;
import com.example.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Set;

/**
 * Interface defining the operations of a social network.
 * <p>
 * This interface provides methods for managing users and their connections
 * in a social network. It includes operations for user registration, connection
 * management, and graph traversal algorithms for finding paths between users.
 * </p>
 */
public interface SocialNetwork {

    /**
     * Registers a new user in the social network.
     * <p>
     * Time Complexity: O(1) - Using a HashMap for storage provides constant time 
     * complexity for user registration operations.
     * </p>
     *
     * @param user the user to register
     * @throws UserAlreadyExistsException if a user with the same ID already exists
     * @throws NullPointerException if the user is null
     */
    void registerUser(Persona user);

    /**
     * Creates a bidirectional connection between two users.
     * <p>
     * Time Complexity: O(log n) - The operation requires finding both users in the 
     * network (O(1)) and then adding each user to the other's connection set. 
     * If using a TreeSet for storing connections, the add operation is O(log n).
     * </p>
     *
     * @param id1 the ID of the first user
     * @param id2 the ID of the second user
     * @throws UserNotFoundException if either user does not exist
     * @throws ConnectionAlreadyExistsException if the connection already exists
     * @throws IllegalArgumentException if trying to connect a user to themselves
     */
    void connect(String id1, String id2);

    /**
     * Retrieves the set of users directly connected to the user with the given ID.
     * <p>
     * Time Complexity: O(1) - Accessing the connections of a user from a HashMap
     * is a constant time operation.
     * </p>
     *
     * @param id the ID of the user
     * @return a set of users directly connected to the user
     * @throws UserNotFoundException if the user does not exist
     */
    Set<Persona> getFriends(String id);

    /**
     * Finds the shortest path (list of users) between two users.
     * <p>
     * Uses Breadth-First Search (BFS) to find the shortest path.
     * </p>
     * <p>
     * Time Complexity: O(V + E) where V is the number of users and E is the number
     * of connections in the network. This is the standard complexity for BFS.
     * </p>
     *
     * @param id1 the ID of the first user
     * @param id2 the ID of the second user
     * @return a list of users representing the shortest path from id1 to id2 (inclusive)
     * @throws UserNotFoundException if either user does not exist
     * @throws NoPathException if no path exists between the users
     */
    List<Persona> getConnectionPathBetween(String id1, String id2);

    /**
     * Calculates the degree of connection between two users.
     * <p>
     * The degree of connection is the number of edges in the shortest path 
     * between two users. A direct connection has a degree of 1.
     * </p>
     * <p>
     * Time Complexity: O(V + E) where V is the number of users and E is the number
     * of connections in the network. This is based on the BFS algorithm used to find
     * the shortest path.
     * </p>
     *
     * @param id1 the ID of the first user
     * @param id2 the ID of the second user
     * @return the degree of connection between the users
     * @throws UserNotFoundException if either user does not exist
     * @throws NoPathException if no path exists between the users
     */
    int getConnectionLevelBetween(String id1, String id2);

    /**
     * Retrieves a set of all users ordered by their registration date.
     * <p>
     * Time Complexity: O(n) where n is the number of users. This operation requires
     * iterating through all users to create an ordered set.
     * </p>
     *
     * @return a set of users ordered by registration date
     */
    Set<Persona> getUsersOrderedByRegistration();
}