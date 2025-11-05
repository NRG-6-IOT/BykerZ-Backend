package nrg.inc.bykerz.shared.domain.services;

import nrg.inc.bykerz.shared.domain.model.aggregates.Owner;
import nrg.inc.bykerz.shared.domain.model.queries.GetOwnerByIdQuery;

import java.util.Optional;

public interface OwnerQueryService {
    Optional<Owner> handle(GetOwnerByIdQuery query);
}
