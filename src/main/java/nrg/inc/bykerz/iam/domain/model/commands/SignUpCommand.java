package nrg.inc.bykerz.iam.domain.model.commands;

import nrg.inc.bykerz.iam.domain.model.valueobjects.Roles;

import java.util.List;
import java.util.Set;

public record SignUpCommand(
        String firstName,
        String lastName,
        String username,
        String email,
        String photoUrl,
        String password,
        List<Roles> roles
) {
    public SignUpCommand {
        if (firstName==null||firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if (lastName==null||lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        if (username==null||username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (email==null||email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (photoUrl==null||photoUrl.isBlank()) {
            throw new IllegalArgumentException("Photo URL cannot be empty");
        }
        if (password==null||password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (roles==null||roles.isEmpty()) {
            throw new IllegalArgumentException("Roles cannot be empty");
        }
    }
}
