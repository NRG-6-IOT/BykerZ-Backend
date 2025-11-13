package nrg.inc.bykerz.vehicles.domain.services;

import nrg.inc.bykerz.vehicles.domain.model.aggregates.Owner;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetAllOwnersQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetOwnerByIdQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetOwnerByVehicleIdQuery;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface OwnerQueryService {
    Optional<Owner> handle(GetOwnerByIdQuery query);
    Optional<Owner> handle(GetOwnerByVehicleIdQuery query);
    List<Owner> handle(GetAllOwnersQuery query);
}
