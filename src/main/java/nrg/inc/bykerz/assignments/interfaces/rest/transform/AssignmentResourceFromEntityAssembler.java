package nrg.inc.bykerz.assignments.interfaces.rest.transform;

import nrg.inc.bykerz.assignments.domain.model.aggregates.Assignment;
import nrg.inc.bykerz.assignments.interfaces.rest.resources.AssignmentResource;

public class AssignmentResourceFromEntityAssembler {
    public static AssignmentResource toResourceFromEntity(Assignment entity){
        return new AssignmentResource(
                entity.getId(),
                entity.getVehicleId(),
                entity.getMechanicId(),
                entity.getType().toString(),
                entity.getStatus().toString(),
                entity.getCreatedAt().toString()
        );
    }
}
