package nrg.inc.bykerz.maintenance.application.internal.queryservices;

import nrg.inc.bykerz.maintenance.domain.model.agreggates.Maintenance;
import nrg.inc.bykerz.maintenance.domain.model.queries.GetAllMaintenancesByMechanicIdQuery;
import nrg.inc.bykerz.maintenance.domain.model.queries.GetAllMaintenancesByVehicleIdQuery;
import nrg.inc.bykerz.maintenance.domain.model.queries.GetMaintenanceByIdQuery;
import nrg.inc.bykerz.maintenance.domain.services.MaintenanceQueryService;
import nrg.inc.bykerz.maintenance.infrastructure.persistence.jpa.repositories.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceQueryServiceImpl implements MaintenanceQueryService {

    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceQueryServiceImpl(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public List<Maintenance> handle(GetAllMaintenancesByVehicleIdQuery query) {
        return maintenanceRepository.findByVehicleId(query.vehicleId());
    }

    @Override
    public Optional<Maintenance> handle(GetMaintenanceByIdQuery query) {
        return maintenanceRepository.findById(query.maintenanceId());
    }

    @Override
    public List<Maintenance> handle(GetAllMaintenancesByMechanicIdQuery query) {
        return maintenanceRepository.findByMechanicId(query.mechanicId());
    }
}
