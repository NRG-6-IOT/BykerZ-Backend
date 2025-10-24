package nrg.inc.bykerz.vehicles.domain.services;

import nrg.inc.bykerz.vehicles.domain.model.commands.CreateModelCommand;
import nrg.inc.bykerz.vehicles.domain.model.entities.Model;

import java.util.Optional;

public interface ModelCommandService {
    Optional<Model> handle(CreateModelCommand createModelCommand);
}
