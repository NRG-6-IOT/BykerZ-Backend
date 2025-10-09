package nrg.inc.bykerz.vehicle.interfaces.rest;

import nrg.inc.bykerz.vehicle.domain.model.commands.CreateBrandCommand;
import nrg.inc.bykerz.vehicle.domain.model.entities.Brand;
import nrg.inc.bykerz.vehicle.domain.model.queries.GetAllBrands;
import nrg.inc.bykerz.vehicle.domain.model.queries.GetBrandById;
import nrg.inc.bykerz.vehicle.domain.services.BrandCommandService;
import nrg.inc.bykerz.vehicle.domain.services.BrandQueryService;
import nrg.inc.bykerz.vehicle.interfaces.rest.resources.BrandResource;
import nrg.inc.bykerz.vehicle.interfaces.rest.resources.CreateBrandResource;
import nrg.inc.bykerz.vehicle.interfaces.rest.transform.BrandResourceFromEntityAssembler;
import nrg.inc.bykerz.vehicle.interfaces.rest.transform.CreateBrandCommandFromResourceAssembler;
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
@RequestMapping("/api/v1/brands")
@Tag(name = "Brands", description = "Operations related to brands")
public class BrandsController {

    private final BrandCommandService brandCommandService;
    private final BrandQueryService brandQueryService;

    public BrandsController(BrandCommandService brandCommandService, BrandQueryService brandQueryService) {
        this.brandCommandService = brandCommandService;
        this.brandQueryService = brandQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new brand")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Brand created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<BrandResource> createBrand(@RequestBody CreateBrandResource createBrandResource) {
        CreateBrandCommand command = CreateBrandCommandFromResourceAssembler.toCommandFromResource(createBrandResource);
        var brand = brandCommandService.handle(command)
                .orElseThrow(() -> new IllegalArgumentException("Error creating brand"));

        return new ResponseEntity<>(BrandResourceFromEntityAssembler.toResourceFromEntity(brand), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all brands")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Brands retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No brands found")
    })
    public ResponseEntity<List<BrandResource>> getAllBrands() {
        var brands = brandQueryService.handle(new GetAllBrands());
        var resources = brands.stream()
                .map(BrandResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{brandId}")
    @Operation(summary = "Get brand by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Brand retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    public ResponseEntity<BrandResource> getBrandById(@PathVariable Long brandId) {
        var brand = brandQueryService.handle(new GetBrandById(brandId));
        return brand.map(b -> ResponseEntity.ok(BrandResourceFromEntityAssembler.toResourceFromEntity(b)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}