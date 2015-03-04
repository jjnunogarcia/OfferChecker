package com.android.jjnunogarcia.offerchecker.threads;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.android.jjnunogarcia.offerchecker.eventbus.GetAdvertisingIdInfoTaskResultEvent;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import de.greenrobot.event.EventBus;

import java.io.IOException;

/**
 * User: jesus
 * Date: 26/02/15
 *
 * @author jjnunogarcia@gmail.com
 */
public class GetAdvertisingIdInfoTask extends AsyncTask<Void, Void, GetAdvertisingIdInfoTaskResultEvent> {
  private static final String TAG     = GetAdvertisingIdInfoTask.class.getSimpleName();
  public static final  int    SUCCESS = 0;
  public static final  int    ERROR   = -1;

  private Context                             context;
  private GetAdvertisingIdInfoTaskResultEvent getAdvertisingIdInfoTaskResultEvent;

  public GetAdvertisingIdInfoTask(Context context) {
    this.context = context;
    getAdvertisingIdInfoTaskResultEvent = new GetAdvertisingIdInfoTaskResultEvent(ERROR);
  }

  @Override
  protected GetAdvertisingIdInfoTaskResultEvent doInBackground(Void... params) {
    try {
      AdvertisingIdClient.Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
      getAdvertisingIdInfoTaskResultEvent.setAdvertisingId(adInfo.getId());
      getAdvertisingIdInfoTaskResultEvent.setTrackingEnabled(adInfo.isLimitAdTrackingEnabled());
      getAdvertisingIdInfoTaskResultEvent.setMessage(SUCCESS);
    } catch (IOException e) {
      Log.e(TAG, "Error: Unrecoverable error connecting to Google Play services", e);
    } catch (GooglePlayServicesNotAvailableException e) {
      Log.e(TAG, "Error: Google Play services is not available entirely", e);
    } catch (GooglePlayServicesRepairableException e) {
      Log.e(TAG, "Error: Connection to Google Play services unsuccessful", e);
    }
    return getAdvertisingIdInfoTaskResultEvent;
  }

  @Override
  protected void onPostExecute(GetAdvertisingIdInfoTaskResultEvent getAdvertisingIdInfoTaskResultEvent) {
    super.onPostExecute(getAdvertisingIdInfoTaskResultEvent);
    EventBus.getDefault().post(getAdvertisingIdInfoTaskResultEvent);
  }
}
