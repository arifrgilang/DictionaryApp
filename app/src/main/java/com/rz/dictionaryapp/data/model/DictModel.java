package com.rz.dictionaryapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DictModel implements Parcelable {

    private int id;
    private String word;
    private String translation;

    public DictModel(int id, String word, String translation){
        this.id = id;
        this.word = word;
        this.translation = translation;
    }

    public DictModel(String word, String translation){
        this.word = word;
        this.translation = translation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDict() {
        return word;
    }

    public void setDict(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.word);
        dest.writeString(this.translation);
    }

    protected DictModel(Parcel in) {
        this.id = in.readInt();
        this.word = in.readString();
        this.translation = in.readString();
    }

    public static final Parcelable.Creator<DictModel> CREATOR = new Parcelable.Creator<DictModel>() {
        @Override
        public DictModel createFromParcel(Parcel source) {
            return new DictModel(source);
        }

        @Override
        public DictModel[] newArray(int size) {
            return new DictModel[size];
        }
    };
}
