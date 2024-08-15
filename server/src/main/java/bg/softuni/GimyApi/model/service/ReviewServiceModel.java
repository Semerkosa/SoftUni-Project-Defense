package bg.softuni.GimyApi.model.service;

public class ReviewServiceModel {

    private String review;
    private String entityId;

    public ReviewServiceModel() {
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getEntityId() {
        return entityId;
    }

    public ReviewServiceModel setEntityId(String entityId) {
        this.entityId = entityId;
        return this;
    }

    @Override
    public String toString() {
        return "ReviewServiceModel{" +
                "review='" + review + '\'' +
                ", entityId='" + entityId + '\'' +
                '}';
    }
}
