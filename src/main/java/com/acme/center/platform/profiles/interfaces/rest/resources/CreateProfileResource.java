package com.acme.center.platform.profiles.interfaces.rest.resources;

public record CreateProfileResource(
        String firstName,
        String lastName,
        String email,
        String street,
        String number,
        String city,
        String postalCode,
        String country
) {

    public CreateProfileResource {
        if (firstName == null || firstName.isBlank()) throw new IllegalArgumentException("firstName cannot be blank");
        if (lastName == null || lastName.isBlank()) throw new IllegalArgumentException("lastName cannot be blank");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("email cannot be blank");
        if (street == null || street.isBlank()) throw new IllegalArgumentException("street cannot be blank");
        if (number == null || number.isBlank()) throw new IllegalArgumentException("number cannot be blank");
        if (city == null || city.isBlank()) throw new IllegalArgumentException("city cannot be blank");
        if (postalCode == null || postalCode.isBlank()) throw new IllegalArgumentException("postalCode cannot be blank");
        if (country == null || country.isBlank()) throw new IllegalArgumentException("country cannot be blank");
    }

}
