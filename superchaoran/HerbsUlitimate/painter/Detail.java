package superchaoran.HerbsUlitimate.painter;

//More cancer

public class Detail {

    private String name;
    private Object object;

    public Detail(String detail) {
        this.name = detail;
    }

    public String getName() {
        return name;
    }

    public Object getObject() {
        return object;
    }

    public Detail setObject(Object o) {
        object = o;
        return this;
    }
}
