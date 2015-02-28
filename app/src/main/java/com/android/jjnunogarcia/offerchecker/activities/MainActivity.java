package com.android.jjnunogarcia.offerchecker.activities;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.android.jjnunogarcia.offerchecker.R;
import com.android.jjnunogarcia.offerchecker.backend.requests.GetOffersTask;
import com.android.jjnunogarcia.offerchecker.eventbus.GetAdvertisingIdInfoTaskResultEvent;
import com.android.jjnunogarcia.offerchecker.threads.GetAdvertisingIdInfoTask;
import de.greenrobot.event.EventBus;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends ActionBarActivity {
  private static final String TAG = MainActivity.class.getSimpleName();

  @InjectView(R.id.uid_edit_text)
  EditText uidEditText;
  @InjectView(R.id.api_key_edit_text)
  EditText apiKeyEditText;
  @InjectView(R.id.appid_edit_text)
  EditText appIdEditText;
  @InjectView(R.id.pub0_edit_text)
  EditText pub0EditText;
  @InjectView(R.id.get_offers_button)
  Button   getOffersButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);
  }

  @OnClick(R.id.get_offers_button)
  void getOffers() {
    GetAdvertisingIdInfoTask getAdvertisingIdInfoTask = new GetAdvertisingIdInfoTask(getApplicationContext());
    getAdvertisingIdInfoTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
    GetOffersTask getOffersTask = new GetOffersTask(getApplicationContext(), parameters);
    getOffersTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
