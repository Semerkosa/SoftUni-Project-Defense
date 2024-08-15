package bg.softuni.GimyApi.model.service;

public class ReviewServiceModel {

    private String userId;
    private String review;
    private String entityId;

    public ReviewServiceModel() {
    }

    public String getUserId() {
        return userId;
    }

    public ReviewServiceModel setUserId(String userId) {
        this.userId = userId;
        return this;
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
                "userId='" + userId + '\'' +
                ", review='" + review + '\'' +
                ", entityId='" + entityId + '\'' +
                '}';
    }
}
