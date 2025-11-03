package nrg.inc.bykerz.profiles.infrastructure.persistence.jpa.repositories;

import nrg.inc.bykerz.profiles.domain.model.aggregates.Profile;
import nrg.inc.bykerz.profiles.domain.model.valueobjects.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByEmailAddress(EmailAddress emailAddress);
    boolean existsByEmailAddress(EmailAddress emailAddress);
}
