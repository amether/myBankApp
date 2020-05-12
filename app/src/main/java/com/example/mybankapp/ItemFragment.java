package com.example.mybankapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.example.mybankapp.Constants.PARAM_BUNDLE_ADRESS;
import static com.example.mybankapp.Constants.PARAM_BUNDLE_CITY;
import static com.example.mybankapp.Constants.PARAM_BUNDLE_IMG;
import static com.example.mybankapp.Constants.PARAM_BUNDLE_LICENSE;
import static com.example.mybankapp.Constants.PARAM_BUNDLE_MAP;
import static com.example.mybankapp.Constants.PARAM_BUNDLE_NAME;
import static com.example.mybankapp.Constants.PARAM_BUNDLE_OGRN;
import static com.example.mybankapp.Constants.PARAM_BUNDLE_SITE;

public class ItemFragment extends Fragment {
    private ImageView bankIconImageView;
    private TextView bankNameTextView;
    private TextView varBankLicenseTextView;
    private TextView varBankOgrnTextView;
    private TextView varBankAdressTextView;
    private Button toSiteButton;
    private Button toMapButton;

    private int imgPath;
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
        bankIconImageView = view.findViewById(R.id.bankIconImageView);
        bankNameTextView = view.findViewById(R.id.bankNameTextView);
        varBankLicenseTextView = view.findViewById(R.id.variableLicenseTextView);
        varBankOgrnTextView = view.findViewById(R.id.variableOgrnTextView);
        varBankAdressTextView = view.findViewById(R.id.variableAdressTextView);
        toSiteButton = view.findViewById(R.id.toSiteButton);
        toMapButton = view.findViewById(R.id.toMapButton);
        bankIconImageView = view.findViewById(R.id.bankIconImageView);


        Bundle args = getArguments();
        if (args == null) {
            args = savedInstanceState;
        }
        if (args != null && args.containsKey(PARAM_BUNDLE_NAME) && args.containsKey(PARAM_BUNDLE_OGRN) && args.containsKey(PARAM_BUNDLE_IMG)
                && args.containsKey(PARAM_BUNDLE_LICENSE) && args.containsKey(PARAM_BUNDLE_ADRESS) && args.containsKey(PARAM_BUNDLE_SITE) &&
                args.containsKey(PARAM_BUNDLE_MAP)){
            bankNameTextView.setText(args.getString(PARAM_BUNDLE_NAME));
            varBankOgrnTextView.setText(args.getString(PARAM_BUNDLE_OGRN));
            varBankLicenseTextView.setText(args.getString(PARAM_BUNDLE_LICENSE));
            varBankAdressTextView.setText(args.getString(PARAM_BUNDLE_ADRESS));
            imgPath = args.getInt(PARAM_BUNDLE_IMG);
            bankMap = args.getString(PARAM_BUNDLE_MAP);
            bankSite = args.getString(PARAM_BUNDLE_SITE);
            city = args.getString(PARAM_BUNDLE_CITY);
            bankIconImageView.setImageResource(imgPath);
        }

        toSiteButton.setOnClickListener(buttonAcceptOnClickListener);
        toMapButton.setOnClickListener(buttonAcceptOnClickListener);

    }

    private View.OnClickListener buttonAcceptOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            switch (b.getId()){
                case R.id.toSiteButton:{
                    Uri webpage = Uri.parse(bankSite);
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                    startActivity(webIntent);
                }
                break;
                case R.id.toMapButton:{
                    String bankLocation = bankMap + city;
                    Uri location = Uri.parse(bankLocation);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                    startActivity(mapIntent);
                }
                break;
            }
        }
    };
}
