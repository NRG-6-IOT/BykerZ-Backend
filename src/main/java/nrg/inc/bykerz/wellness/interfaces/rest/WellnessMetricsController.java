package nrg.inc.bykerz.wellness.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import nrg.inc.bykerz.wellness.domain.model.aggregates.WellnessMetric;
import nrg.inc.bykerz.wellness.domain.model.queries.GetWellnessMetricByIdQuery;
import nrg.inc.bykerz.wellness.domain.services.WellnessMetricCommandService;
import nrg.inc.bykerz.wellness.domain.services.WellnessMetricQueryService;
import nrg.inc.bykerz.wellness.interfaces.rest.resources.CreateWellnessMetricResource;
import nrg.inc.bykerz.wellness.interfaces.rest.resources.WellnessMetricResource;
import nrg.inc.bykerz.wellness.interfaces.rest.transform.CreateWellnessMetricCommandFromResourceAssembler;
import nrg.inc.bykerz.wellness.interfaces.rest.transform.WellnessMetricResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/metrics", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Wellness Metrics", description = "Operations related to wellness metrics")
@RequiredArgsConstructor
public class WellnessMetricsController {

    private final WellnessMetricCommandService wellnessMetricCommandService;
    private final WellnessMetricQueryService wellnessMetricQueryService;
    //

    @PostMapping
    @Operation(summary = "Create a new wellness metric", description = "Creates a new wellness metric for a vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Wellness metric created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Vehicle not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<WellnessMetricResource> createWellnessMetric(@RequestBody CreateWellnessMetricResource resource){

        //Transform resource to command
        var createCommand = CreateWellnessMetricCommandFromResourceAssembler.toCommandFromResource(resource);

        //Handle the command
        var wellnessMetricId = wellnessMetricCommandService.handle(createCommand);

        //Validate the creation
        if (wellnessMetricId == null) {
            return ResponseEntity.badRequest().build();
        }

        //Retrieve the created metric
        var wellnessMetricOpt = wellnessMetricQueryService.handle(new GetWellnessMetricByIdQuery( wellnessMetricId ));

        if (wellnessMetricOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var wellnessMetric = wellnessMetricOpt.get();

        //Transform to resource

        var wellnessMetricResource = WellnessMetricResourceFromEntityAssembler.toResourceFromEntity(wellnessMetric);
        return ResponseEntity.status(201).body(wellnessMetricResource);

    }

}
