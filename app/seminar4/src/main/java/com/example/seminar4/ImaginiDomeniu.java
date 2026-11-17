package com.example.seminar4;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class ImaginiDomeniu implements Parcelable {
    private String textAfisat;
    private Bitmap imagine;
    private String link;

    public ImaginiDomeniu(Bitmap imagine, String link, String textAfisat) {
        this.imagine = imagine;
        this.link = link;
        this.textAfisat = textAfisat;
    }

    @Override
    public String toString() {
        return "ImaginiDomeniu{" +
                "imagine=" + imagine +
                ", textAfisat='" + textAfisat + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    public Bitmap getImagine() {
        return imagine;
    }

    public void setImagine(Bitmap imagine) {
        this.imagine = imagine;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTextAfisat() {
        return textAfisat;
    }

    public void setTextAfisat(String textAfisat) {
        this.textAfisat = textAfisat;
    }

    protected ImaginiDomeniu(Parcel in) {
        textAfisat = in.readString();
        imagine = in.readParcelable(Bitmap.class.getClassLoader());
        link = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(textAfisat);
        dest.writeParcelable(imagine, flags);
        dest.writeString(link);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ImaginiDomeniu> CREATOR = new Creator<ImaginiDomeniu>() {
        @Override
        public ImaginiDomeniu createFromParcel(Parcel in) {
            return new ImaginiDomeniu(in);
        }

        @Override
        public ImaginiDomeniu[] newArray(int size) {
            return new ImaginiDomeniu[size];
        }
    };
}
