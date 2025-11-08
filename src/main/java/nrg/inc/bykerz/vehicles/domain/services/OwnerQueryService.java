package nrg.inc.bykerz.vehicles.domain.services;

import nrg.inc.bykerz.vehicles.domain.model.aggregates.Owner;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetOwnerByIdQuery;

import java.util.Optional;

public interface OwnerQueryService {
    Optional<Owner> handle(GetOwnerByIdQuery query);
}
