package nrg.inc.bykerz.profiles.domain.model.queries;

import nrg.inc.bykerz.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}
