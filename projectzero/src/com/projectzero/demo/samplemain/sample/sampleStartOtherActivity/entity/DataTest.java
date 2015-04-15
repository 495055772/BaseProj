package com.projectzero.demo.samplemain.sample.sampleStartOtherActivity.entity;



import android.os.Parcel;
import android.os.Parcelable;

//parcelable自动生成工具：http://parcelabler.com/
public class DataTest implements Parcelable {
    String str;
    Long lon;

    public DataTest() {

    }

    protected DataTest(Parcel in) {
        str = in.readString();
        lon = in.readByte() == 0x00 ? null : in.readLong();
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Long getLon() {
        return lon;
    }

    public void setLon(Long lon) {
        this.lon = lon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(str);
        if (lon == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeLong(lon);
        }
    }

    public static final Creator<DataTest> CREATOR = new Creator<DataTest>() {
        @Override
        public DataTest createFromParcel(Parcel in) {
            return new DataTest(in);
        }

        @Override
        public DataTest[] newArray(int size) {
            return new DataTest[size];
        }
    };
}