package by.epamtc.entity;

public class Value {

    private int proteins;

    private int fats;

    private int carbohydrates;

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Value value = (Value) o;
        return proteins == value.proteins && fats == value.fats
                && carbohydrates == value.carbohydrates;
    }

    @Override
    public int hashCode() {
        int result = 37;
        result = 17 * result + proteins;
        result = 17 * result + fats;
        result = 17 * result + carbohydrates;
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "@proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates;
    }

}
