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
public class OfferType implements Parcelable {
  @SerializedName("offer_type_id")
  private int    offerTypeId;
  @SerializedName("readable")
  private String readable;

  public static final Creator<OfferType> CREATOR;

  static {
    CREATOR = new Creator<OfferType>() {

      @Override
      public OfferType createFromParcel(Parcel source) {
        return new OfferType(source);
      }

      @Override
      public OfferType[] newArray(int size) {
        return new OfferType[size];
      }
    };
  }

  public OfferType() {}

  private OfferType(Parcel source) {
    this();
    readFromParcel(source);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(offerTypeId);
    dest.writeString(readable);
  }

  private void readFromParcel(Parcel source) {
    offerTypeId = source.readInt();
    readable = source.readString();
  }
}
