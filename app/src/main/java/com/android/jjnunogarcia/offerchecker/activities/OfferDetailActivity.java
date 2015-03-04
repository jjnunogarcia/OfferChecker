package com.android.jjnunogarcia.offerchecker.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.android.jjnunogarcia.offerchecker.OfferCheckerApplication;
import com.android.jjnunogarcia.offerchecker.R;
import com.android.jjnunogarcia.offerchecker.dialogs.OfferTypesDialog;
import com.android.jjnunogarcia.offerchecker.helpers.PicassoUrlCallback;
import com.android.jjnunogarcia.offerchecker.helpers.Utils;
import com.android.jjnunogarcia.offerchecker.model.jsonparsing.Offer;
import com.android.jjnunogarcia.offerchecker.model.jsonparsing.OfferType;
import com.android.jjnunogarcia.offerchecker.model.jsonparsing.Thumbnail;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * User: jesus
 * Date: 28/02/15
 *
 * @author j.nuno@klara.com
 */
public class OfferDetailActivity extends ActionBarActivity {
  private static final String TAG       = OfferDetailActivity.class.getSimpleName();
  static final String KEY_OFFER = "key_offer";

  @InjectView(R.id.activity_offer_detail_title_header_text)
  TextView    titleHeaderText;
  @InjectView(R.id.activity_offer_detail_title_text)
  TextView    titleText;
  @InjectView(R.id.activity_offer_detail_offer_id_header_text)
  TextView    offerIdHeaderText;
  @InjectView(R.id.activity_offer_detail_offer_id_text)
  TextView    offerIdText;
  @InjectView(R.id.activity_offer_detail_teaser_header_text)
  TextView    teaserHeaderText;
  @InjectView(R.id.activity_offer_detail_teaser_text)
  TextView    teaserText;
  @InjectView(R.id.activity_offer_detail_required_actions_header_text)
  TextView    requiredActionsHeaderText;
  @InjectView(R.id.activity_offer_detail_required_actions_text)
  TextView    requiredActionsText;
  @InjectView(R.id.activity_offer_detail_link_header_text)
  TextView    linkHeaderText;
  @InjectView(R.id.activity_offer_detail_link_text)
  TextView    linkText;
  @InjectView(R.id.activity_offer_detail_offer_types_header_text)
  TextView    offerTypesHeaderText;
  @InjectView(R.id.activity_offer_detail_offer_types_button)
  Button      offerTypesButton;
  @InjectView(R.id.activity_offer_detail_payout_header_text)
  TextView    payoutHeaderText;
  @InjectView(R.id.activity_offer_detail_payout_text)
  TextView    payoutText;
  @InjectView(R.id.activity_offer_detail_time_to_payout_header_text)
  TextView    timeToPayoutHeaderText;
  @InjectView(R.id.activity_offer_detail_time_to_payout_amount_header_text)
  TextView    timeToPayoutAmountHeaderText;
  @InjectView(R.id.activity_offer_detail_time_to_payout_amount_text)
  TextView    timeToPayoutAmountText;
  @InjectView(R.id.activity_offer_detail_time_to_payout_readable_header_text)
  TextView    timeToPayoutReadableHeaderText;
  @InjectView(R.id.activity_offer_detail_time_to_payout_readable_text)
  TextView    timeToPayoutReadableText;
  @InjectView(R.id.activity_offer_detail_thumbnail_header_text)
  TextView    thumbnailHeaderText;
  @InjectView(R.id.activity_offer_detail_thumbnail_image)
  ImageView   thumbnailImage;
  @InjectView(R.id.activity_offer_detail_thumbnail_image_progress_bar)
  ProgressBar thumbnailProgressBar;
  @InjectView(R.id.activity_offer_detail_store_id_header_text)
  TextView    storeIdHeaderText;
  @InjectView(R.id.activity_offer_detail_store_id_text)
  TextView    storeIdText;

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
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setFonts();

    Bundle arguments = getIntent().getExtras();

