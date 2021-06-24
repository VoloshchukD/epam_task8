package by.epamtc.entity.type;

public abstract class CandyType {

    private String variety;

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandyType candyType = (CandyType) o;
        return (variety == candyType.variety || (variety != null && variety.equals(candyType.variety)));
    }

    @Override
    public int hashCode() {
        int result = 37;
        result = 17 * result + ((variety != null) ? variety.hashCode() : 0);
        return result;
    }

}
