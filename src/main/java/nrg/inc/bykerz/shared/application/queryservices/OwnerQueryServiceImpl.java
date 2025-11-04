package nrg.inc.bykerz.shared.application.queryservices;

import nrg.inc.bykerz.shared.domain.model.aggregates.Owner;
import nrg.inc.bykerz.shared.domain.model.queries.GetOwnerByCodeQuery;
import nrg.inc.bykerz.shared.domain.model.queries.GetOwnerByIdQuery;
import nrg.inc.bykerz.shared.domain.model.valueobjects.OwnerCode;
import nrg.inc.bykerz.shared.domain.services.OwnerQueryService;
import nrg.inc.bykerz.shared.infrastructure.persistence.jpa.repositories.OwnerRepository;
import org.springframework.stereotype.Service;

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
    public Optional<Owner> handle(GetOwnerByCodeQuery query) {
        var code = new OwnerCode(query.code());
        return this.ownerRepository.findByCode(code);
    }
}
