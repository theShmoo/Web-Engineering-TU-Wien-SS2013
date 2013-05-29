package tuwien.big.formel0.entities;

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
    MALE("male"),
    /**
     * A female user
     */
    FEMALE("female");
    
    private String label;

    private Sex(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
