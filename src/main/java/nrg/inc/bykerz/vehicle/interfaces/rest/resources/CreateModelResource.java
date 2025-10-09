package nrg.inc.bykerz.vehicle.interfaces.rest.resources;

import nrg.inc.bykerz.vehicle.domain.model.commands.CreateBrandCommand;

import java.util.Date;

public record CreateModelResource(
        String name,
        Long brandId,
        String modelYear,
        String originCountry,
        Date producedAt,
        String type,
        String displacement,
        String potency,
        String engineType,
        String engineTorque,
        String weight,
        String transmission,
        String brakes,
        String tank,
        String seatHeight,
        String consumption,
        Float price,
        String oilCapacity,
        String connectivity,
        String durability,
        String octane
) {}
