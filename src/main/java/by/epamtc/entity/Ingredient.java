package by.epamtc.entity;

public class Ingredient {

    private String name;

    private int weight;

    private String kind;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return weight == that.weight
                && (name == that.name || (name != null && name.equals(that.name)))
                && (kind == that.kind || (kind != null && kind.equals(that.kind)));
    }

    @Override
    public int hashCode() {
        int result = 37;
        result = 17 * result + ((name != null) ? name.hashCode() : 0);
        result = 17 * result + weight;
        result = 17 * result + ((kind != null) ? kind.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "@name=" + name +
                ", weight=" + weight +
                ", kind=" + kind;
    }

}
