package model;

public class Score {

    private String username;
    private int score;
    private int upScore;
    private  int downScore;

    public Score(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getUpScore() {
        return upScore;
    }

    public void setUpScore(int upScore) {
        this.upScore = upScore;
    }

    public int getDownScore() {
        return downScore;
    }

    public void setDownScore(int downScore) {
        this.downScore = downScore;
    }
}
