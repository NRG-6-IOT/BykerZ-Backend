package nrg.inc.bykerz.vehicles.interfaces.rest.transform;

import nrg.inc.bykerz.iam.application.external.ExternalProfileService;
import nrg.inc.bykerz.profiles.domain.model.queries.GetProfileByIdQuery;
import nrg.inc.bykerz.profiles.domain.services.ProfileQueryService;
import nrg.inc.bykerz.vehicles.domain.model.aggregates.Owner;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.OwnerResource;

public class OwnerResourceFromEntityAssembler {

    public static OwnerResource toResourceFromEntity(Owner entity) {
        return new OwnerResource(
                entity.getId(),
                entity.getProfile().getCompleteName()
        );
    }
}
