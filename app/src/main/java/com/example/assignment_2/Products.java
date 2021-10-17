package com.example.assignment_2;

import android.os.Parcel;
import android.os.Parcelable;

public class Products implements Parcelable {
    public String productName;
    public int productQty;
    public double productPrice;
    public String purchaseDate;

    public Products(String productName, int productQty, double productPrice, String purchaseDate) {
        this.productName = productName;
        this.productQty = productQty;
        this.productPrice = productPrice;
        this.purchaseDate = purchaseDate;
    }

    public Products(String productName, int qty, double price) {
        this.productName = productName;
        this.productQty = qty;
        this.productPrice = price;
        this.purchaseDate = "";
    }

    protected Products(Parcel in) {
        productName = in.readString();
        productQty = in.readInt();
        productPrice = in.readDouble();
        purchaseDate = in.readString();
    }

    public static final Creator<Products> CREATOR = new Creator<Products>() {
        @Override
        public Products createFromParcel(Parcel in) {
            return new Products(in);
        }

        @Override
        public Products[] newArray(int size) {
            return new Products[size];
        }
    };

    public void updateQty(int boughtNumber) {
        productQty = productQty - boughtNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(productName);
        parcel.writeInt(productQty);
        parcel.writeDouble(productPrice);
        parcel.writeString(purchaseDate);
    }
}
