package nrg.inc.bykerz.vehicles.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/owners")
@Tag(name = "Owners", description = "Operations related to vehicle owners")
public class OwnersController {



}
