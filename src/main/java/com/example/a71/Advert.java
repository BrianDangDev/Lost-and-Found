package com.example.a71;

import android.os.Parcel;
import android.os.Parcelable;

public class Advert implements Parcelable {

    private int id; // New member variable for the ID
    private String postType;
    private String description;
    private String date;
    private String location;
    private String name;
    private String phoneNumber;

    public Advert(int id, String postType, String description, String date, String location) {
        this.id = id;
        this.postType = postType;
        this.description = description;
        this.date = date;
        this.location = location;
    }


    // Getter method for the ID


    protected Advert(Parcel in) {

        postType = in.readString();
        description = in.readString();
        date = in.readString();
        location = in.readString();
    }

    public static final Creator<Advert> CREATOR = new Creator<Advert>() {
        @Override
        public Advert createFromParcel(Parcel in) {
            return new Advert(in);
        }

        @Override
        public Advert[] newArray(int size) {
            return new Advert[size];
        }
    };

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", postType='" + postType + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
    // Getter and setter methods for the ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPostType() {
        return postType;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(postType);
        dest.writeString(description);
        dest.writeString(date);
        dest.writeString(location);
    }
}
