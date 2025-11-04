package nrg.inc.bykerz.shared.domain.model.valueobjects;

import java.security.SecureRandom;

public record OwnerCode(String code) {
    private static final String ALPHANUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int LENGTH = 9;

    public OwnerCode {
        if (code == null || code.length() != LENGTH || !code.matches("[0-9A-Z]{" + LENGTH + "}")) {
            throw new IllegalArgumentException("El código debe tener 9 caracteres alfanuméricos (0-9, A-Z)");
        }
    }

    public static OwnerCode random() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            sb.append(ALPHANUM.charAt(random.nextInt(ALPHANUM.length())));
        }
        return new OwnerCode(sb.toString());
    }
}
