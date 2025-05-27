package com.example.service;

import com.example.domain.Persona;
import com.example.exceptions.ConnectionAlreadyExistsException;
import com.example.exceptions.NoPathException;
import com.example.exceptions.UserAlreadyExistsException;
import com.example.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link SocialNetworkImpl}.
 * <p>
 * This test class covers all functionality of the SocialNetwork implementation,
 * including happy paths and failure scenarios. It tests the behavior of each method
 * with various inputs and validates that exceptions are thrown correctly.
 * </p>
 */
@DisplayName("SocialNetworkImpl Tests")
class SocialNetworkImplTest {

    private SocialNetwork network;
    private Persona alice;
    private Persona bob;
    private Persona charlie;
    private Persona diana;
    private Persona edward;
    private Persona frank;
    private Persona grace;
    private Persona hannah;
    private Persona ian;
    private Persona julia;
    private Persona kevin;
    private Persona linda;
    private Persona mike;
    private Persona nancy;
    private Persona oscar;
    private Persona patricia;
    private Persona quentin;
    private Persona rachel;
    private Persona steve;
    private Persona tina;

    @BeforeEach
    void setUp() {
        network = new SocialNetworkImpl();
        
        // Create users with varying registration dates
        alice = new Persona("1", "Alice Johnson", LocalDate.of(1990, 5, 15), "New York", LocalDate.of(2023, 1, 10));
        bob = new Persona("2", "Bob Smith", LocalDate.of(1985, 8, 22), "Los Angeles", LocalDate.of(2023, 1, 15));
        charlie = new Persona("3", "Charlie Brown", LocalDate.of(1992, 3, 10), "Chicago", LocalDate.of(2023, 2, 5));
        diana = new Persona("4", "Diana Prince", LocalDate.of(1988, 7, 1), "Washington", LocalDate.of(2023, 2, 20));
        edward = new Persona("5", "Edward Stark", LocalDate.of(1995, 11, 30), "Boston", LocalDate.of(2023, 3, 1));
        frank = new Persona("6", "Frank Miller", LocalDate.of(1982, 6, 5), "San Francisco", LocalDate.of(2023, 3, 15));
        grace = new Persona("7", "Grace Lee", LocalDate.of(1991, 9, 18), "Seattle", LocalDate.of(2023, 4, 1));
        hannah = new Persona("8", "Hannah Montana", LocalDate.of(1987, 4, 23), "Miami", LocalDate.of(2023, 4, 10));
        ian = new Persona("9", "Ian Wright", LocalDate.of(1994, 2, 14), "Denver", LocalDate.of(2023, 5, 5));
        julia = new Persona("10", "Julia Roberts", LocalDate.of(1989, 10, 8), "Phoenix", LocalDate.of(2023, 5, 20));
        kevin = new Persona("11", "Kevin Hart", LocalDate.of(1986, 7, 17), "Philadelphia", LocalDate.of(2023, 6, 1));
        linda = new Persona("12", "Linda Kim", LocalDate.of(1993, 12, 3), "Houston", LocalDate.of(2023, 6, 15));
        mike = new Persona("13", "Mike Tyson", LocalDate.of(1984, 5, 27), "Detroit", LocalDate.of(2023, 7, 1));
        nancy = new Persona("14", "Nancy Chen", LocalDate.of(1996, 8, 12), "Portland", LocalDate.of(2023, 7, 15));
        oscar = new Persona("15", "Oscar Wilde", LocalDate.of(1981, 3, 21), "Austin", LocalDate.of(2023, 8, 1));
        patricia = new Persona("16", "Patricia Lopez", LocalDate.of(1990, 6, 9), "San Diego", LocalDate.of(2023, 8, 15));
        quentin = new Persona("17", "Quentin Adams", LocalDate.of(1988, 11, 4), "Nashville", LocalDate.of(2023, 9, 1));
        rachel = new Persona("18", "Rachel Green", LocalDate.of(1992, 1, 29), "New Orleans", LocalDate.of(2023, 9, 15));
        steve = new Persona("19", "Steve Rogers", LocalDate.of(1985, 9, 13), "Minneapolis", LocalDate.of(2023, 10, 1));
        tina = new Persona("20", "Tina Turner", LocalDate.of(1991, 4, 7), "Las Vegas", LocalDate.of(2023, 10, 15));
    }

    @Nested
    @DisplayName("User Registration Tests")
    class UserRegistrationTests {

