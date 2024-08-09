package bg.softuni.GimyApi.model.view;

public class ErrorViewModel {

    private String error;

    public ErrorViewModel(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public ErrorViewModel setError(String error) {
        this.error = error;
        return this;
    }
}
