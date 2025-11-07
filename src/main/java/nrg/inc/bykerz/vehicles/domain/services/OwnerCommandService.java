package nrg.inc.bykerz.vehicles.domain.services;

import nrg.inc.bykerz.vehicles.domain.model.aggregates.Owner;
import nrg.inc.bykerz.vehicles.domain.model.commands.CreateOwnerCommand;

import java.util.Optional;

public interface OwnerCommandService {
    Optional<Owner> handle(CreateOwnerCommand command);

    Optional<Owner> handle();
}
