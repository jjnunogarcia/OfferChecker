package com.android.jjnunogarcia.offerchecker.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.android.jjnunogarcia.offerchecker.R;
import com.android.jjnunogarcia.offerchecker.adapters.OffersListAdapter;
import com.android.jjnunogarcia.offerchecker.helpers.Utils;
import com.android.jjnunogarcia.offerchecker.model.jsonparsing.Offer;

import java.util.ArrayList;

/**
 * User: jesus
 * Date: 28/02/15
 *
 * @author j.nuno@klara.com
 */
public class ResultsActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
  private static final String TAG        = ResultsActivity.class.getSimpleName();
  private static final String KEY_OFFERS = "key_offers";

  @InjectView(R.id.activity_results_list_view)
  ListView listView;
  @InjectView(R.id.activity_results_no_offers_text)
  TextView noOffersText;

  private OffersListAdapter offersListAdapter;

  public static void startNewActivity(Context context, ArrayList<Offer> offers) {
    Intent intent = new Intent(context, ResultsActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    Bundle arguments = new Bundle();
    arguments.putParcelableArrayList(KEY_OFFERS, offers);
    intent.putExtras(arguments);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_results);
    ButterKnife.inject(this);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setFonts();

    Bundle arguments = getIntent().getExtras();

    if (arguments != null && arguments.containsKey(KEY_OFFERS)) {
      ArrayList<Offer> offers = arguments.getParcelableArrayList(KEY_OFFERS);
      if (offers == null || offers.isEmpty()) {
        listView.setVisibility(View.GONE);
        noOffersText.setVisibility(View.VISIBLE);
      } else {
        listView.setVisibility(View.VISIBLE);
        noOffersText.setVisibility(View.GONE);
        offersListAdapter = new OffersListAdapter(getApplicationContext(), offers);
        listView.setAdapter(offersListAdapter);
        listView.setOnItemClickListener(this);
      }
    }
  }

  private void setFonts() {
    Typeface typeface = Typeface.createFromAsset(getAssets(), Utils.FONT_PATH_FABRICA);
    noOffersText.setTypeface(typeface);
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Offer offer = offersListAdapter.getItem(position);
    OfferDetailActivity.startNewActivity(getApplicationContext(), offer);
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
