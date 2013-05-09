package formel0api;

/**
 * This enumeration represents the {@link Sex} of a {@link User}
 *
 * @author Lukas Kraenkl
 * @author David Pfahler
 * @author Johannes Deml
 */
public enum Sex {
    /**
     * A male user
     */
    MALE("data.sexes.male"),
    /**
     * A female user
     */
    FEMALE("data.sexes.female");
    
    private String label;

    private Sex(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
