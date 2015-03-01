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
import com.android.jjnunogarcia.offerchecker.model.jsonparsing.OfferType;

import java.util.ArrayList;
import java.util.List;

/**
 * User: jesus
 * Date: 28/02/15
 *
 * @author j.nuno@klara.com
 */
public class OfferTypesListAdapter extends BaseAdapter {
  private Context         context;
  private List<OfferType> offerTypes;

  public OfferTypesListAdapter(Context context) {
    this.context = context;
    offerTypes = new ArrayList<>();
  }

  public OfferTypesListAdapter(Context context, List<OfferType> offerTypes) {
    this.context = context;
    this.offerTypes = offerTypes;
  }

  public void setContent(List<OfferType> offerTypes) {
    this.offerTypes = offerTypes;
    notifyDataSetChanged();
  }

  public void addContent(OfferType offer) {
    offerTypes.add(offer);
    notifyDataSetChanged();
  }

  @Override
  public int getCount() {
    return offerTypes.size();
  }

  @Override
  public OfferType getItem(int position) {
    return offerTypes.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view = convertView;
    OfferTypeViewHolder offerTypeViewHolder;

    if (view == null) {
      view = LayoutInflater.from(context).inflate(R.layout.offer_type_row, parent, false);
      offerTypeViewHolder = new OfferTypeViewHolder(view);
      offerTypeViewHolder.id.setTypeface(Typeface.createFromAsset(context.getAssets(), Utils.FONT_PATH_FABRICA));
      offerTypeViewHolder.readable.setTypeface(Typeface.createFromAsset(context.getAssets(), Utils.FONT_PATH_FABRICA));
      view.setTag(offerTypeViewHolder);
    } else {
      offerTypeViewHolder = (OfferTypeViewHolder) view.getTag();
    }

    OfferType offerType = offerTypes.get(position);
    offerTypeViewHolder.id.setText(String.valueOf(offerType.getOfferTypeId()));
    offerTypeViewHolder.readable.setText(offerType.getReadable());

    return view;
  }

  static class OfferTypeViewHolder {
    @InjectView(R.id.offer_type_row_id)
    TextView id;
    @InjectView(R.id.offer_type_row_readable)
    TextView readable;

    OfferTypeViewHolder(View view) {
      ButterKnife.inject(this, view);
    }
  }
}
