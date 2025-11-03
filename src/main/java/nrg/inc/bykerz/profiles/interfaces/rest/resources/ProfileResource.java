package nrg.inc.bykerz.profiles.interfaces.rest.resources;

public record ProfileResource(
        Long id,
        String firstName,
        String lastName,
        String email,
        Long userId
) {
}