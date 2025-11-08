package nrg.inc.bykerz.assignments.domain.services;

import nrg.inc.bykerz.assignments.domain.model.aggregates.Mechanic;
import nrg.inc.bykerz.assignments.domain.model.queries.GetMechanicByIdQuery;

import java.util.Optional;

public interface MechanicQueryService {
    Optional<Mechanic> handle(GetMechanicByIdQuery query);
}
