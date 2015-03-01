package com.android.jjnunogarcia.offerchecker.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.android.jjnunogarcia.offerchecker.OfferCheckerApplication;
import com.android.jjnunogarcia.offerchecker.R;
import com.android.jjnunogarcia.offerchecker.helpers.PicassoUrlCallback;
import com.android.jjnunogarcia.offerchecker.helpers.Utils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class DisplayImageActivity extends ActionBarActivity {
  public static final String TAG           = DisplayImageActivity.class.getSimpleName();
  public static final String KEY_IMAGE_URL = "key_image_url";

  @InjectView(R.id.activity_display_image_image)
  ImageView   image;
  @InjectView(R.id.activity_display_image_image_progress_bar)
  ProgressBar imageProgressBar;

  private String imageUrl;

  public static void startNewActivity(Context context, String imageUrl) {
    Intent intent = new Intent(context, DisplayImageActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    Bundle arguments = new Bundle();
    arguments.putString(KEY_IMAGE_URL, imageUrl);
    intent.putExtras(arguments);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_image);
    ButterKnife.inject(this);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    Bundle arguments = getIntent().getExtras();

    if (arguments != null && arguments.containsKey(KEY_IMAGE_URL)) {
      imageUrl = arguments.getString(KEY_IMAGE_URL);
      setImage();
    }
  }

  private void setImage() {
    if (TextUtils.isEmpty(imageUrl)) {
      imageProgressBar.setVisibility(View.GONE);
      Toast.makeText(getApplicationContext(), getString(R.string.activity_display_image_error), Toast.LENGTH_SHORT).show();
    } else {
      Picasso picasso = ((OfferCheckerApplication) getApplication()).getPicasso();
      Callback picassoCallback = new PicassoUrlCallback(image, imageProgressBar);
      int screenSize = Utils.getScreenHeight(this);
      picasso.load(imageUrl).resize(screenSize, screenSize).centerInside().into(image, picassoCallback);
    }
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
