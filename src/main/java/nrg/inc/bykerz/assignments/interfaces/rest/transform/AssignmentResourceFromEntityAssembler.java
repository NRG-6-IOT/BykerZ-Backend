package nrg.inc.bykerz.assignments.interfaces.rest.transform;

import nrg.inc.bykerz.assignments.domain.model.aggregates.Assignment;
import nrg.inc.bykerz.assignments.interfaces.rest.resources.AssignmentResource;
import nrg.inc.bykerz.shared.domain.model.aggregates.Mechanic;
import nrg.inc.bykerz.shared.interfaces.rest.transform.MechanicResourceFromEntityAssembler;

public class AssignmentResourceFromEntityAssembler {
    public static AssignmentResource toResourceFromEntity(Assignment entity, Mechanic mechanic){
        return new AssignmentResource(
                entity.getId(),
                entity.getVehicleId(),
                MechanicResourceFromEntityAssembler.toResourceFromEntity(mechanic),
                entity.getType().toString(),
                entity.getStatus().toString(),
                entity.getCreatedAt().toString()
        );
    }
}
