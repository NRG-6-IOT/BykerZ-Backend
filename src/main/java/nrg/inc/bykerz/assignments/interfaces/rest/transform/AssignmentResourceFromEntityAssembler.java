package nrg.inc.bykerz.assignments.interfaces.rest.transform;

import nrg.inc.bykerz.assignments.domain.model.aggregates.Assignment;
import nrg.inc.bykerz.assignments.interfaces.rest.resources.AssignmentResource;
import nrg.inc.bykerz.assignments.domain.model.aggregates.Mechanic;
import nrg.inc.bykerz.vehicles.domain.model.aggregates.Owner;
import nrg.inc.bykerz.vehicles.interfaces.rest.transform.OwnerResourceFromEntityAssembler;

public class AssignmentResourceFromEntityAssembler {
    public static AssignmentResource toResourceFromEntity(Assignment entity, Mechanic mechanic, Owner owner) {
        return new AssignmentResource(
                entity.getId(),
                owner == null ? null : OwnerResourceFromEntityAssembler.toResourceFromEntity(owner),
                MechanicResourceFromEntityAssembler.toResourceFromEntity(mechanic),
                entity.getType().toString(),
                entity.getStatus().toString(),
                entity.getAssignmentCode().code().toString(),
                entity.getCreatedAt().toString()
        );
    }
}
