package com.acme.center.platform.profiles.interfaces.rest;

import com.acme.center.platform.profiles.domain.model.queries.GetAllProfilesQuery;
import com.acme.center.platform.profiles.domain.model.queries.GetProfileByIdQuery;
import com.acme.center.platform.profiles.domain.services.ProfileCommandService;
import com.acme.center.platform.profiles.domain.services.ProfileQueryService;
import com.acme.center.platform.profiles.interfaces.rest.resources.CreateProfileResource;
import com.acme.center.platform.profiles.interfaces.rest.resources.ProfileResource;
import com.acme.center.platform.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.acme.center.platform.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Available Profile Endpoints")
public class ProfilesController {

    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    ProfilesController(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Profile created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource) {
        var createProfileCommand = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var createdProfile = profile.get();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(createdProfile);
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }

    @GetMapping("/{profileId}")
    @Operation(summary = "Get profile by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile Found"),
            @ApiResponse(responseCode = "404", description = "Profile not Found")
    })
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        var profileEntity = profile.get();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profileEntity);
        return ResponseEntity.ok(profileResource);
    }

    @GetMapping
    @Operation(summary = "Get all profiles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile Found"),
            @ApiResponse(responseCode = "404", description = "Profile not Found")
    })
    public ResponseEntity<List<ProfileResource>> getAllProfiles() {
        var profiles = profileQueryService.handle(new GetAllProfilesQuery());
        if (profiles.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = profiles.stream()
                .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(profileResource);
    }

}
