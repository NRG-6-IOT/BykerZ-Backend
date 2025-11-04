package nrg.inc.bykerz.shared.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import nrg.inc.bykerz.profiles.domain.model.aggregates.Profile;
import nrg.inc.bykerz.shared.domain.model.valueobjects.OwnerCode;

@Entity
@Getter
public class Owner extends AuditableAbstractAggregateRoot<Owner> {

    @OneToOne(optional = false)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    @Embedded
    @Setter
    @AttributeOverride(name = "code", column = @Column(name = "code", unique = true))
    private OwnerCode code;

    protected Owner() {
    }

    public Owner(Profile profile, OwnerCode code) {
        this.profile = profile;
        this.code = code;
    }

    public String getName() {
        return profile.getFirstName() + " " + profile.getLastName();
    }
}
