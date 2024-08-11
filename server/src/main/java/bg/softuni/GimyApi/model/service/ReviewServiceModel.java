package bg.softuni.GimyApi.model.service;

public class ReviewServiceModel {

    private String review;

    public ReviewServiceModel(String review) {
        this.review = review;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
