package nrg.inc.bykerz.vehicle.domain.model.commands;

import java.util.Date;

public record CreateModelCommand(
        String name,
        Long brandId, // ID de la marca existente
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