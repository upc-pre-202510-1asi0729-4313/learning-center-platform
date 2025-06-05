package com.acme.center.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record PersonName(String firstName, String lastName) {

    PersonName() {
        this(null, null);
    }

    public String getFullName() {
        return "%s %s".formatted(firstName, lastName);
    }

    public PersonName {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("firstName cannot be null or empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("lastName cannot be null or empty");
        }
    }
}
