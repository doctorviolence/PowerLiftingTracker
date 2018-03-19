package powerlifting.model;

public class UserStatDetail {

    private int detailsId;
    private int weight;
    private boolean isMale;
    private boolean isFemale;
    private int userStatId;

    public UserStatDetail() {

    }

    public UserStatDetail(int detailsId, int weight, boolean isMale, boolean isFemale, int userStatId) {
        this.detailsId = detailsId;
        this.weight = weight;
        this.isMale = isMale;
        this.isFemale = isFemale;
        this.userStatId = userStatId;
    }

    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isFemale() {
        return isFemale;
    }

    public void setFemale(boolean female) {
        isFemale = female;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public int getUserStatId() {
        return userStatId;
    }

    public void setUserStatId(int userStatId) {
        this.userStatId = userStatId;
    }

    @Override
    public String toString() {
        return "Details Id: " + detailsId + " Weight: " + weight + " Male: " + isMale + " Female: " + isFemale + " User Stat Id: " + userStatId;
    }
}
