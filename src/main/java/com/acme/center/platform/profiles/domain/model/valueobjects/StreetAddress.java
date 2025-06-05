package com.acme.center.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record StreetAddress(
        String street,
        String number,
        String city,
        String postalCode,
        String country
) {

    public StreetAddress() {
        this(null, null, null, null, null);
    }

    public StreetAddress {
        if (street == null || street.isEmpty()) {
            throw new IllegalArgumentException("street cannot be null or empty");
        }
        if (number == null || number.isEmpty()) {
            throw new IllegalArgumentException("number cannot be null or empty");
        }
        if (city == null || city.isEmpty()) {
            throw new IllegalArgumentException("city cannot be null or empty");
        }
        if (postalCode == null || postalCode.isEmpty()) {
            throw new IllegalArgumentException("postalCode cannot be null or empty");
        }
        if (country == null || country.isEmpty()) {
            throw new IllegalArgumentException("country cannot be null or empty");
        }
    }

    public String getStreetAddress() {
        return "%s %s, %s, %s, %s".formatted(street, number, city, postalCode, country);
    }
}
