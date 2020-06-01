package com.example.mybankapp;

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
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.app.Activity.RESULT_OK;
import static com.example.mybankapp.Constants.PARAM_SWITCH_TYPE;
import static com.example.mybankapp.Constants.PARAM_SWITCH_TYPE_REQUEST_ADDED;

public class AddRequestFragment extends Fragment {
    Button takePhotoButton;
    Button createRequestButton;
    EditText creditCount;
    EditText timeCount;
    EditText firstName;
    EditText secondName;
    EditText thirdName;
    Switch nationality;
    EditText serialPassport;
    CheckBox personalHandleAccepted;
    ImageView takedPhotoImage;
    boolean btnClicked = false;
    String chosenBankName;
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
        takePhotoButton = view.findViewById(R.id.take_photo_button);
        takedPhotoImage = view.findViewById(R.id.taked_photo_view);
        createRequestButton = view.findViewById(R.id.create_request_button);
        creditCount = view.findViewById(R.id.enter_count_editText);
        timeCount = view.findViewById(R.id.enter_time_editText);
        firstName = view.findViewById(R.id.enter_first_name);
        secondName = view.findViewById(R.id.enter_last_name);
        thirdName = view.findViewById(R.id.enter_dad_name);
        nationality = view.findViewById(R.id.nationality_switch);
        serialPassport = view.findViewById(R.id.enter_passport_number);
        personalHandleAccepted = view.findViewById(R.id.personal_data_handle_accept_checkBox);

        Bundle args = getArguments();
        if (args == null) {
            args = savedInstanceState;
        }

        if (args != null && args.containsKey("bank name")){
            chosenBankName = args.getString("bank name");
        }

        createRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!creditCount.getText().toString().equals("") && !timeCount.getText().toString().equals("") &&
                        !firstName.getText().toString().equals("") && !secondName.getText().toString().equals("") &&
                        !thirdName.getText().toString().equals("") && !serialPassport.getText().toString().equals("") &&
                        personalHandleAccepted.isChecked() && btnClicked) {
                    //Если все ок, то .....
                    Intent intent = new Intent("myDumbBroadcast");
                    intent.putExtra(PARAM_SWITCH_TYPE, PARAM_SWITCH_TYPE_REQUEST_ADDED);
                    intent.putExtra("bank name", chosenBankName);
                    intent.putExtra("credit count", creditCount.getText().toString());
                    intent.putExtra("time", Integer.parseInt(timeCount.getText().toString()));
                    intent.putExtra("first name", firstName.getText().toString());
                    intent.putExtra("second name", secondName.getText().toString());
                    intent.putExtra("third name", thirdName.getText().toString());
                    intent.putExtra("serial passport", serialPassport.getText().toString());
                    if (getActivity() != null)
                        getActivity().sendBroadcast(intent);
                } else {
                    Toast.makeText(getContext(),"Заполните все поля и сделайте фото", Toast.LENGTH_SHORT).show();
                }
            }
        });

        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClicked = true;
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
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
