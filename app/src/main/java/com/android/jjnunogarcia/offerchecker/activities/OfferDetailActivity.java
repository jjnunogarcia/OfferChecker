package com.android.jjnunogarcia.offerchecker.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.android.jjnunogarcia.offerchecker.R;
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

  @InjectView(R.id.activity_offer_detail_title_text)
  TextView  titleText;
  @InjectView(R.id.activity_offer_detail_offer_id_text)
  TextView  offerIdText;
  @InjectView(R.id.activity_offer_detail_teaser_text)
  TextView  teaserText;
  @InjectView(R.id.activity_offer_detail_required_actions_text)
  TextView  requiredActionsText;
  @InjectView(R.id.activity_offer_detail_link_text)
  TextView  linkText;
  @InjectView(R.id.activity_offer_detail_payout_text)
  TextView  payoutText;
  @InjectView(R.id.activity_offer_detail_time_to_payout_text)
  TextView  timeToPayoutText;
  @InjectView(R.id.activity_offer_detail_thumbnail_image)
  ImageView thumbnail;
  @InjectView(R.id.activity_offer_detail_store_id_text)
  TextView  storeIdText;

  public static void startNewActivity(Context context, Offer offer) {
    Intent intent = new Intent(context, OfferDetailActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    Bundle arguments = new Bundle();
    arguments.putParcelable(KEY_OFFER, offer);
    intent.putExtras(arguments);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_offer_detail);
    ButterKnife.inject(this);

    Bundle arguments = getIntent().getExtras();

    if (arguments != null && arguments.containsKey(KEY_OFFER)) {
      Offer offer = arguments.getParcelable(KEY_OFFER);
      titleText.setText(offer.getTitle());
      offerIdText.setText(String.valueOf(offer.getOfferId()));
      teaserText.setText(offer.getTeaser());
      requiredActionsText.setText(offer.getRequiredActions());
      linkText.setText(offer.getLink());
      payoutText.setText(String.valueOf(offer.getPayout()));
//      timeToPayoutText.setText(offer.getTimeToPayout());
      storeIdText.setText(offer.getStoreId());
    }
  }
}
