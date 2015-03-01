package com.android.jjnunogarcia.offerchecker.helpers;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.squareup.picasso.Callback;

/**
 * User: jesus
 * Date: 01/03/15
 *
 * @author j.nuno@klara.com
 */
public class PicassoUrlCallback implements Callback {
  public static final String TAG = PicassoUrlCallback.class.getSimpleName();

  private ImageView   image;
  private ProgressBar progressBar;

  public PicassoUrlCallback(ImageView image, ProgressBar progressBar) {
    this.image = image;
    this.progressBar = progressBar;
    progressBar.setVisibility(View.VISIBLE);
    image.setVisibility(View.GONE);
  }

  @Override
  public void onSuccess() {
    progressBar.setVisibility(View.GONE);
    image.setVisibility(View.VISIBLE);
  }

  @Override
  public void onError() {
    Log.e(TAG, "Error setting image");
    progressBar.setVisibility(View.GONE);
    image.setVisibility(View.VISIBLE);
  }
}
