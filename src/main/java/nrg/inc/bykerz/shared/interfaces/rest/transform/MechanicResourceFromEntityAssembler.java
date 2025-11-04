package nrg.inc.bykerz.shared.interfaces.rest.transform;

import nrg.inc.bykerz.shared.domain.model.aggregates.Mechanic;
import nrg.inc.bykerz.shared.interfaces.rest.resources.MechanicResource;

public class MechanicResourceFromEntityAssembler {
    public static MechanicResource toResourceFromEntity(Mechanic entity) {
        return new MechanicResource(
                entity.getId(),
                entity.getName(),
                entity.getCode().code()
        );
    }
}
