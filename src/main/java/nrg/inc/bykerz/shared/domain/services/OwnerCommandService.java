package nrg.inc.bykerz.shared.domain.services;

import nrg.inc.bykerz.shared.domain.model.aggregates.Owner;
import nrg.inc.bykerz.shared.domain.model.commands.CreateOwnerCommand;

import java.util.Optional;

public interface OwnerCommandService {
    Optional<Owner> handle(CreateOwnerCommand command);
}
