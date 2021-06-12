package by.epamtc.entity;

import java.util.List;
import java.util.Set;

public class Candy {

    private String name;

    private int energy;

    private Set<CandyType> types;

    private List<CandyType> ingredient;

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

    public List<CandyType> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<CandyType> ingredient) {
        this.ingredient = ingredient;
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
    public String toString() {
        return "Candy{" +
                "name='" + name + '\'' +
                ", energy=" + energy +
                ", types=" + types +
                ", ingredient=" + ingredient +
                ", value=" + value +
                ", production='" + production + '\'' +
                '}';
    }
}