        @Test
        @DisplayName("Should register a user successfully")
        void shouldRegisterUserSuccessfully() {
            network.registerUser(alice);
            Set<Persona> users = network.getUsersOrderedByRegistration();
            
            assertEquals(1, users.size());
            assertTrue(users.contains(alice));
        }

        @Test
        @DisplayName("Should register multiple users successfully")
        void shouldRegisterMultipleUsersSuccessfully() {
            // Register all 20 users
            registerAllUsers();
            
            Set<Persona> users = network.getUsersOrderedByRegistration();
            assertEquals(20, users.size());
        }

        @Test
        @DisplayName("Should throw UserAlreadyExistsException when registering a user with an existing ID")
        void shouldThrowExceptionWhenRegisteringExistingUser() {
            network.registerUser(alice);
            
            // Create a new user with the same ID but different other details
            Persona aliceDuplicate = new Persona("1", "Alice Smith", LocalDate.of(1991, 6, 20), "Boston", LocalDate.of(2023, 2, 15));
            
            assertThrows(UserAlreadyExistsException.class, () -> network.registerUser(aliceDuplicate));
        }

        @Test
        @DisplayName("Should throw NullPointerException when registering a null user")
        void shouldThrowExceptionWhenRegisteringNullUser() {
            assertThrows(NullPointerException.class, () -> network.registerUser(null));
        }
    }

    @Nested
    @DisplayName("Connection Tests")
    class ConnectionTests {

        @BeforeEach
        void setUpConnections() {
            registerAllUsers();
        }

        @Test
        @DisplayName("Should connect two users successfully")
        void shouldConnectUsersSuccessfully() {
            network.connect("1", "2"); // Connect Alice and Bob
            
            Set<Persona> aliceFriends = network.getFriends("1");
            Set<Persona> bobFriends = network.getFriends("2");
            
            assertEquals(1, aliceFriends.size());
            assertEquals(1, bobFriends.size());
            assertTrue(aliceFriends.contains(bob));
            assertTrue(bobFriends.contains(alice));
        }

        @Test
        @DisplayName("Should connect multiple users successfully")
        void shouldConnectMultipleUsersSuccessfully() {
            // Create a network with multiple connections
            createTestNetwork();
            
            // Verify Alice's connections
            Set<Persona> aliceFriends = network.getFriends("1");
            assertEquals(3, aliceFriends.size());
            assertTrue(aliceFriends.contains(bob));
            assertTrue(aliceFriends.contains(charlie));
            assertTrue(aliceFriends.contains(diana));
        }

        @Test
        @DisplayName("Should throw UserNotFoundException when connecting with non-existent user ID")
        void shouldThrowExceptionWhenConnectingNonExistentUser() {
            assertThrows(UserNotFoundException.class, () -> network.connect("1", "999"));
            assertThrows(UserNotFoundException.class, () -> network.connect("999", "1"));
        }

        @Test
        @DisplayName("Should throw ConnectionAlreadyExistsException when connection already exists")
        void shouldThrowExceptionWhenConnectionAlreadyExists() {
            network.connect("1", "2");
            assertThrows(ConnectionAlreadyExistsException.class, () -> network.connect("1", "2"));
            assertThrows(ConnectionAlreadyExistsException.class, () -> network.connect("2", "1"));
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when connecting a user to themselves")
        void shouldThrowExceptionWhenConnectingUserToThemselves() {
            assertThrows(IllegalArgumentException.class, () -> network.connect("1", "1"));
        }
    }

    @Nested
    @DisplayName("Get Friends Tests")
    class GetFriendsTests {

        @BeforeEach
        void setUpConnections() {
            registerAllUsers();
            createTestNetwork();
        }

        @Test
        @DisplayName("Should return the correct set of friends")
        void shouldReturnCorrectFriends() {
            Set<Persona> bobFriends = network.getFriends("2");
            
            assertEquals(3, bobFriends.size());
            assertTrue(bobFriends.contains(alice));
            assertTrue(bobFriends.contains(charlie));
            assertTrue(bobFriends.contains(edward));
        }

        @Test
        @DisplayName("Should return an empty set for a user with no friends")
        void shouldReturnEmptySetForUserWithNoFriends() {
            Set<Persona> tinaFriends = network.getFriends("20");
            assertTrue(tinaFriends.isEmpty());
        }

        @Test
        @DisplayName("Should throw UserNotFoundException for a non-existent user ID")
        void shouldThrowExceptionForNonExistentUser() {
            assertThrows(UserNotFoundException.class, () -> network.getFriends("999"));
        }
    }

    @Nested
    @DisplayName("Path Finding Tests")
    class PathFindingTests {

