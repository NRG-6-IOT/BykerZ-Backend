package nrg.inc.bykerz.profiles.domain.model.commands;

public record CreateProfileCommand(
        String firstName,
        String lastName,
        String email,
        String photoUrl,
        Long userId
) {
}
