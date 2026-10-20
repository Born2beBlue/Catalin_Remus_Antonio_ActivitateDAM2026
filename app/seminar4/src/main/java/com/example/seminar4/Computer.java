package com.example.seminar4;

import android.os.Parcel;
import android.os.Parcelable;

public class Computer implements Parcelable {
    final private String marca;
    final private double pret;
    final private boolean areSsd;
    final private String tip;
    final private String sistemOperare;
    final private float rating;
    final private boolean placaVideoDedicata;

    public Computer(String marca, double pret, boolean areSsd, String tip, String sistemOperare, float rating, boolean placaVideoDedicata) {
        this.marca = marca;
        this.pret = pret;
        this.areSsd = areSsd;
        this.tip = tip;
        this.sistemOperare = sistemOperare;
        this.rating = rating;
        this.placaVideoDedicata = placaVideoDedicata;
    }

    protected Computer(Parcel in) {
        marca = in.readString();
        pret = in.readDouble();
        areSsd = in.readByte() != 0;
        tip = in.readString();
        sistemOperare = in.readString();
        rating = in.readFloat();
        placaVideoDedicata = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(marca);
        dest.writeDouble(pret);
        dest.writeByte((byte) (areSsd ? 1 : 0));
        dest.writeString(tip);
        dest.writeString(sistemOperare);
        dest.writeFloat(rating);
        dest.writeByte((byte) (placaVideoDedicata ? 1 : 0));
    }

    @Override
    public int describeContents() { return 0; }

    public static final Creator<Computer> CREATOR = new Creator<Computer>() {
        @Override public Computer createFromParcel(Parcel in) { return new Computer(in); }
        @Override public Computer[] newArray(int size) { return new Computer[size]; }
    };

    @Override
    public String toString() {
        return "Marcă: " + marca + "\nPreț: " + pret + " lei\nSSD: " + (areSsd ? "da" : "nu")
                + "\nTip: " + tip + "\nSistem de operare: " + sistemOperare
                + "\nRating: " + rating
                + "\nPlacă video dedicată: " + (placaVideoDedicata ? "da" : "nu");
    }
}