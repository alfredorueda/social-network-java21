package com.example;

import com.example.domain.Persona;
import com.example.service.SocialNetwork;
import com.example.service.SocialNetworkImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Main demonstration class for the Social Network.
 * <p>
 * This class demonstrates the functionality of the social network implementation
 * by creating users, establishing connections, and displaying various network
 * properties such as friends, connection paths, and levels.
 * </p>
 */
public class Main {

    /**
     * Main method to demonstrate the social network functionality.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Social Network Demonstration");
        System.out.println("===========================");
        
        // Create a new social network
        SocialNetwork network = new SocialNetworkImpl();
        
        // Create and register users
        Persona alice = new Persona(
                "1", 
                "Alice Johnson", 
                LocalDate.of(1990, 5, 15), 
                "New York", 
                LocalDate.of(2023, 1, 10));
        
        Persona bob = new Persona(
                "2", 
                "Bob Smith", 
                LocalDate.of(1985, 8, 22), 
                "Los Angeles", 
                LocalDate.of(2023, 1, 15));
        
        Persona charlie = new Persona(
                "3", 
                "Charlie Brown", 
                LocalDate.of(1992, 3, 10), 
                "Chicago", 
                LocalDate.of(2023, 2, 5));
        
        Persona diana = new Persona(
                "4", 
                "Diana Prince", 
                LocalDate.of(1988, 7, 1), 
                "Washington", 
                LocalDate.of(2023, 2, 20));
        
        Persona edward = new Persona(
                "5", 
                "Edward Stark", 
                LocalDate.of(1995, 11, 30), 
                "Boston", 
                LocalDate.of(2023, 3, 1));
        
        // Register users
        System.out.println("\nRegistering users...");
        network.registerUser(alice);
        network.registerUser(bob);
        network.registerUser(charlie);
        network.registerUser(diana);
        network.registerUser(edward);
        
        // Connect users
        System.out.println("\nCreating connections...");
        network.connect("1", "2");  // Alice -> Bob
        network.connect("1", "3");  // Alice -> Charlie
        network.connect("2", "4");  // Bob -> Diana
        network.connect("3", "5");  // Charlie -> Edward
        
        // Display user connections
        System.out.println("\nFriends of Alice:");
        Set<Persona> aliceFriends = network.getFriends("1");
        aliceFriends.forEach(friend -> System.out.println("- " + friend.getName()));
        
        // Show connection path
        System.out.println("\nConnection path from Alice to Diana:");
        List<Persona> path = network.getConnectionPathBetween("1", "4");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).getName());
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
        
        // Show connection level
        System.out.println("\nConnection level between Alice and Diana: " 
                + network.getConnectionLevelBetween("1", "4"));
        
        // Show connection path between Alice and Edward
        System.out.println("\nConnection path from Alice to Edward:");
        path = network.getConnectionPathBetween("1", "5");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).getName());
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
        
        // Show connection level between Alice and Edward
        System.out.println("\nConnection level between Alice and Edward: " 
                + network.getConnectionLevelBetween("1", "5"));
        
        // Display users ordered by registration date
        System.out.println("\nUsers ordered by registration date:");
        Set<Persona> orderedUsers = network.getUsersOrderedByRegistration();
        orderedUsers.forEach(user -> System.out.println("- " + user.getName() 
                + " (Registered: " + user.getRegistrationDate() + ")"));
        
        // Demonstrate exception handling
        try {
            System.out.println("\nTrying to find connection path between Alice and a non-existent user...");
            network.getConnectionPathBetween("1", "6");
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
        
        try {
            System.out.println("\nTrying to create a connection that already exists...");
            network.connect("1", "2"); // Alice and Bob are already connected
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}