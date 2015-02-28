package com.android.jjnunogarcia.offerchecker.backend.requests;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.android.jjnunogarcia.offerchecker.R;
import com.android.jjnunogarcia.offerchecker.eventbus.requests.GetOffersTaskResultEvent;
import com.android.jjnunogarcia.offerchecker.helpers.String2SHA1;
import com.android.jjnunogarcia.offerchecker.model.jsonparsing.OfferTaskResult;
import com.google.gson.Gson;
import de.greenrobot.event.EventBus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * User: Jesús
 * Date: 25/02/14
 *
 * @author j.nuno@klara.com
 */
public class GetOffersTask extends AsyncTask<Void, Void, GetOffersTaskResultEvent> implements ResponseHandler<GetOffersTaskResultEvent> {
  private static final String TAG                = GetOffersTask.class.getSimpleName();
  public static final  int    CONNECTION_TIMEOUT = 10000;
  public static final  int    SERVER_NOT_FOUND   = 404;
  public static final  int    SERVER_SUCCESS     = 200;

  private Context                  context;
  private List<NameValuePair>      parameters;
  private GetOffersTaskResultEvent getOffersTaskResultEvent;

  public GetOffersTask(Context context, List<NameValuePair> parameters) {
    this.context = context;
    this.parameters = new ArrayList<>(parameters);
    getOffersTaskResultEvent = new GetOffersTaskResultEvent(SERVER_NOT_FOUND);
  }

  @Override
  protected GetOffersTaskResultEvent doInBackground(Void... params) {
    parameters.add(new BasicNameValuePair("hashkey", calculateHashKey()));
    String paramString = URLEncodedUtils.format(parameters, HTTP.UTF_8);
    String baseUrl = context.getString(R.string.offer_url);
    baseUrl += paramString;
    HttpClient httpClient = new DefaultHttpClient();
    HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), CONNECTION_TIMEOUT);

    try {
      HttpUriRequest request = new HttpGet(baseUrl);
      return httpClient.execute(request, this);
    } catch (ClientProtocolException e) {
      Log.e(TAG, "Client Protocol exception", e);
    } catch (IOException e) {
      Log.e(TAG, "Input/Output exception", e);
    }

    return getOffersTaskResultEvent;
  }

  @Override
  public GetOffersTaskResultEvent handleResponse(HttpResponse httpResponse) throws IOException {
    HttpEntity entity = httpResponse.getEntity();
    StatusLine statusLine = httpResponse.getStatusLine();

    if (entity != null && statusLine != null) {
      int statusCode = statusLine.getStatusCode();
      getOffersTaskResultEvent.setServerResponse(statusCode);
      String responseString = EntityUtils.toString(entity);
      getOffersTaskResultEvent.setServerMessage(responseString);
      if (statusCode == SERVER_SUCCESS) {
        Gson gson = new Gson();
        OfferTaskResult offerTaskResults = gson.fromJson(responseString, OfferTaskResult.class);
        getOffersTaskResultEvent.setOfferTaskResult(offerTaskResults);
      }
    }

    return getOffersTaskResultEvent;
  }

  @Override
  protected void onPostExecute(GetOffersTaskResultEvent getOffersTaskResultEvent) {
    super.onPostExecute(getOffersTaskResultEvent);
    EventBus.getDefault().post(getOffersTaskResultEvent);
  }

  private Comparator<NameValuePair> comp = new Comparator<NameValuePair>() {
    @Override
    public int compare(NameValuePair p1, NameValuePair p2) {
      return p1.getName().compareTo(p2.getName());
    }
  };

  private String calculateHashKey() {
    Collections.sort(parameters, comp);
    String params = URLEncodedUtils.format(parameters, HTTP.UTF_8) + "&" + context.getString(R.string.api_key);
    String hashKey = "";
    try {
      hashKey = String2SHA1.SHA1(params);
    } catch (NoSuchAlgorithmException e) {
      Log.e(TAG, "No algorithm exception", e);
    } catch (UnsupportedEncodingException e) {
      Log.e(TAG, "Unsupported encoding exception", e);
    }

    return hashKey;
  }
}
