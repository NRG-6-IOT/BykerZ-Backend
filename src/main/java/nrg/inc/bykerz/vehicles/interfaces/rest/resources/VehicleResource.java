package nrg.inc.bykerz.vehicles.interfaces.rest.resources;

import java.util.Date;

public record VehicleResource(
        Long id,
        Long mechanicId,

        String modelName,

        String brandName,// from Brand

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
        String octane,

        Date manufacturingDate,
        String plate
) {
}
