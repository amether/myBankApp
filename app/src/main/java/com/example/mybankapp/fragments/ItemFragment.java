package com.example.mybankapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mybankapp.R;

import static com.example.mybankapp.constants.Constants.PARAM_BROADCAST_NAME;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_ADRESS;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_CITY;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_IMG;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_LICENSE;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_MAP;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_NAME;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_OGRN;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_SITE;
import static com.example.mybankapp.constants.Constants.PARAM_SWITCH_TYPE;
import static com.example.mybankapp.constants.Constants.PARAM_SWITCH_TYPE_REQUEST_FRAGMENT;

public class ItemFragment extends Fragment {
    private TextView bankNameTextView;
    private String bankSite;
    private String bankMap;
    private String city;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView bankIconImageView = view.findViewById(R.id.bankIconImageView);
        bankNameTextView = view.findViewById(R.id.bankNameTextView);
        TextView varBankLicenseTextView = view.findViewById(R.id.variableLicenseTextView);
        TextView varBankOgrnTextView = view.findViewById(R.id.variableOgrnTextView);
        TextView varBankAdressTextView = view.findViewById(R.id.variableAdressTextView);
        Button toSiteButton = view.findViewById(R.id.toSiteButton);
        Button toMapButton = view.findViewById(R.id.toMapButton);
        Button addRequest = view.findViewById(R.id.add_request);


        Bundle args = getArguments();
        if (args == null) {
            args = savedInstanceState;
        }
        if (args != null && args.containsKey(PARAM_BUNDLE_NAME) && args.containsKey(PARAM_BUNDLE_OGRN) && args.containsKey(PARAM_BUNDLE_IMG)
                && args.containsKey(PARAM_BUNDLE_LICENSE) && args.containsKey(PARAM_BUNDLE_ADRESS) && args.containsKey(PARAM_BUNDLE_SITE) &&
                args.containsKey(PARAM_BUNDLE_MAP)) {
            bankNameTextView.setText(args.getString(PARAM_BUNDLE_NAME));
            varBankOgrnTextView.setText(args.getString(PARAM_BUNDLE_OGRN));
            varBankLicenseTextView.setText(args.getString(PARAM_BUNDLE_LICENSE));
            varBankAdressTextView.setText(args.getString(PARAM_BUNDLE_ADRESS));
            int imgPath = args.getInt(PARAM_BUNDLE_IMG);
            bankMap = args.getString(PARAM_BUNDLE_MAP);
            bankSite = args.getString(PARAM_BUNDLE_SITE);
            city = args.getString(PARAM_BUNDLE_CITY);
            bankIconImageView.setImageResource(imgPath);
        }

        toSiteButton.setOnClickListener(buttonAcceptOnClickListener);
        toMapButton.setOnClickListener(buttonAcceptOnClickListener);
        addRequest.setOnClickListener(buttonAcceptOnClickListener);

    }

    private View.OnClickListener buttonAcceptOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            switch (b.getId()) {
                case R.id.toSiteButton: {
                    Uri webpage = Uri.parse(bankSite);
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                    startActivity(webIntent);
                }
                break;
                case R.id.toMapButton: {
                    String bankLocation = bankMap + city;
                    Uri location = Uri.parse(bankLocation);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                    startActivity(mapIntent);
                }
                break;
                case R.id.add_request: {
                    Intent intent = new Intent(PARAM_BROADCAST_NAME);
                    intent.putExtra(PARAM_SWITCH_TYPE, PARAM_SWITCH_TYPE_REQUEST_FRAGMENT);
                    intent.putExtra(PARAM_BUNDLE_NAME, bankNameTextView.getText().toString());
                    if (getActivity() != null)
                        getActivity().sendBroadcast(intent);
                }
                break;
            }
        }
    };
}
