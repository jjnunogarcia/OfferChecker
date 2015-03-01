package com.android.jjnunogarcia.offerchecker.dialogs;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.android.jjnunogarcia.offerchecker.R;
import com.android.jjnunogarcia.offerchecker.adapters.OfferTypesListAdapter;
import com.android.jjnunogarcia.offerchecker.helpers.Utils;
import com.android.jjnunogarcia.offerchecker.model.jsonparsing.OfferType;

import java.util.ArrayList;

/**
 * User: jesus
 * Date: 01/03/15
 *
 * @author j.nuno@klara.com
 */
public class OfferTypesDialog extends DialogFragment {
  public static final String TAG             = OfferTypesDialog.class.getSimpleName();
  public static final String KEY_OFFER_TYPES = "key_offer_types";

  @InjectView(R.id.offer_types_dialog_list_view)
  ListView listView;
  @InjectView(R.id.offer_types_dialog_button)
  Button   button;

  public static OfferTypesDialog newInstance(ArrayList<OfferType> offerTypes) {
    OfferTypesDialog offerTypesDialog = new OfferTypesDialog();
    Bundle arguments = new Bundle();
    arguments.putParcelableArrayList(KEY_OFFER_TYPES, offerTypes);
    offerTypesDialog.setArguments(arguments);
    return offerTypesDialog;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.offer_types_dialog, container, false);
    getDialog().setTitle(getString(R.string.offer_types_dialog_title));
    ButterKnife.inject(this, view);
    return view;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    setFonts();

    Bundle arguments = getArguments();

    if (arguments != null && arguments.containsKey(KEY_OFFER_TYPES)) {
      ArrayList<OfferType> offerTypes = arguments.getParcelableArrayList(KEY_OFFER_TYPES);
      ListAdapter offerTypesListAdapter = new OfferTypesListAdapter(getActivity().getApplicationContext(), offerTypes);
      listView.setAdapter(offerTypesListAdapter);
    }
  }

  private void setFonts() {
    Typeface typeface = Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(), Utils.FONT_PATH_FABRICA);
    button.setTypeface(typeface);
  }

  @OnClick(R.id.offer_types_dialog_button)
  void onOfferTypesDialogButtonClick() {
    dismiss();
  }
}
