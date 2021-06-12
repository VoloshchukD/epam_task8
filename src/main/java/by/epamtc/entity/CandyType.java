package by.epamtc.entity;

public class CandyType {

    private String name;

    private boolean withFilling;

    private String consistency;

    private String preparation;

    private String species;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWithFilling() {
        return withFilling;
    }

    public void setWithFilling(boolean withFilling) {
        this.withFilling = withFilling;
    }

    public String getConsistency() {
        return consistency;
    }

    public void setConsistency(String consistency) {
        this.consistency = consistency;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandyType candyType = (CandyType) o;
        return withFilling == candyType.withFilling
                && (name == candyType.name || (name != null && name.equals(candyType.name)))
                && (consistency == candyType.consistency
                || (consistency != null && consistency.equals(candyType.consistency)))
                && (preparation == candyType.preparation
                || (preparation != null && preparation.equals(candyType.preparation)))
                && (species == candyType.species
                || (species != null && species.equals(candyType.species)));
    }

    @Override
    public int hashCode() {
        int result = 37;
        result = 17 * result + ((name != null) ? name.hashCode() : 0);
        result = 17 * result + ((consistency != null) ? consistency.hashCode() : 0);
        result = 17 * result + ((preparation != null) ? preparation.hashCode() : 0);
        result = 17 * result + ((species != null) ? species.hashCode() : 0);
        result = 17 * result + (withFilling ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "@name='" + name +
                ", withFilling=" + withFilling +
                ", consistency=" + consistency +
                ", preparation=" + preparation +
                ", species=" + species;
    }

}
