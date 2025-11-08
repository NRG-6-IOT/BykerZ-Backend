package nrg.inc.bykerz.assignments.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import nrg.inc.bykerz.profiles.domain.model.aggregates.Profile;
import nrg.inc.bykerz.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Mechanic extends AuditableAbstractAggregateRoot<Mechanic> {

    @OneToOne(optional = false)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;


    @OneToMany(mappedBy = "mechanic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assignment> assignments = new ArrayList<>();

    protected Mechanic() {
    }

    public Mechanic(Profile profile) {
        this.profile = profile;
    }

    public String getName() {
        return profile.getFirstName() + " " + profile.getLastName();
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
        assignment.setMechanic(this);
    }

    public void removeAssignment(Assignment assignment) {
        assignments.remove(assignment);
        assignment.setMechanic(null);
    }
}
