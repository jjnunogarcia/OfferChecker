package com.android.jjnunogarcia.offerchecker.backend;

import android.content.Context;
import android.os.AsyncTask;
import com.android.jjnunogarcia.offerchecker.backend.requests.GetOffersTask;
import com.android.jjnunogarcia.offerchecker.backend.requests.GetAdvertisingIdInfoTask;
import org.apache.http.NameValuePair;

import java.util.List;

/**
 * User: jesus
 * Date: 26/02/15
 *
 * @author jjnunogarcia@gmail.com
 */
public final class BackendEndPoint {

  public static GetAdvertisingIdInfoTask getAdvertisingIdInfoTask(Context context) {
    GetAdvertisingIdInfoTask getAdvertisingIdInfoTask = new GetAdvertisingIdInfoTask(context);
    getAdvertisingIdInfoTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    return getAdvertisingIdInfoTask;
  }

  public static GetOffersTask getOffersTask(Context context, List<NameValuePair> parameters, String apiKey) {
    GetOffersTask getOffersTask = new GetOffersTask(context, parameters, apiKey);
    getOffersTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    return getOffersTask;
  }
}
