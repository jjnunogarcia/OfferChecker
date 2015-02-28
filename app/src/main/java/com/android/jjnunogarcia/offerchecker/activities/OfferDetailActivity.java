package com.android.jjnunogarcia.offerchecker.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.android.jjnunogarcia.offerchecker.model.jsonparsing.Offer;

/**
 * User: jesus
 * Date: 28/02/15
 *
 * @author j.nuno@klara.com
 */
public class OfferDetailActivity extends ActionBarActivity {
  private static final String TAG       = OfferDetailActivity.class.getSimpleName();
  private static final String KEY_OFFER = "key_offer";

  public static void startNewActivity(Context context, Offer offer) {
    Intent intent = new Intent(context, OfferDetailActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    Bundle arguments = new Bundle();
    arguments.putParcelable(KEY_OFFER, offer);
    intent.putExtras(arguments);
    context.startActivity(intent);
  }
}