    if (arguments != null && arguments.containsKey(KEY_OFFER)) {
      Offer offer = arguments.getParcelable(KEY_OFFER);
      Utils.setTextBlockContent(titleHeaderText, titleText, offer.getTitle());
      Utils.setTextBlockContent(offerIdHeaderText, offerIdText, String.valueOf(offer.getOfferId()));
      Utils.setTextBlockContent(teaserHeaderText, teaserText, offer.getTeaser());
      Utils.setTextBlockContent(requiredActionsHeaderText, requiredActionsText, offer.getRequiredActions());
      Utils.setTextBlockContent(linkHeaderText, linkText, offer.getLink());
      offerTypesButton.setTag(offer.getOfferTypes());
      Utils.setTextBlockContent(payoutHeaderText, payoutText, String.valueOf(offer.getPayout()));
      setTimeToPayout(offer);
      setThumbnail(offer.getThumbnail());
      Utils.setTextBlockContent(storeIdHeaderText, storeIdText, offer.getStoreId());
    }
  }

  private void setFonts() {
    Typeface typeface = Typeface.createFromAsset(getAssets(), Utils.FONT_PATH_FABRICA);
    titleHeaderText.setTypeface(typeface);
    titleText.setTypeface(typeface);
    offerIdHeaderText.setTypeface(typeface);
    offerIdText.setTypeface(typeface);
    teaserHeaderText.setTypeface(typeface);
    teaserText.setTypeface(typeface);
    requiredActionsHeaderText.setTypeface(typeface);
    requiredActionsText.setTypeface(typeface);
    linkHeaderText.setTypeface(typeface);
    linkText.setTypeface(typeface);
    offerTypesHeaderText.setTypeface(typeface);
    offerTypesButton.setTypeface(typeface);
    payoutHeaderText.setTypeface(typeface);
    payoutText.setTypeface(typeface);
    timeToPayoutHeaderText.setTypeface(typeface);
    timeToPayoutAmountHeaderText.setTypeface(typeface);
    timeToPayoutAmountText.setTypeface(typeface);
    timeToPayoutReadableHeaderText.setTypeface(typeface);
    timeToPayoutReadableText.setTypeface(typeface);
    thumbnailHeaderText.setTypeface(typeface);
    storeIdHeaderText.setTypeface(typeface);
    storeIdText.setTypeface(typeface);
  }

  private void setTimeToPayout(Offer offer) {
    Utils.setTextBlockContent(timeToPayoutAmountHeaderText, timeToPayoutAmountText, String.valueOf(offer.getTimeToPayout().getAmount()));
    Utils.setTextBlockContent(timeToPayoutReadableHeaderText, timeToPayoutReadableText, offer.getTimeToPayout().getReadable());

    if (TextUtils.isEmpty(String.valueOf(offer.getTimeToPayout().getAmount())) && TextUtils.isEmpty(offer.getTimeToPayout().getReadable())) {
      timeToPayoutHeaderText.setVisibility(View.GONE);
    } else {
      timeToPayoutHeaderText.setVisibility(View.VISIBLE);
    }
  }

  private void setThumbnail(Thumbnail thumbnail) {
    thumbnailImage.setTag(thumbnail);
    Picasso picasso = ((OfferCheckerApplication) getApplication()).getPicasso();
    Callback picassoCallback = new PicassoUrlCallback(thumbnailImage, thumbnailProgressBar);
    int thumbnailSize = (int) getResources().getDimension(R.dimen.activity_offer_detail_thumbnail_size);
    picasso.load(thumbnail.getLowResolution()).resize(thumbnailSize, thumbnailSize).centerInside().into(thumbnailImage, picassoCallback);
  }

  @OnClick(R.id.activity_offer_detail_thumbnail_image)
  void displayBigThumbnail() {
    Thumbnail thumbnail = (Thumbnail) thumbnailImage.getTag();
    DisplayImageActivity.startNewActivity(getApplicationContext(), thumbnail.getHighResolution());
  }

  @OnClick(R.id.activity_offer_detail_offer_types_button)
  void displayOfferTypes() {
    ArrayList<OfferType> offerTypes = (ArrayList<OfferType>) offerTypesButton.getTag();
    OfferTypesDialog.newInstance(offerTypes).show(getSupportFragmentManager().beginTransaction(), OfferTypesDialog.TAG);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
