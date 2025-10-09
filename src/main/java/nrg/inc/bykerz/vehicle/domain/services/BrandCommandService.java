package nrg.inc.bykerz.vehicle.domain.services;

import nrg.inc.bykerz.vehicle.domain.model.commands.CreateBrandCommand;
import nrg.inc.bykerz.vehicle.domain.model.entities.Brand;

import java.util.Optional;

public interface BrandCommandService {

    Optional<Brand> handle(CreateBrandCommand createBrandCommand);

}
