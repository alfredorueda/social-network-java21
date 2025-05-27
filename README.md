# Java 21 Collections Framework Demo

![Java Version](https://img.shields.io/badge/Java-21-orange)
![License](https://img.shields.io/badge/License-MIT-blue)
![Build](https://img.shields.io/badge/Build-Maven-green)

A comprehensive educational resource demonstrating advanced usage patterns and subtle behaviors of the Java Collections Framework. This project implements a social network application that showcases various collection types and algorithms, designed specifically for those preparing for the Oracle Certified Professional (OCP) Java 21 certification.

## 📋 Table of Contents

- [Overview](#overview)
- [Key Features](#key-features)
- [Collection Types Demonstrated](#collection-types-demonstrated)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Advanced Concepts Demonstrated](#advanced-concepts-demonstrated)
- [Testing](#testing)
- [How to Use This Repository](#how-to-use-this-repository)
- [Contributing](#contributing)
- [License](#license)

## 🔍 Overview

This repository contains a social network implementation that demonstrates real-world usage of Java's Collections Framework. The application models users (Persona) and their connections, allowing operations such as:

- User registration
- Creating connections between users
- Finding connection paths (using BFS algorithm)
- Determining connection levels between users
- Retrieving users ordered by registration date

Each feature demonstrates specific collection behaviors and performance characteristics relevant to the OCP Java 21 certification.

## ✨ Key Features

- **Graph-based Social Network**: Demonstrates adjacency list representation using maps and sets
- **Path Finding Algorithm**: Implements BFS to find shortest paths between users
- **Custom Ordering**: Shows how to implement custom comparators for collection ordering
- **Exception Handling**: Proper exception design and implementation
- **Immutable Views**: Returns unmodifiable collections to preserve encapsulation
- **Time Complexity Analysis**: Each method includes runtime complexity annotations
- **Comprehensive Testing**: Thorough test suite covering all functionality

## 🧰 Collection Types Demonstrated

| Collection Type | Usage in Project | Key Learning Points |
|-----------------|------------------|---------------------|
| `LinkedHashMap` | Stores users with insertion-order preservation | Order preservation, O(1) lookup |
| `TreeSet` | Stores user connections with custom ordering | Custom comparators, sorted collections |
| `HashSet` | Used in BFS algorithm for tracking visited nodes | Fast membership testing |
| `Queue<Persona>` | Implemented with `LinkedList` in BFS algorithm | FIFO operations |
| `List<Persona>` | Used for path representation | Ordered collection with indexes |
| `HashMap` | Used for path reconstruction | Key-value associations |
| `Collections.unmodifiableSet` | Returns read-only views of collections | Defensive programming |

## 🚀 Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6.3 or higher

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/java21-collections-demos.git
   cd java21-collections-demos
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the tests:
   ```bash
   mvn test
   ```

## 📁 Project Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── example/
│               ├── Main.java                         # Application entry point
│               ├── domain/
│               │   └── Persona.java                  # User entity
│               ├── exceptions/
│               │   ├── ConnectionAlreadyExistsException.java
│               │   ├── NoPathException.java
│               │   ├── UserAlreadyExistsException.java
│               │   └── UserNotFoundException.java
│               └── service/
│                   ├── SocialNetwork.java            # Service interface
│                   └── SocialNetworkImpl.java        # Implementation with collections
└── test/
    └── java/
        └── com/
            └── example/
                └── service/
                    └── SocialNetworkImplTest.java    # Comprehensive test suite
```

## 🧠 Advanced Concepts Demonstrated

### 1. Collection Selection Tradeoffs

The implementation shows when to choose:
- `LinkedHashMap` over `HashMap` (for insertion order preservation)
- `TreeSet` over `HashSet` (for maintaining sorted order)
- `Queue` over `List` for BFS implementation

### 2. Complexity Analysis

Each method includes detailed time complexity analysis in Javadoc comments:
- Registration operations: O(1)
- Connection operations: O(log n)
- Path finding: O(V + E) where V is vertices and E is edges

### 3. Graph Algorithms

The project implements:
- Breadth-First Search (BFS) for finding shortest paths
- Path reconstruction techniques
- Connection level determination

### 4. Iterator and Comparator Behaviors

- Custom comparator for `TreeSet` ordering users by name then ID
- Using collections that maintain specific iteration order

### 5. Defensive Programming

- Immutable collection views using `Collections.unmodifiableSet()`
- Proper exception handling with custom exception types
- Null checking with `Objects.requireNonNull()`

## 🧪 Testing

The test suite covers:

- **User Registration**: Adding users and handling duplicates
- **Connection Management**: Creating connections between users
- **Friend Retrieval**: Getting connected users
- **Path Finding**: Finding shortest paths between users
- **Connection Levels**: Determining degrees of separation
- **Registration Order**: Verifying order preservation
- **Custom Ordering**: Testing comparator-based ordering

Run the tests with:
```bash
mvn test
```

## 📝 How to Use This Repository

1. **Study the Implementation**:
   - Review the `SocialNetwork` interface to understand the contract
   - Examine `SocialNetworkImpl` to see collection usage patterns
   - Look at the test cases to understand expected behaviors

2. **For OCP Java 21 Preparation**:
   - Focus on the Javadoc comments explaining time complexity
   - Understand the collection selection tradeoffs
   - Study the custom comparator implementation
   - Review the exception handling patterns

3. **Extend the Project**:
   - Add new features like user recommendations
   - Implement different graph algorithms
   - Create alternative implementations using different collection types

## 🤝 Contributing

Contributions are welcome! Here's how you can contribute:

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/new-feature`
3. Commit your changes: `git commit -am 'Add new feature'`
4. Push to the branch: `git push origin feature/new-feature`
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

Created with ❤️ for Java developers preparing for the OCP Java 21 certification.