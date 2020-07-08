package com.example.mybankapp.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import com.example.mybankapp.R;

import static android.app.Activity.RESULT_OK;
import static com.example.mybankapp.constants.Constants.PARAM_BROADCAST_NAME;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_NAME;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_TO_REQUEST_FRAGMENT_MONEY_COUNT;
import static com.example.mybankapp.constants.Constants.PARAM_SWITCH_TYPE;
import static com.example.mybankapp.constants.Constants.PARAM_SWITCH_TYPE_REQUEST_ADDED;

public class AddRequestFragment extends Fragment {
    private EditText creditCount;
    private EditText timeCount;
    private EditText firstName;
    private EditText secondName;
    private EditText thirdName;
    private EditText serialPassport;
    private CheckBox personalHandleAccepted;
    private ImageView takedPhotoImage;
    private boolean btnClicked = false;
    private String chosenBankName;
    private static final int CAMERA_REQUEST = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_request_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Button takePhotoButton = view.findViewById(R.id.take_photo_button);
        takedPhotoImage = view.findViewById(R.id.taked_photo_view);
        Button createRequestButton = view.findViewById(R.id.create_request_button);
        creditCount = view.findViewById(R.id.enter_count_editText);
        timeCount = view.findViewById(R.id.enter_time_editText);
        firstName = view.findViewById(R.id.enter_first_name);
        secondName = view.findViewById(R.id.enter_last_name);
        thirdName = view.findViewById(R.id.enter_dad_name);
        serialPassport = view.findViewById(R.id.enter_passport_number);
        personalHandleAccepted = view.findViewById(R.id.personal_data_handle_accept_checkBox);

        Bundle args = getArguments();
        if (args == null) {
            args = savedInstanceState;
        }

        if (args != null && args.containsKey(PARAM_BUNDLE_NAME)) {
            chosenBankName = args.getString(PARAM_BUNDLE_NAME);
        }

        createRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!creditCount.getText().toString().equals("") && !timeCount.getText().toString().equals("") &&
                        !firstName.getText().toString().equals("") && !secondName.getText().toString().equals("") &&
                        !thirdName.getText().toString().equals("") && !serialPassport.getText().toString().equals("") &&
                        personalHandleAccepted.isChecked() && btnClicked) {
                    Intent intent = new Intent(PARAM_BROADCAST_NAME);
                    intent.putExtra(PARAM_SWITCH_TYPE, PARAM_SWITCH_TYPE_REQUEST_ADDED);
                    intent.putExtra(PARAM_BUNDLE_NAME, chosenBankName);
                    intent.putExtra(PARAM_BUNDLE_TO_REQUEST_FRAGMENT_MONEY_COUNT, creditCount.getText().toString());
                    intent.putExtra("time", Integer.parseInt(timeCount.getText().toString()));
                    intent.putExtra("first name", firstName.getText().toString());
                    intent.putExtra("second name", secondName.getText().toString());
                    intent.putExtra("third name", thirdName.getText().toString());
                    intent.putExtra("serial passport", serialPassport.getText().toString());
                    if (getActivity() != null)
                        getActivity().sendBroadcast(intent);
                } else {
                    Toast.makeText(getContext(), R.string.add_request_fragment_button_description, Toast.LENGTH_SHORT).show();
                }
            }
        });

        ConstraintLayout constraintLayout = null;
        final ConstraintLayout finalConstraintLayout = constraintLayout;
        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClicked = true;
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                takedPhotoImage.setVisibility(View.VISIBLE);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(finalConstraintLayout);
                constraintSet.clear(R.id.create_request_button, ConstraintSet.TOP);
                constraintSet.connect(R.id.create_request_button, ConstraintSet.TOP, R.id.taked_photo_view, ConstraintSet.BOTTOM);


            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap thumbnailBitmap = (Bitmap) data.getExtras().get("data");
            takedPhotoImage.setImageBitmap(thumbnailBitmap);
        }
    }
}
