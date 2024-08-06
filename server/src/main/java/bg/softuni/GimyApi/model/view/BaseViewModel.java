package bg.softuni.GimyApi.model.view;

public abstract class BaseViewModel {

    private String id;

    public String getId() {
        return id;
    }

    public BaseViewModel setId(String id) {
        this.id = id;
        return this;
    }
}
