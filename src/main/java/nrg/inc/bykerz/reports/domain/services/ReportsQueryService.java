package nrg.inc.bykerz.reports.domain.services;

import nrg.inc.bykerz.reports.interfaces.rest.resources.ReportResource;
import nrg.inc.bykerz.reports.domain.model.queries.GetReportByVehicleIdQuery;

import java.util.Optional;

public interface ReportsQueryService {
    Optional<ReportResource> handle(GetReportByVehicleIdQuery query);
}

