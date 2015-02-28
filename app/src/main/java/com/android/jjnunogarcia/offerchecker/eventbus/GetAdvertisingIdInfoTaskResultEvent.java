package com.android.jjnunogarcia.offerchecker.eventbus;

/**
 * User: jesus
 * Date: 26/02/15
 *
 * @author j.nuno@klara.com
 */
public class GetAdvertisingIdInfoTaskResultEvent {
  private String  advertisingId;
  private boolean trackingEnabled;

  public GetAdvertisingIdInfoTaskResultEvent() {
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
}
