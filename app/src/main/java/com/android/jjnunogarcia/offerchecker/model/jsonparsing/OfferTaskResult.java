package com.android.jjnunogarcia.offerchecker.model.jsonparsing;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * User: jesus
 * Date: 27/02/15
 *
 * @author j.nuno@klara.com
 */
public class OfferTaskResult {
  @SerializedName("code")
  private String      code;
  @SerializedName("message")
  private String      message;
  @SerializedName("count")
  private int         count;
  @SerializedName("pages")
  private int         pages;
  @SerializedName("information")
  private Information information;
  @SerializedName("offers")
  private List<Offer> offers;

  private static class Information {
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

  }
}
