package nrg.inc.bykerz.assignments.application.internal.queryservice;

import nrg.inc.bykerz.assignments.domain.model.aggregates.Mechanic;
import nrg.inc.bykerz.assignments.domain.model.queries.GetMechanicByCodeQuery;
import nrg.inc.bykerz.assignments.domain.model.queries.GetMechanicByIdQuery;
import nrg.inc.bykerz.assignments.domain.model.valueobjects.MechanicCode;
import nrg.inc.bykerz.assignments.domain.services.MechanicQueryService;
import nrg.inc.bykerz.assignments.infrastructure.persistence.jpa.repositories.MechanicRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MechanicQueryServiceImpl implements MechanicQueryService {

    private final MechanicRepository mechanicRepository;

    public MechanicQueryServiceImpl(MechanicRepository mechanicRepository) {
        this.mechanicRepository = mechanicRepository;
    }

    @Override
    public Optional<Mechanic> handle(GetMechanicByIdQuery query) {
        return this.mechanicRepository.findById(query.mechanicId());
    }

    @Override
    public Optional<Mechanic> handle(GetMechanicByCodeQuery query) {
        var code = new MechanicCode(query.code());
        var mechanic= this.mechanicRepository.findByCode(code);
        if (mechanic.isEmpty()) {
            return Optional.empty();
        }
        return mechanic;
    }
}
