package nrg.inc.bykerz.assignments.interfaces.rest.transform;

import nrg.inc.bykerz.assignments.domain.model.aggregates.Assignment;
import nrg.inc.bykerz.assignments.interfaces.rest.resources.AssignmentResource;
import nrg.inc.bykerz.shared.domain.model.aggregates.Mechanic;
import nrg.inc.bykerz.shared.interfaces.rest.transform.MechanicResourceFromEntityAssembler;
import nrg.inc.bykerz.vehicles.domain.model.entities.Vehicle;
import nrg.inc.bykerz.vehicles.interfaces.rest.transform.VehicleResourceFromEntityAssembler;

public class AssignmentResourceFromEntityAssembler {
    public static AssignmentResource toResourceFromEntity(Assignment entity, Mechanic mechanic, Vehicle vehicle) {
        return new AssignmentResource(
                entity.getId(),
                VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle),
                MechanicResourceFromEntityAssembler.toResourceFromEntity(mechanic),
                entity.getType().toString(),
                entity.getStatus().toString(),
                entity.getCreatedAt().toString()
        );
    }
}
