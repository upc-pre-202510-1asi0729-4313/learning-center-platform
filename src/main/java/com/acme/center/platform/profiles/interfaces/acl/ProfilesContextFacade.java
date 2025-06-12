package com.acme.center.platform.profiles.interfaces.acl;

public interface ProfilesContextFacade {

    Long createProfile(
            String firstName,
            String lastName,
            String email,
            String street,
            String number,
            String city,
            String postalCode,
            String country);

    Long fetchProfileIdByEmail(String email);

}
