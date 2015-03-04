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
public class Information implements Parcelable {
  @SerializedName("app_name")
  private String appName;
  @SerializedName("appid")
  private int    appId;
  @SerializedName("virtual_currency")
  private String virtualCurrency;
  @SerializedName("country")
  private String country;
  @SerializedName("language")
  private String language;
  @SerializedName("support_url")
  private String supportUrl;

  public static final Creator<Information> CREATOR;

  static {
    CREATOR = new Creator<Information>() {

      @Override
      public Information createFromParcel(Parcel source) {
        return new Information(source);
      }

      @Override
      public Information[] newArray(int size) {
        return new Information[size];
      }
    };
  }

  public Information() {}

  private Information(Parcel source) {
    this();
    readFromParcel(source);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(appName);
    dest.writeInt(appId);
    dest.writeString(virtualCurrency);
    dest.writeString(country);
    dest.writeString(language);
    dest.writeString(supportUrl);
  }

  private void readFromParcel(Parcel source) {
    appName = source.readString();
    appId = source.readInt();
    virtualCurrency = source.readString();
    country = source.readString();
    language = source.readString();
    supportUrl = source.readString();
  }

  public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  public int getAppId() {
    return appId;
  }

  public void setAppId(int appId) {
    this.appId = appId;
  }

  public String getVirtualCurrency() {
    return virtualCurrency;
  }

  public void setVirtualCurrency(String virtualCurrency) {
    this.virtualCurrency = virtualCurrency;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getSupportUrl() {
    return supportUrl;
  }

  public void setSupportUrl(String supportUrl) {
    this.supportUrl = supportUrl;
  }
}
