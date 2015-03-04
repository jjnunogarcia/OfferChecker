package com.android.jjnunogarcia.offerchecker.activities;

import android.app.ProgressDialog;
import android.graphics.Typeface;
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
import com.android.jjnunogarcia.offerchecker.backend.BackendEndPoint;
import com.android.jjnunogarcia.offerchecker.backend.requests.GetOffersTask;
import com.android.jjnunogarcia.offerchecker.eventbus.requests.GetAdvertisingIdInfoTaskResultEvent;
import com.android.jjnunogarcia.offerchecker.eventbus.requests.GetOffersTaskResultEvent;
import com.android.jjnunogarcia.offerchecker.helpers.Utils;
import com.android.jjnunogarcia.offerchecker.backend.requests.GetAdvertisingIdInfoTask;
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
  @InjectView(R.id.activity_search_user_id_edit_text)
  EditText userIdEditText;
  @InjectView(R.id.activity_search_api_key_edit_text)
  EditText apiKeyEditText;
  @InjectView(R.id.activity_search_application_id_edit_text)
  EditText applicationIdEditText;
  @InjectView(R.id.activity_search_custom_parameters_edit_text)
  EditText customParametersEditText;
  @InjectView(R.id.activity_search_get_offers_button)
  Button   getOffersButton;
  @InjectView(R.id.activity_search_dummy_button)
  Button   dummyValuesButton;
  @InjectView(R.id.activity_search_clear_button)
  Button   clearButton;

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
    userIdEditText.setTypeface(typeface);
    apiKeyEditText.setTypeface(typeface);
    applicationIdEditText.setTypeface(typeface);
    customParametersEditText.setTypeface(typeface);
    getOffersButton.setTypeface(typeface);
    dummyValuesButton.setTypeface(typeface);
    clearButton.setTypeface(typeface);
  }

  @OnClick(R.id.activity_search_get_offers_button)
  void getOffers() {
    if (userIdEditText.getText().toString().trim().isEmpty() || apiKeyEditText.getText().toString().trim().isEmpty() || applicationIdEditText.getText().toString().trim().isEmpty()) {
      Toast.makeText(getApplicationContext(), getString(R.string.activity_search_complete_fields_message), Toast.LENGTH_SHORT).show();
    } else {
      progressDialog = ProgressDialog.show(this, getString(R.string.activity_search_progress_dialog_title), getString(R.string.activity_search_progress_dialog_body), true);
      BackendEndPoint.getAdvertisingIdInfoTask(getApplicationContext());
    }
  }

  @OnClick(R.id.activity_search_dummy_button)
  void insertDummyValues() {
    applicationIdEditText.setText(getString(R.string.application_id));
    userIdEditText.setText(getString(R.string.user_id));
    customParametersEditText.setText(getString(R.string.custom_parameters));
    apiKeyEditText.setText(getString(R.string.api_key));
  }

  @OnClick(R.id.activity_search_clear_button)
  void clearValues() {
    applicationIdEditText.setText("");
    userIdEditText.setText("");
    customParametersEditText.setText("");
    apiKeyEditText.setText("");
  }

  public void onEvent(GetAdvertisingIdInfoTaskResultEvent getAdvertisingIdInfoTaskResultEvent) {
    if (getAdvertisingIdInfoTaskResultEvent.getMessage() == GetAdvertisingIdInfoTask.SUCCESS) {
      List<NameValuePair> parameters = assembleCallParameters(getAdvertisingIdInfoTaskResultEvent);
      BackendEndPoint.getOffersTask(getApplicationContext(), parameters, apiKeyEditText.getText().toString().trim());
    } else {
      Toast.makeText(getApplicationContext(), getString(R.string.activity_search_play_services_error), Toast.LENGTH_SHORT).show();
    }
  }

  private List<NameValuePair> assembleCallParameters(GetAdvertisingIdInfoTaskResultEvent getAdvertisingIdInfoTaskResultEvent) {
    List<NameValuePair> parameters = new ArrayList<>();

    String advertisingId = getAdvertisingIdInfoTaskResultEvent.getAdvertisingId();
    String trackingEnabled = String.valueOf(getAdvertisingIdInfoTaskResultEvent.isTrackingEnabled());
    String unixTimestamp = String.valueOf(System.currentTimeMillis() / 1000L);

    parameters.add(new BasicNameValuePair("format", getString(R.string.format)));
    parameters.add(new BasicNameValuePair("appid", applicationIdEditText.getText().toString().trim()));
    parameters.add(new BasicNameValuePair("uid", userIdEditText.getText().toString().trim()));
    parameters.add(new BasicNameValuePair("locale", getLanguage()));
    parameters.add(new BasicNameValuePair("os_version", Build.VERSION.RELEASE));
    parameters.add(new BasicNameValuePair("timestamp", String.valueOf(unixTimestamp)));
    parameters.add(new BasicNameValuePair("google_ad_id", advertisingId));
    parameters.add(new BasicNameValuePair("google_ad_id_limited_tracking_enabled", String.valueOf(trackingEnabled)));
    parameters.add(new BasicNameValuePair("pub0", customParametersEditText.getText().toString().trim()));

    return parameters;
  }

  public void onEvent(GetOffersTaskResultEvent getOffersTaskResultEvent) {
    progressDialog.dismiss();
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
