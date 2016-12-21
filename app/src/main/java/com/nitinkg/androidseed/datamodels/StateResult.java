package com.nitinkg.androidseed.datamodels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nitin on 7/12/16.
 */

public class StateResult implements Parcelable {

    @SerializedName("country")
    private String country;
    @SerializedName("name")
    private String name;
    @SerializedName("abbr")
    private String abbr;
    @SerializedName("area")
    private String area;
    @SerializedName("largest_city")
    private String largestCity;
    @SerializedName("capital")
    private String capital;

    /**
     * @return The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The abbr
     */
    public String getAbbr() {
        return abbr;
    }

    /**
     * @param abbr The abbr
     */
    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    /**
     * @return The area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area The area
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return The largestCity
     */
    public String getLargestCity() {
        return largestCity;
    }

    /**
     * @param largestCity The largest_city
     */
    public void setLargestCity(String largestCity) {
        this.largestCity = largestCity;
    }

    /**
     * @return The capital
     */
    public String getCapital() {
        return capital;
    }

    /**
     * @param capital The capital
     */
    public void setCapital(String capital) {
        this.capital = capital;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    public static final Parcelable.Creator<StateResult> CREATOR = new Creator<StateResult>() {

        public StateResult createFromParcel(Parcel source) {

            StateResult result = new StateResult();
            result.name = source.readString();
            result.abbr = source.readString();
            result.country = source.readString();
            result.capital = source.readString();
            result.largestCity = source.readString();
            result.area = source.readString();

            return result;

        }

        public StateResult[] newArray(int size) {

            return new StateResult[size];
        }

    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(abbr);
        parcel.writeString(country);
        parcel.writeString(capital);
        parcel.writeString(largestCity);
        parcel.writeString(area);

    }

}