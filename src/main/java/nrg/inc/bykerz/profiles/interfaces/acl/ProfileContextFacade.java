package nrg.inc.bykerz.profiles.interfaces.acl;

public interface ProfileContextFacade {
    Long createProfile(String firstName, String lastName, String email, Long userId);
}
