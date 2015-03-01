package com.android.jjnunogarcia.offerchecker;

import android.app.Application;
import com.squareup.picasso.Picasso;

import java.util.concurrent.Executors;

/**
 * User: Jesús
 * Date: 01/03/15
 *
 * @author j.nuno@klara.com
 */
public class OfferCheckerApplication extends Application {
  public static final String TAG = OfferCheckerApplication.class.getSimpleName();

  private Picasso picasso;

  @Override
  public void onCreate() {
    super.onCreate();
    picasso = new Picasso.Builder(getApplicationContext()).executor(Executors.newSingleThreadExecutor()).build();
  }

  public Picasso getPicasso() {
    return picasso;
  }
}
