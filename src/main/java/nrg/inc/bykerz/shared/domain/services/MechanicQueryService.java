package nrg.inc.bykerz.shared.domain.services;

import nrg.inc.bykerz.shared.domain.model.aggregates.Mechanic;
import nrg.inc.bykerz.shared.domain.model.queries.GetMechanicByCodeQuery;
import nrg.inc.bykerz.shared.domain.model.queries.GetMechanicByIdQuery;

import java.util.Optional;

public interface MechanicQueryService {
    Optional<Mechanic> handle(GetMechanicByIdQuery query);
    Optional<Mechanic> handle(GetMechanicByCodeQuery query);
}
