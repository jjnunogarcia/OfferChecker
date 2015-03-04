package com.android.jjnunogarcia.offerchecker.helpers;

import android.app.Activity;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

/**
 * User: jesus
 * Date: 01/03/15
 *
 * @author jjnunogarcia@gmail.com
 */
public final class Utils {
  public static final String FONT_PATH_FABRICA = "fonts/Fabrica.otf";

  public static void setTextBlockContent(TextView header, TextView content, CharSequence text) {
    if (TextUtils.isEmpty(text)) {
      header.setVisibility(View.GONE);
      content.setVisibility(View.GONE);
    } else {
      header.setVisibility(View.VISIBLE);
      content.setVisibility(View.VISIBLE);
      content.setText(text);
    }
  }

  public static int getScreenWidth(Activity activity) {
    Display display = activity.getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    return size.x;
  }

  public static int getScreenHeight(Activity activity) {
    Display display = activity.getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    return size.y;
  }
}
