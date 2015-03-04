package com.android.jjnunogarcia.offerchecker.model.jsonparsing;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * User: jesus
 * Date: 27/02/15
 *
 * @author jjnunogarcia@gmail.com
 */
public class Offer implements Parcelable {
  @SerializedName("title")
  private String               title;
  @SerializedName("offer_id")
  private int                  offerId;
  @SerializedName("teaser")
  private String               teaser;
  @SerializedName("required_actions")
  private String               requiredActions;
  @SerializedName("link")
  private String               link;
  @SerializedName("offer_types")
  private ArrayList<OfferType> offerTypes;
  @SerializedName("payout")
  private int                  payout;
  @SerializedName("time_to_payout")
  private TimeToPayout         timeToPayout;
  @SerializedName("thumbnail")
  private Thumbnail            thumbnail;
  @SerializedName("store_id")
  private String               storeId;

  public static final Creator<Offer> CREATOR;

  static {
    CREATOR = new Creator<Offer>() {

      @Override
      public Offer createFromParcel(Parcel source) {
        return new Offer(source);
      }

      @Override
      public Offer[] newArray(int size) {
        return new Offer[size];
      }
    };
  }

  public Offer() {
    offerTypes = new ArrayList<>();
  }

  private Offer(Parcel source) {
    this();
    readFromParcel(source);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(title);
    dest.writeInt(offerId);
    dest.writeString(teaser);
    dest.writeString(requiredActions);
    dest.writeString(link);
    dest.writeTypedList(offerTypes);
    dest.writeInt(payout);
    dest.writeParcelable(timeToPayout, flags);
    dest.writeParcelable(thumbnail, flags);
    dest.writeString(storeId);
  }

  private void readFromParcel(Parcel source) {
    title = source.readString();
    offerId = source.readInt();
    teaser = source.readString();
    requiredActions = source.readString();
    link = source.readString();
    source.readTypedList(offerTypes, OfferType.CREATOR);
    payout = source.readInt();
    timeToPayout = source.readParcelable(TimeToPayout.class.getClassLoader());
    thumbnail = source.readParcelable(Thumbnail.class.getClassLoader());
    storeId = source.readString();
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getOfferId() {
    return offerId;
  }

  public void setOfferId(int offerId) {
    this.offerId = offerId;
  }

  public String getTeaser() {
    return teaser;
  }

  public void setTeaser(String teaser) {
    this.teaser = teaser;
  }

  public String getRequiredActions() {
    return requiredActions;
  }

  public void setRequiredActions(String requiredActions) {
    this.requiredActions = requiredActions;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public ArrayList<OfferType> getOfferTypes() {
    return offerTypes;
  }

  public void setOfferTypes(ArrayList<OfferType> offerTypes) {
    this.offerTypes = offerTypes;
  }

  public int getPayout() {
    return payout;
  }

  public void setPayout(int payout) {
    this.payout = payout;
  }

  public TimeToPayout getTimeToPayout() {
    return timeToPayout;
  }

  public void setTimeToPayout(TimeToPayout timeToPayout) {
    this.timeToPayout = timeToPayout;
  }

  public Thumbnail getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(Thumbnail thumbnail) {
    this.thumbnail = thumbnail;
  }

  public String getStoreId() {
    return storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }
}
