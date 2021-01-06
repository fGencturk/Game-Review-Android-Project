package com.ctis487.group8.project;

public class GameReview {
    private int _id;
    private int gameId;
    private int hoursSpent;
    private int rating;
    private String review;

    public GameReview(int _id, int gameId, int hoursSpent, int rating, String review) {
        this._id = _id;
        this.gameId = gameId;
        this.hoursSpent = hoursSpent;
        this.rating = rating;
        this.review = review;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getHoursSpent() {
        return hoursSpent;
    }

    public void setHoursSpent(int hoursSpent) {
        this.hoursSpent = hoursSpent;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
