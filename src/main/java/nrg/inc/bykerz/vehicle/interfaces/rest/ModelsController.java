package nrg.inc.bykerz.vehicle.interfaces.rest;

import nrg.inc.bykerz.vehicle.domain.model.commands.CreateModelCommand;
import nrg.inc.bykerz.vehicle.domain.model.entities.Model;
import nrg.inc.bykerz.vehicle.domain.model.queries.GetAllModels;
import nrg.inc.bykerz.vehicle.domain.model.queries.GetModelById;
import nrg.inc.bykerz.vehicle.domain.services.ModelCommandService;
import nrg.inc.bykerz.vehicle.domain.services.ModelQueryService;
import nrg.inc.bykerz.vehicle.interfaces.rest.resources.CreateModelResource;
import nrg.inc.bykerz.vehicle.interfaces.rest.resources.ModelResource;
import nrg.inc.bykerz.vehicle.interfaces.rest.transform.CreateModelCommandFromResourceAssembler;
import nrg.inc.bykerz.vehicle.interfaces.rest.transform.ModelResourceFromEntityAssembler;
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

    private final ModelCommandService modelCommandService;
    private final ModelQueryService modelQueryService;

    public ModelsController(ModelCommandService modelCommandService, ModelQueryService modelQueryService) {
        this.modelCommandService = modelCommandService;
        this.modelQueryService = modelQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new model")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Model created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<ModelResource> createModel(@RequestBody CreateModelResource createModelResource) {
        CreateModelCommand command = CreateModelCommandFromResourceAssembler.toCommandFromResource(createModelResource);
        var model = modelCommandService.handle(command)
                .orElseThrow(() -> new IllegalArgumentException("Error creating model"));

        return new ResponseEntity<>(ModelResourceFromEntityAssembler.toResourceFromEntity(model), HttpStatus.CREATED);
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
