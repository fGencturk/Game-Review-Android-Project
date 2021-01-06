package com.ctis487.group8.project;

import android.os.Parcel;
import android.os.Parcelable;

public class Game implements Parcelable {
    public int id, year, rating;
    public String name, image;

    protected Game(Parcel in) {
        id = in.readInt();
        year = in.readInt();
        rating = in.readInt();
        name = in.readString();
        image = in.readString();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(year);
        dest.writeInt(rating);
        dest.writeString(name);
        dest.writeString(image);
    }
}
