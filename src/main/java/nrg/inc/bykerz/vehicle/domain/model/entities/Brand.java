package nrg.inc.bykerz.vehicle.domain.model.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Getter;
import nrg.inc.bykerz.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import nrg.inc.bykerz.vehicle.domain.model.commands.CreateBrandCommand;

@Getter
@Entity
public class Brand extends AuditableAbstractAggregateRoot<Brand> {

    private String brandName;

    protected Brand() {
        super();
    }

    public Brand(String brandName) {
        this.brandName = brandName;
    }

    public Brand(CreateBrandCommand command) {
        this.brandName = command.brandName();
    }

}
