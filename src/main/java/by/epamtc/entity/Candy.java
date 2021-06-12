package by.epamtc.entity;

import java.util.List;
import java.util.Set;

public class Candy {

    private String name;

    private int energy;

    private Set<CandyType> types;

    private List<Ingredient> ingredients;

    private Value value;

    private String production;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public Set<CandyType> getTypes() {
        return types;
    }

    public void setTypes(Set<CandyType> types) {
        this.types = types;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candy candy = (Candy) o;
        return energy == candy.energy
                && (name == candy.name || (name != null && name.equals(candy.name)))
                && (types == candy.types || (types != null && types.equals(candy.types)))
                && (ingredients == candy.ingredients || (ingredients != null && ingredients.equals(candy.ingredients)))
                && (value == candy.value || (value != null && value.equals(candy.value)))
                && (production == candy.production || (production != null && production.equals(candy.production)));
    }

    @Override
    public int hashCode() {
        int result = 37;
        result = 17 * result + ((name != null) ? name.hashCode() : 0);
        result = 17 * result + energy;
        result = 17 * result + ((value != null) ? value.hashCode() : 0);
        result = 17 * result + ((production != null) ? production.hashCode() : 0);
        if (types != null) {
            for (CandyType candyType : types) {
                result += (candyType == null ? 0 : candyType.hashCode());
            }
        }
        if (ingredients != null) {
            for (Ingredient ingredient : ingredients) {
                result += (ingredient == null ? 0 : ingredient.hashCode());
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "@name=" + name +
                ", energy=" + energy +
                ", types=" + types +
                ", ingredient=" + ingredients +
                ", value=" + value +
                ", production=" + production;
    }

}