        @BeforeEach
        void setUpConnections() {
            registerAllUsers();
            createTestNetwork();
        }

        @Test
        @DisplayName("Should find the correct path between directly connected users")
        void shouldFindPathBetweenDirectlyConnectedUsers() {
            List<Persona> path = network.getConnectionPathBetween("1", "2");
            
            assertEquals(2, path.size());
            assertEquals(alice, path.get(0));
            assertEquals(bob, path.get(1));
        }

        @Test
        @DisplayName("Should find the correct path between indirectly connected users")
        void shouldFindPathBetweenIndirectlyConnectedUsers() {
            List<Persona> path = network.getConnectionPathBetween("1", "6");
            
            assertEquals(3, path.size());
            assertEquals(alice, path.get(0));
            assertEquals(diana, path.get(1));
            assertEquals(frank, path.get(2));
        }

        @Test
        @DisplayName("Should find the correct long path in a complex network")
        void shouldFindLongPathInComplexNetwork() {
            List<Persona> path = network.getConnectionPathBetween("1", "16");
            
            assertEquals(4, path.size());
            assertEquals(alice, path.get(0));
            assertEquals(diana, path.get(1));
            assertEquals(oscar, path.get(2));
            assertEquals(patricia, path.get(3));
        }

        @Test
        @DisplayName("Should return a single-element path for the same user")
        void shouldReturnSingleElementPathForSameUser() {
            List<Persona> path = network.getConnectionPathBetween("1", "1");
            
            assertEquals(1, path.size());
            assertEquals(alice, path.get(0));
        }

        @Test
        @DisplayName("Should throw NoPathException when no path exists")
        void shouldThrowExceptionWhenNoPathExists() {
            assertThrows(NoPathException.class, () -> network.getConnectionPathBetween("1", "20"));
        }

        @Test
        @DisplayName("Should throw UserNotFoundException for a non-existent user ID")
        void shouldThrowExceptionForNonExistentUser() {
            assertThrows(UserNotFoundException.class, () -> network.getConnectionPathBetween("1", "999"));
            assertThrows(UserNotFoundException.class, () -> network.getConnectionPathBetween("999", "1"));
        }
    }

    @Nested
    @DisplayName("Connection Level Tests")
    class ConnectionLevelTests {

        @BeforeEach
        void setUpConnections() {
            registerAllUsers();
            createTestNetwork();
        }

        @Test
        @DisplayName("Should return level 1 for directly connected users")
        void shouldReturnLevelOneForDirectlyConnectedUsers() {
            int level = network.getConnectionLevelBetween("1", "2");
            assertEquals(1, level);
        }

        @Test
        @DisplayName("Should return level 2 for users connected through one intermediary")
        void shouldReturnLevelTwoForUsersWithOneIntermediary() {
            int level = network.getConnectionLevelBetween("1", "6");
            assertEquals(2, level);
        }

        @Test
        @DisplayName("Should return level 0 for the same user")
        void shouldReturnLevelZeroForSameUser() {
            int level = network.getConnectionLevelBetween("1", "1");
            assertEquals(0, level);
        }

        @Test
        @DisplayName("Should throw NoPathException when no path exists")
        void shouldThrowExceptionWhenNoPathExists() {
            assertThrows(NoPathException.class, () -> network.getConnectionLevelBetween("1", "20"));
        }
    }

    @Nested
    @DisplayName("Users Ordered By Registration Tests")
    class UsersOrderedByRegistrationTests {

        @Test
        @DisplayName("Should return users in the order they were registered")
        void shouldReturnUsersInRegistrationOrder() {
            registerAllUsersInOrder();
            
            Set<Persona> orderedUsers = network.getUsersOrderedByRegistration();
            
            // Convert to array for easier assertion
            Persona[] usersArray = orderedUsers.toArray(new Persona[0]);
            
            // Check that users are in the order they were registered
            assertEquals(alice, usersArray[0]);
            assertEquals(bob, usersArray[1]);
            assertEquals(charlie, usersArray[2]);
            assertEquals(diana, usersArray[3]);
            assertEquals(edward, usersArray[4]);
            // ... and so on
        }

        @Test
        @DisplayName("Should return an empty set when no users are registered")
        void shouldReturnEmptySetWhenNoUsers() {
            Set<Persona> orderedUsers = network.getUsersOrderedByRegistration();
            assertTrue(orderedUsers.isEmpty());
        }
    }

    @Nested
    @DisplayName("TreeSet Ordering Tests")
    class TreeSetOrderingTests {

