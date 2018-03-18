package model;

public class UserStat {

    private int userStatId;
    private int userId;

    public UserStat(int userStatId, int userId){
        this.userStatId = userStatId;
        this.userId = userId;
    }

    public int getUserStatId() {
        return userStatId;
    }

    public void setUserStatId(int userStatId) {
        this.userStatId = userStatId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString(){
        return "User Stat Id: " + userStatId + " User Id: " + userId;
    }
}
