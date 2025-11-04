package nrg.inc.bykerz.shared.domain.services;

import nrg.inc.bykerz.shared.domain.model.aggregates.Mechanic;
import nrg.inc.bykerz.shared.domain.model.commands.CreateMechanicCommand;

import java.util.Optional;

public interface MechanicCommandService {
    Optional<Mechanic> handle(CreateMechanicCommand command);
}
