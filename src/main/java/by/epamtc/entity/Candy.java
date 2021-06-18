package by.epamtc.entity;

import by.epamtc.entity.type.CandyType;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Candy {

    private String name;

    private int energy;

    private LocalDate productionDate;

    private LocalDate expirationDate;

    private Set<CandyType> types;

    private Set<Ingredient> ingredients;

    private Value value;

    private String production;

    public Candy() {
        types = new HashSet<>();
        ingredients = new HashSet<>();
    }

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

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Set<CandyType> getTypes() {
        return types;
    }

    public void setTypes(Set<CandyType> types) {
        this.types = types;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
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
                && (productionDate == candy.productionDate || (productionDate != null && productionDate.equals(candy.productionDate)))
                && (expirationDate == candy.expirationDate || (expirationDate != null && expirationDate.equals(candy.expirationDate)))
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
        result = 17 * result + ((productionDate != null) ? productionDate.hashCode() : 0);
        result = 17 * result + ((expirationDate != null) ? expirationDate.hashCode() : 0);
        result = 17 * result + ((value != null) ? value.hashCode() : 0);
        result = 17 * result + ((production != null) ? production.hashCode() : 0);
        result = 17 * result + ((types != null) ? types.hashCode() : 0);
        result = 17 * result + ((ingredients != null) ? ingredients.hashCode() : 0);
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
                ", productionDate=" + productionDate +
                ", expirationDate=" + expirationDate +
                ", types=" + types +
                ", ingredients=" + ingredients +
                ", value=" + value +
                ", production=" + production;
    }

}
