package com.android.jjnunogarcia.offerchecker.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.android.jjnunogarcia.offerchecker.R;
import com.android.jjnunogarcia.offerchecker.helpers.Utils;
import com.android.jjnunogarcia.offerchecker.model.jsonparsing.Offer;

import java.util.ArrayList;
import java.util.List;

/**
 * User: jesus
 * Date: 28/02/15
 *
 * @author jjnunogarcia@gmail.com
 */
public class OffersListAdapter extends BaseAdapter {
  private Context     context;
  private List<Offer> offers;

  public OffersListAdapter(Context context) {
    this.context = context;
    offers = new ArrayList<>();
  }

  public OffersListAdapter(Context context, List<Offer> offers) {
    this.context = context;
    this.offers = offers;
  }

  public void setContent(List<Offer> offers) {
    this.offers = offers;
    notifyDataSetChanged();
  }

  public void addContent(Offer offer) {
    offers.add(offer);
    notifyDataSetChanged();
  }

  @Override
  public int getCount() {
    return offers.size();
  }

  @Override
  public Offer getItem(int position) {
    return offers.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view = convertView;
    OfferViewHolder offerViewHolder;

    if (view == null) {
      view = LayoutInflater.from(context).inflate(R.layout.offer_row, parent, false);
      offerViewHolder = new OfferViewHolder(view);
      offerViewHolder.title.setTypeface(Typeface.createFromAsset(context.getAssets(), Utils.FONT_PATH_FABRICA));
      offerViewHolder.teaser.setTypeface(Typeface.createFromAsset(context.getAssets(), Utils.FONT_PATH_FABRICA));
      view.setTag(offerViewHolder);
    } else {
      offerViewHolder = (OfferViewHolder) view.getTag();
    }

    Offer offer = offers.get(position);
    offerViewHolder.title.setText(offer.getTitle());
    offerViewHolder.teaser.setText(offer.getTeaser());

    return view;
  }

  static class OfferViewHolder {
    @InjectView(R.id.offer_row_title)
    TextView title;
    @InjectView(R.id.offer_row_teaser)
    TextView teaser;

    OfferViewHolder(View view) {
      ButterKnife.inject(this, view);
    }
  }
}
