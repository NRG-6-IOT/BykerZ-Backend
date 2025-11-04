package nrg.inc.bykerz.profiles.interfaces.rest.resources;

public record CreateProfileResource(
        Long userId,
        String firstName,
        String lastName,
        String email
) {
    public CreateProfileResource {
        if (firstName == null || firstName.isBlank()) throw new IllegalArgumentException("First name is required");
        if (lastName == null || lastName.isBlank()) throw new IllegalArgumentException("Last name is required");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email is required");
    }
}
