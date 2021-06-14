package by.epamtc.entity.type;

public abstract class CandyType {

    private String kind;

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
        CandyType candyType = (CandyType) o;
        return (kind == candyType.kind || (kind != null && kind.equals(candyType.kind)));
    }

    @Override
    public int hashCode() {
        int result = 37;
        result = 17 * result + ((kind != null) ? kind.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "@kind=" + kind;
    }

}
