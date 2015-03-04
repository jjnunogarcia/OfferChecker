package com.android.jjnunogarcia.offerchecker.eventbus;

/**
 * User: jesus
 * Date: 26/02/15
 *
 * @author jjnunogarcia@gmail.com
 */
public class GetAdvertisingIdInfoTaskResultEvent {
  private String  advertisingId;
  private boolean trackingEnabled;
  private int     message;

  public GetAdvertisingIdInfoTaskResultEvent(int message) {
    this.message = message;
    advertisingId = "";
    trackingEnabled = true;
  }

  public String getAdvertisingId() {
    return advertisingId;
  }

  public void setAdvertisingId(String advertisingId) {
    this.advertisingId = advertisingId;
  }

  public boolean isTrackingEnabled() {
    return trackingEnabled;
  }

  public void setTrackingEnabled(boolean trackingEnabled) {
    this.trackingEnabled = trackingEnabled;
  }

  public int getMessage() {
    return message;
  }

  public void setMessage(int message) {
    this.message = message;
  }
}
