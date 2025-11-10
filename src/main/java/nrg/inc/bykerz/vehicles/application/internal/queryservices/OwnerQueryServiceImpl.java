package nrg.inc.bykerz.vehicles.application.internal.queryservices;

import nrg.inc.bykerz.vehicles.domain.model.aggregates.Owner;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetAllOwnersQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetOwnerByIdQuery;
import nrg.inc.bykerz.vehicles.domain.services.OwnerQueryService;
import nrg.inc.bykerz.vehicles.infrastructure.persistence.jpa.repositories.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerQueryServiceImpl implements OwnerQueryService {

    private final OwnerRepository ownerRepository;

    public OwnerQueryServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Optional<Owner> handle(GetOwnerByIdQuery query) {
        return this.ownerRepository.findById(query.ownerId());
    }

    @Override
    public List<Owner> handle(GetAllOwnersQuery query) {
        return this.ownerRepository.findAll();
    }
}
