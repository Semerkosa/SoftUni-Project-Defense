package bg.softuni.GimyApi.model.view;

public class CustomMessageViewModel {

    private String message;

    public CustomMessageViewModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public CustomMessageViewModel setMessage(String message) {
        this.message = message;
        return this;
    }
}
