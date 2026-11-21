package com.hospitalsystem.model;

/**
 * Member 1 - Base Person Model.
 *
 * It is abstract because we only instantiate a Patient or a Doctor (ABSTRACTION).
 * Variables are private and accessed via getters (ENCAPSULATION).
 */
public abstract class Person {

    private final String personId;
    private final String personName;
    private final int personAge;

    public Person(String personId, String personName, int personAge) {
        this.personId = personId;
        this.personName = personName;
        this.personAge = personAge;
    }

    public String getId() {
        return personId;
    }

    public String getName() {
        return personName;
    }

    public int getAge() {
        return personAge;
    }

    // every child class defines its own role
    public abstract String getRole();

    // overridden in child classes (POLYMORPHISM)
    public String getDetails() {
        return getRole() + " | ID: " + personId + " | Name: " + personName + " | Age: " + personAge;
    }
}
