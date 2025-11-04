package nrg.inc.bykerz.shared.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import nrg.inc.bykerz.profiles.domain.model.aggregates.Profile;
import nrg.inc.bykerz.shared.domain.model.valueobjects.MechanicCode;

@Entity
@Getter
public class Mechanic extends AuditableAbstractAggregateRoot<Mechanic> {

    @OneToOne(optional = false)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    @Embedded
    @Setter
    @AttributeOverride(name = "code", column = @Column(name = "code", unique = true))
    private MechanicCode code;

    protected Mechanic() {
    }

    public Mechanic(Profile profile, MechanicCode code) {
        this.profile = profile;
        this.code = code;
    }

    public String getName() {
        return profile.getFirstName() + " " + profile.getLastName();
    }
}
