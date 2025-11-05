package nrg.inc.bykerz.wellness.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record StatusImpact(Boolean impactDetected) {
    public StatusImpact{
        if (impactDetected == null) {
            throw new IllegalArgumentException("impactDetected cannot be null");
        }
    }
}