        @Test
        @DisplayName("Should order connections by name and then by ID")
        void shouldOrderConnectionsByNameAndId() {
            network.registerUser(alice);
            network.registerUser(bob);
            
            // Create users with the same name but different IDs
            Persona bob1 = new Persona("21", "Bob Smith", LocalDate.of(1986, 9, 23), "Dallas", LocalDate.of(2023, 11, 1));
            Persona bob2 = new Persona("22", "Bob Smith", LocalDate.of(1987, 10, 24), "Atlanta", LocalDate.of(2023, 11, 5));
            
            network.registerUser(bob1);
            network.registerUser(bob2);
            
            // Connect Alice to all Bobs
            network.connect("1", "2");  // Alice -> Bob
            network.connect("1", "21"); // Alice -> Bob1
            network.connect("1", "22"); // Alice -> Bob2
            
            Set<Persona> aliceFriends = network.getFriends("1");
            
            // Convert to array for easier assertion
            Persona[] friendsArray = aliceFriends.toArray(new Persona[0]);
            
            // Verify that the Bobs are ordered by name (alphabetically) and then by ID
            assertEquals("Bob Smith", friendsArray[0].getName());
            assertEquals("Bob Smith", friendsArray[1].getName());
            assertEquals("Bob Smith", friendsArray[2].getName());
            
            // The IDs should be in order
            assertEquals("2", friendsArray[0].getId());
            assertEquals("21", friendsArray[1].getId());
            assertEquals("22", friendsArray[2].getId());
        }
    }

    // Helper methods

    /**
     * Registers all 20 users in the network.
     */
    private void registerAllUsers() {
        network.registerUser(alice);
        network.registerUser(bob);
        network.registerUser(charlie);
        network.registerUser(diana);
        network.registerUser(edward);
        network.registerUser(frank);
        network.registerUser(grace);
        network.registerUser(hannah);
        network.registerUser(ian);
        network.registerUser(julia);
        network.registerUser(kevin);
        network.registerUser(linda);
        network.registerUser(mike);
        network.registerUser(nancy);
        network.registerUser(oscar);
        network.registerUser(patricia);
        network.registerUser(quentin);
        network.registerUser(rachel);
        network.registerUser(steve);
        network.registerUser(tina);
    }

    /**
     * Registers all users in the specific order to test registration ordering.
     */
    private void registerAllUsersInOrder() {
        network.registerUser(alice);
        network.registerUser(bob);
        network.registerUser(charlie);
        network.registerUser(diana);
        network.registerUser(edward);
        network.registerUser(frank);
        network.registerUser(grace);
        network.registerUser(hannah);
        network.registerUser(ian);
        network.registerUser(julia);
        network.registerUser(kevin);
        network.registerUser(linda);
        network.registerUser(mike);
        network.registerUser(nancy);
        network.registerUser(oscar);
        network.registerUser(patricia);
        network.registerUser(quentin);
        network.registerUser(rachel);
        network.registerUser(steve);
        network.registerUser(tina);
    }

    /**
     * Creates a test network with various connections between users.
     */
    private void createTestNetwork() {
        // Alice's connections
        network.connect("1", "2");  // Alice -> Bob
        network.connect("1", "3");  // Alice -> Charlie
        network.connect("1", "4");  // Alice -> Diana
        
        // Bob's connections (in addition to Alice)
        network.connect("2", "3");  // Bob -> Charlie
        network.connect("2", "5");  // Bob -> Edward
        
        // Diana's connections (in addition to Alice)
        network.connect("4", "6");  // Diana -> Frank
        network.connect("4", "7");  // Diana -> Grace
        
        // Create a chain of connections
        network.connect("7", "8");  // Grace -> Hannah
        network.connect("8", "9");  // Hannah -> Ian
        network.connect("9", "10"); // Ian -> Julia
        
        // Create another branch
        network.connect("6", "11"); // Frank -> Kevin
        network.connect("11", "12"); // Kevin -> Linda
        network.connect("12", "13"); // Linda -> Mike
        
        // Create a shorter path to Patricia through Diana
        network.connect("4", "15"); // Diana -> Oscar
        network.connect("15", "16"); // Oscar -> Patricia
        
        // Original longer path to Patricia
        network.connect("10", "14"); // Julia -> Nancy
        network.connect("14", "15"); // Nancy -> Oscar
        
        // Create a disconnected component
        network.connect("17", "18"); // Quentin -> Rachel
        network.connect("18", "19"); // Rachel -> Steve
        
        // Leave Tina (20) disconnected
    }
}