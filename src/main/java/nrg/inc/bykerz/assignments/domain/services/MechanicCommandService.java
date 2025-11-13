package nrg.inc.bykerz.assignments.domain.services;

import nrg.inc.bykerz.assignments.domain.model.aggregates.Mechanic;
import nrg.inc.bykerz.assignments.domain.model.commands.CreateMechanicCommand;

import java.util.Optional;

public interface MechanicCommandService {
    Optional<Mechanic> handle(CreateMechanicCommand command);
}
