package com.android.jjnunogarcia.offerchecker.model.jsonparsing;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * User: jesus
 * Date: 28/02/15
 *
 * @author jjnunogarcia@gmail.com
 */
public class TimeToPayout implements Parcelable {
  @SerializedName("amount")
  private int    amount;
  @SerializedName("readable")
  private String readable;

  public static final Creator<TimeToPayout> CREATOR;

  static {
    CREATOR = new Creator<TimeToPayout>() {

      @Override
      public TimeToPayout createFromParcel(Parcel source) {
        return new TimeToPayout(source);
      }

      @Override
      public TimeToPayout[] newArray(int size) {
        return new TimeToPayout[size];
      }
    };
  }

  public TimeToPayout() {}

  private TimeToPayout(Parcel source) {
    this();
    readFromParcel(source);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(amount);
    dest.writeString(readable);
  }

  private void readFromParcel(Parcel source) {
    amount = source.readInt();
    readable = source.readString();
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getReadable() {
    return readable;
  }

  public void setReadable(String readable) {
    this.readable = readable;
  }
}
