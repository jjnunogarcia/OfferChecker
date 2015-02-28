package com.android.jjnunogarcia.offerchecker.model.jsonparsing;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * User: jesus
 * Date: 27/02/15
 *
 * @author j.nuno@klara.com
 */
public class OfferTaskResult implements Parcelable {
  @SerializedName("code")
  private String           code;
  @SerializedName("message")
  private String           message;
  @SerializedName("count")
  private int              count;
  @SerializedName("pages")
  private int              pages;
  @SerializedName("information")
  private Information      information;
  @SerializedName("offers")
  private ArrayList<Offer> offers;

  public static final Creator<OfferTaskResult> CREATOR;

  static {
    CREATOR = new Creator<OfferTaskResult>() {

      @Override
      public OfferTaskResult createFromParcel(Parcel source) {
        return new OfferTaskResult(source);
      }

      @Override
      public OfferTaskResult[] newArray(int size) {
        return new OfferTaskResult[size];
      }
    };
  }

  public OfferTaskResult() {
    offers = new ArrayList<>();
  }

  private OfferTaskResult(Parcel source) {
    this();
    readFromParcel(source);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(code);
    dest.writeString(message);
    dest.writeInt(count);
    dest.writeInt(pages);
    dest.writeParcelable(information, flags);
    dest.writeTypedList(offers);
  }

  private void readFromParcel(Parcel source) {
    code = source.readString();
    message = source.readString();
    count = source.readInt();
    pages = source.readInt();
    information = source.readParcelable(Information.class.getClassLoader());
    source.readTypedList(offers, Offer.CREATOR);
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public int getCount() {
    return count;
  }

  public int getPages() {
    return pages;
  }

  public Information getInformation() {
    return information;
  }

  public ArrayList<Offer> getOffers() {
    return offers;
  }
}
