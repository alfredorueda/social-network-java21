package com.example.service;

import com.example.domain.Persona;
import com.example.exceptions.ConnectionAlreadyExistsException;
import com.example.exceptions.NoPathException;
import com.example.exceptions.UserAlreadyExistsException;
import com.example.exceptions.UserNotFoundException;

import java.util.*;

/**
 * Implementation of the SocialNetwork interface.
 * <p>
 * This class implements a social network using a graph data structure where:
 * <ul>
 *   <li>Users are represented as vertices</li>
 *   <li>Connections between users are represented as edges</li>
 *   <li>A LinkedHashMap is used to maintain insertion order of users</li>
 *   <li>TreeSet is used to store connections sorted by name and then by ID</li>
 * </ul>
 * </p>
 *
 * @see SocialNetwork
 */
public class SocialNetworkImpl implements SocialNetwork {

    /**
     * Map of users to their connections.
     * <p>
     * LinkedHashMap preserves insertion order, which helps in implementing
     * the {@link #getUsersOrderedByRegistration()} method.
     * </p>
     */
    private final LinkedHashMap<Persona, Set<Persona>> connections;

    /**
     * Constructs a new empty social network.
     */
    public SocialNetworkImpl() {
        this.connections = new LinkedHashMap<>();
    }

    /**
     * Comparator for ordering users by name and then by ID.
     * <p>
     * This provides a total ordering of users for the TreeSet.
     * </p>
     */
    private static final Comparator<Persona> PERSONA_COMPARATOR = Comparator
            .comparing(Persona::getName)
            .thenComparing(Persona::getId);

    /**
     * {@inheritDoc}
     * 
     * @implNote 
     * The implementation uses a LinkedHashMap to store users and their connections.
     * The put operation in a HashMap has O(1) average time complexity.
     */
    @Override
    public void registerUser(Persona user) {
        Objects.requireNonNull(user, "User cannot be null");
        
        if (connections.containsKey(user)) {
            throw new UserAlreadyExistsException("User with ID " + user.getId() + " already exists");
        }
        
        connections.put(user, new TreeSet<>(PERSONA_COMPARATOR));
    }

    /**
     * {@inheritDoc}
     * 
     * @implNote 
     * The implementation creates a bidirectional connection by adding each user
     * to the other's connection set. Time complexity is dominated by the TreeSet
     * operations which are O(log n).
     */
    @Override
    public void connect(String id1, String id2) {
        if (id1.equals(id2)) {
            throw new IllegalArgumentException("Cannot connect a user to themselves");
        }
        
        Persona user1 = findUserById(id1);
        Persona user2 = findUserById(id2);
        
        Set<Persona> user1Connections = connections.get(user1);
        Set<Persona> user2Connections = connections.get(user2);
        
        if (user1Connections.contains(user2)) {
            throw new ConnectionAlreadyExistsException(
                    "Connection between users " + id1 + " and " + id2 + " already exists");
        }
        
        user1Connections.add(user2);
        user2Connections.add(user1);
    }

    /**
     * {@inheritDoc}
     * 
     * @implNote 
     * The implementation simply returns the connection set for the user.
     * HashMap lookup is O(1).
     */
    @Override
    public Set<Persona> getFriends(String id) {
        Persona user = findUserById(id);
        return Collections.unmodifiableSet(connections.get(user));
    }

    /**
     * {@inheritDoc}
     * 
     * @implNote 
     * The implementation uses Breadth-First Search (BFS) to find the shortest path
     * between two users. BFS has a time complexity of O(V + E) where V is the number
     * of vertices (users) and E is the number of edges (connections).
     */
    @Override
    public List<Persona> getConnectionPathBetween(String id1, String id2) {
        Persona start = findUserById(id1);
        Persona end = findUserById(id2);
        
        if (start.equals(end)) {
            return List.of(start);
        }
        
        Queue<Persona> queue = new LinkedList<>();
        Map<Persona, Persona> previous = new HashMap<>();
        Set<Persona> visited = new HashSet<>();
        
        queue.add(start);
        visited.add(start);
        
        boolean pathFound = false;
        
        while (!queue.isEmpty() && !pathFound) {
            Persona current = queue.poll();
            
            for (Persona neighbor : connections.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    previous.put(neighbor, current);
                    
                    if (neighbor.equals(end)) {
                        pathFound = true;
                        break;
                    }
                }
            }
        }
        
        if (!pathFound) {
            throw new NoPathException("No path exists between user " + id1 + " and user " + id2);
        }
        
        // Reconstruct the path from end to start
        List<Persona> path = new ArrayList<>();
        Persona current = end;
        
        while (current != null) {
            path.add(current);
            current = previous.get(current);
        }
        
        // Reverse the path to get from start to end
        Collections.reverse(path);
        return path;
    }

    /**
     * {@inheritDoc}
     * 
     * @implNote 
     * The implementation finds the shortest path using BFS and then returns
     * the length of the path minus 1, which gives the number of edges in the path.
     */
    @Override
    public int getConnectionLevelBetween(String id1, String id2) {
        List<Persona> path = getConnectionPathBetween(id1, id2);
        // The connection level is the number of edges, which is the number of vertices - 1
        return path.size() - 1;
    }

    /**
     * {@inheritDoc}
     * 
     * @implNote 
     * Since LinkedHashMap maintains insertion order and users are registered in 
     * order of their registration date, simply returning the key set provides
     * the users ordered by registration.
     */
    @Override
    public Set<Persona> getUsersOrderedByRegistration() {
        return Collections.unmodifiableSet(connections.keySet());
    }
    
    /**
     * Helper method to find a user by ID.
     *
     * @param id the ID of the user to find
     * @return the user with the given ID
     * @throws UserNotFoundException if no user with the given ID exists
     */
    private Persona findUserById(String id) {
        for (Persona user : connections.keySet()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        throw new UserNotFoundException("User with ID " + id + " not found");
    }
}