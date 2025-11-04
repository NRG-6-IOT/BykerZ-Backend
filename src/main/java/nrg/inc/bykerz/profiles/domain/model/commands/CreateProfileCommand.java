package nrg.inc.bykerz.profiles.domain.model.commands;

public record CreateProfileCommand(
        Long userId,
        String firstName,
        String lastName,
        String email,
        Long userId
) {
}
