package nrg.inc.bykerz.shared.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import nrg.inc.bykerz.profiles.domain.model.aggregates.Profile;

@Entity
@Getter
public class Owner extends AuditableAbstractAggregateRoot<Owner> {

    @OneToOne(optional = false)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    protected Owner() {
    }

    public Owner(Profile profile) {
        this.profile = profile;
    }

    public String getName() {
        return profile.getFirstName() + " " + profile.getLastName();
    }
}
