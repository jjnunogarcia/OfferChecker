package com.android.jjnunogarcia.offerchecker.model.jsonparsing;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * User: jesus
 * Date: 27/02/15
 *
 * @author j.nuno@klara.com
 */
public class Offer { // TODO parcelable
  @SerializedName("title")
  private String          title;
  @SerializedName("offer_id")
  private int             offerId;
  @SerializedName("teaser")
  private String          teaser;
  @SerializedName("required_actions")
  private String          requiredActions;
  @SerializedName("link")
  private String          link;
  @SerializedName("offer_types")
  private List<OfferType> offerTypes;
  @SerializedName("payout")
  private int             payout;
  @SerializedName("time_to_payout")
  private TimeToPayout    timeToPayout;
  @SerializedName("thumbnail")
  private Thumbnail       thumbnail;
  @SerializedName("store_id")
  private String          storeId;

  private static class OfferType {
    @SerializedName("offer_type_id")
    private int    offerTypeId;
    @SerializedName("readable")
    private String readable;
  }

  private static class TimeToPayout {
    @SerializedName("amount")
    private int    amount;
    @SerializedName("readable")
    private String readable;
  }

  private static class Thumbnail {
    @SerializedName("lowres")
    private String lowResolution;
    @SerializedName("highres")
    private String highResolution;
  }
}
