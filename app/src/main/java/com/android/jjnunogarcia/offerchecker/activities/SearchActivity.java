package com.android.jjnunogarcia.offerchecker.activities;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.android.jjnunogarcia.offerchecker.R;
import com.android.jjnunogarcia.offerchecker.backend.requests.GetOffersTask;
import com.android.jjnunogarcia.offerchecker.eventbus.GetAdvertisingIdInfoTaskResultEvent;
import com.android.jjnunogarcia.offerchecker.eventbus.requests.GetOffersTaskResultEvent;
import com.android.jjnunogarcia.offerchecker.helpers.Utils;
import com.android.jjnunogarcia.offerchecker.threads.GetAdvertisingIdInfoTask;
import de.greenrobot.event.EventBus;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class SearchActivity extends ActionBarActivity {
  private static final String TAG = SearchActivity.class.getSimpleName();

  @InjectView(R.id.activity_search_title_text)
  TextView titleText;
  @InjectView(R.id.activity_search_uid_edit_text)
  EditText uidEditText;
  @InjectView(R.id.activity_search_api_key_edit_text)
  EditText apiKeyEditText;
  @InjectView(R.id.activity_search_appid_edit_text)
  EditText appIdEditText;
  @InjectView(R.id.activity_search_pub0_edit_text)
  EditText pub0EditText;
  @InjectView(R.id.activity_search_get_offers_button)
  Button   getOffersButton;

  private ProgressDialog progressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);
    ButterKnife.inject(this);
    setFonts();
  }

  private void setFonts() {
    Typeface typeface = Typeface.createFromAsset(getAssets(), Utils.FONT_PATH_FABRICA);
    titleText.setTypeface(typeface);
    uidEditText.setTypeface(typeface);
    apiKeyEditText.setTypeface(typeface);
    appIdEditText.setTypeface(typeface);
    pub0EditText.setTypeface(typeface);
    getOffersButton.setTypeface(typeface);
  }

  @OnClick(R.id.activity_search_get_offers_button)
  void getOffers() {
//    if (uidEditText.getText().toString().trim().isEmpty() || apiKeyEditText.getText().toString().trim().isEmpty() || appIdEditText.getText().toString().trim().isEmpty()) {
//      Toast.makeText(getApplicationContext(), "Please, complete the fields", Toast.LENGTH_SHORT).show();
//    } else {
    progressDialog = ProgressDialog.show(this, "Loading", "Please wait...", true);
    GetAdvertisingIdInfoTask getAdvertisingIdInfoTask = new GetAdvertisingIdInfoTask(getApplicationContext());
    getAdvertisingIdInfoTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//    }
  }

  public void onEvent(GetAdvertisingIdInfoTaskResultEvent getAdvertisingIdInfoTaskResultEvent) {
    String advertisingId = getAdvertisingIdInfoTaskResultEvent.getAdvertisingId();
    String trackingEnabled = String.valueOf(getAdvertisingIdInfoTaskResultEvent.isTrackingEnabled());
    String unixTimestamp = String.valueOf(System.currentTimeMillis() / 1000L);
    List<NameValuePair> parameters = new ArrayList<>();
    parameters.add(new BasicNameValuePair("format", "json"));
    parameters.add(new BasicNameValuePair("appid", "2070"));
    parameters.add(new BasicNameValuePair("uid", "spiderman"));
    parameters.add(new BasicNameValuePair("locale", getLanguage()));
    parameters.add(new BasicNameValuePair("os_version", Build.VERSION.RELEASE));
    parameters.add(new BasicNameValuePair("timestamp", String.valueOf(unixTimestamp)));
    parameters.add(new BasicNameValuePair("google_ad_id", advertisingId));
    parameters.add(new BasicNameValuePair("google_ad_id_limited_tracking_enabled", String.valueOf(trackingEnabled)));
    parameters.add(new BasicNameValuePair("pub0", "test"));
    GetOffersTask getOffersTask = new GetOffersTask(getApplicationContext(), parameters, getString(R.string.api_key)); // TODO get api key from input
    getOffersTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
  }

  public void onEvent(GetOffersTaskResultEvent getOffersTaskResultEvent) {
    progressDialog.hide();
    if (getOffersTaskResultEvent.getServerResponse() == GetOffersTask.SERVER_SUCCESS) {
      ResultsActivity.startNewActivity(getApplicationContext(), getOffersTaskResultEvent.getOfferTaskResult().getOffers());
    } else if (getOffersTaskResultEvent.getServerResponse() == GetOffersTask.SIGNATURE_FAILED) {
      Toast.makeText(getApplicationContext(), getString(R.string.activity_search_bad_signature), Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(getApplicationContext(), getString(R.string.activity_search_error), Toast.LENGTH_SHORT).show();
    }
  }

  private String getLanguage() {
    Locale locale = getResources().getConfiguration().locale;
    return locale.getLanguage().toLowerCase();
  }

  @Override
  protected void onResume() {
    super.onResume();
    EventBus.getDefault().registerSticky(this);
  }

  @Override
  protected void onPause() {
    super.onPause();
    EventBus.getDefault().unregister(this);
  }
}
