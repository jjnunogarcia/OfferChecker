package com.android.jjnunogarcia.offerchecker.model.jsonparsing;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * User: jesus
 * Date: 28/02/15
 *
 * @author j.nuno@klara.com
 */
public class Thumbnail implements Parcelable {
  @SerializedName("lowres")
  private String lowResolution;
  @SerializedName("highres")
  private String highResolution;

  public static final Creator<Thumbnail> CREATOR;

  static {
    CREATOR = new Creator<Thumbnail>() {

      @Override
      public Thumbnail createFromParcel(Parcel source) {
        return new Thumbnail(source);
      }

      @Override
      public Thumbnail[] newArray(int size) {
        return new Thumbnail[size];
      }
    };
  }

  public Thumbnail() {}

  private Thumbnail(Parcel source) {
    this();
    readFromParcel(source);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(lowResolution);
    dest.writeString(highResolution);
  }

  private void readFromParcel(Parcel source) {
    lowResolution = source.readString();
    highResolution = source.readString();
  }

  public String getLowResolution() {
    return lowResolution;
  }

  public void setLowResolution(String lowResolution) {
    this.lowResolution = lowResolution;
  }

  public String getHighResolution() {
    return highResolution;
  }

  public void setHighResolution(String highResolution) {
    this.highResolution = highResolution;
  }
}
