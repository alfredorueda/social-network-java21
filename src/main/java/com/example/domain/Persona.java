package com.example.domain;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a user in the social network system.
 * <p>
 * This class contains essential information about a user including their
 * unique identifier, personal details, and registration information.
 * </p>
 * 
 * @author Example Author
 * @version 1.0
 */
public class Persona {
    private final String id;
    private final String name;
    private final LocalDate birthDate;
    private final String city;
    private final LocalDate registrationDate;

    /**
     * Constructs a new Persona with the specified details.
     *
     * @param id               the unique identifier of the user
     * @param name             the full name of the user
     * @param birthDate        the birth date of the user
     * @param city             the city where the user lives
     * @param registrationDate the date when the user registered in the system
     * @throws NullPointerException if any of the parameters is null
     */
    public Persona(String id, String name, LocalDate birthDate, String city, LocalDate registrationDate) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.birthDate = Objects.requireNonNull(birthDate, "Birth date cannot be null");
        this.city = Objects.requireNonNull(city, "City cannot be null");
        this.registrationDate = Objects.requireNonNull(registrationDate, "Registration date cannot be null");
    }

    /**
     * Returns the unique identifier of the user.
     *
     * @return the unique identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the full name of the user.
     *
     * @return the full name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the birth date of the user.
     *
     * @return the birth date
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Returns the city where the user lives.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the date when the user registered in the system.
     *
     * @return the registration date
     */
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The equality is based solely on the ID field, as it uniquely identifies a Persona.
     * </p>
     *
     * @param o the reference object with which to compare
     * @return true if this object is the same as the o argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(id, persona.id);
    }

    /**
     * Returns a hash code value for the object.
     * <p>
     * The hash code is based solely on the ID field to maintain consistency with equals.
     * </p>
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns a string representation of the user.
     *
     * @return a string representation of the user
     */
    @Override
    public String toString() {
        return "Persona{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", city='" + city + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}