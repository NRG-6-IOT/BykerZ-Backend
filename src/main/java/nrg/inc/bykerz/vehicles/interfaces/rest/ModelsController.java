package nrg.inc.bykerz.vehicles.interfaces.rest;

import nrg.inc.bykerz.vehicles.domain.model.commands.CreateModelCommand;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetAllModels;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetModelById;
import nrg.inc.bykerz.vehicles.domain.services.ModelCommandService;
import nrg.inc.bykerz.vehicles.domain.services.ModelQueryService;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.CreateModelResource;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.ModelResource;
import nrg.inc.bykerz.vehicles.interfaces.rest.transform.CreateModelCommandFromResourceAssembler;
import nrg.inc.bykerz.vehicles.interfaces.rest.transform.ModelResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/models")
@Tag(name = "Models", description = "Operations related to vehicle models")
public class ModelsController {
    private final ModelQueryService modelQueryService;

    public ModelsController(ModelQueryService modelQueryService) {
        this.modelQueryService = modelQueryService;
    }

    @GetMapping
    @Operation(summary = "Get all models")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Models retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No models found")
    })
    public ResponseEntity<List<ModelResource>> getAllModels() {
        var models = modelQueryService.handle(new GetAllModels());
        var resources = models.stream()
                .map(ModelResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{modelId}")
    @Operation(summary = "Get model by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Model retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Model not found")
    })
    public ResponseEntity<ModelResource> getModelById(@PathVariable Long modelId) {
        var model = modelQueryService.handle(new GetModelById(modelId));
        return model.map(m -> ResponseEntity.ok(ModelResourceFromEntityAssembler.toResourceFromEntity(m)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
