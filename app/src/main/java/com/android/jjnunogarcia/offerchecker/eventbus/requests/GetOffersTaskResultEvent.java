package com.android.jjnunogarcia.offerchecker.eventbus.requests;

import com.android.jjnunogarcia.offerchecker.model.jsonparsing.OfferTaskResult;

/**
 * User: jesus
 * Date: 25/02/15
 *
 * @author j.nuno@klara.com
 */
public class GetOffersTaskResultEvent {
  private int             serverResponse;
  private String          serverMessage;
  private OfferTaskResult offerTaskResult;

  public GetOffersTaskResultEvent(int serverResponse) {
    this.serverResponse = serverResponse;
  }

  public int getServerResponse() {
    return serverResponse;
  }

  public void setServerResponse(int serverResponse) {
    this.serverResponse = serverResponse;
  }

  public String getServerMessage() {
    return serverMessage;
  }

  public void setServerMessage(String serverMessage) {
    this.serverMessage = serverMessage;
  }

  public OfferTaskResult getOfferTaskResult() {
    return offerTaskResult;
  }

  public void setOfferTaskResult(OfferTaskResult offerTaskResult) {
    this.offerTaskResult = offerTaskResult;
  }
}