package nrg.inc.bykerz.vehicle.domain.services;

import nrg.inc.bykerz.vehicle.domain.model.commands.CreateModelCommand;
import nrg.inc.bykerz.vehicle.domain.model.entities.Model;

import java.util.Optional;

public interface ModelCommandService {

    Optional<Model> handle(CreateModelCommand createModelCommand);
}
