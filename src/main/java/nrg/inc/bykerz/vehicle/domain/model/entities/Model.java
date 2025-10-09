package nrg.inc.bykerz.vehicle.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import nrg.inc.bykerz.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import nrg.inc.bykerz.vehicle.domain.model.commands.CreateModelCommand;

import java.util.Date;

@Getter
@Entity
public class Model extends AuditableAbstractAggregateRoot<Model> {

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Brand brand;

    private String modelYear;
    private String originCountry;
    private Date producedAt;
    private String type;
    private String displacement;
    private String potency;
    private String engineType;
    private String engineTorque;
    private String weight;
    private String transmission;
    private String brakes;
    private String tank;
    private String seatHeight;
    private String consumption;
    private Float price;
    private String oilCapacity;
    private String connectivity;
    private String durability;
    private String octane;

    protected Model() {
        super();
    }

    public Model(String name, Brand brand ,String modelYear, String originCountry, Date producedAt, String type, String displacement, String potency, String engineType, String engineTorque, String weight, String transmission, String brakes, String tank, String seatHeight, String consumption, Float price, String oilCapacity, String connectivity, String durability, String octane) {
        this();
        this.name = name;
        this.brand = brand;
        this.modelYear = modelYear;
        this.originCountry = originCountry;
        this.producedAt = producedAt;
        this.type = type;
        this.displacement = displacement;
        this.potency = potency;
        this.engineType = engineType;
        this.engineTorque = engineTorque;
        this.weight = weight;
        this.transmission = transmission;
        this.brakes = brakes;
        this.tank = tank;
        this.seatHeight = seatHeight;
        this.consumption = consumption;
        this.price = price;
        this.oilCapacity = oilCapacity;
        this.connectivity = connectivity;
        this.durability = durability;
        this.octane = octane;
    }

    public Model(CreateModelCommand command, Brand brand) {
        this.name = command.name();
        this.brand = brand; // Brand ya existe en BD
        this.modelYear = command.modelYear();
        this.originCountry = command.originCountry();
        this.producedAt = command.producedAt();
        this.type = command.type();
        this.displacement = command.displacement();
        this.potency = command.potency();
        this.engineType = command.engineType();
        this.engineTorque = command.engineTorque();
        this.weight = command.weight();
        this.transmission = command.transmission();
        this.brakes = command.brakes();
        this.tank = command.tank();
        this.seatHeight = command.seatHeight();
        this.consumption = command.consumption();
        this.price = command.price();
        this.oilCapacity = command.oilCapacity();
        this.connectivity = command.connectivity();
        this.durability = command.durability();
        this.octane = command.octane();
    }



}
