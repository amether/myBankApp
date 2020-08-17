package com.example.mybankapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mybankapp.R;

import static com.example.mybankapp.constants.Constants.PARAM_BROADCAST_NAME;
import static com.example.mybankapp.constants.Constants.PARAM_FILTER_CHANGED;
import static com.example.mybankapp.constants.Constants.PARAM_SWITCH_TYPE;
import static com.example.mybankapp.constants.Constants.PARAM_SWITCH_TYPE_FILTER_FRAGMENT;
import static com.example.mybankapp.constants.Constants.PARAM_CITY;
import static com.example.mybankapp.constants.Constants.PARAM_CITY_STATUS;
import static com.example.mybankapp.constants.Constants.PARAM_CREDIT_CARD;
import static com.example.mybankapp.constants.Constants.PARAM_CREDIT_CASH;
import static com.example.mybankapp.constants.Constants.PARAM_DEBIT_CARD;
import static com.example.mybankapp.constants.Constants.PARAM_DEPOSIT;
import static com.example.mybankapp.constants.Constants.PARAM_FOR_FOREIGNERS;
import static com.example.mybankapp.constants.Constants.PARAM_FOR_PRIVATE_PERSON;
import static com.example.mybankapp.constants.Constants.PARAM_INSURANCE;
import static com.example.mybankapp.constants.Constants.PARAM_INVESTMENTS;
import static com.example.mybankapp.constants.Constants.PARAM_MORTGAGE;

public class FilterFragment extends Fragment {

    private RadioButton forPrivatePersonRadiobutton;
    private RadioButton forLegalRadiobutton;
    private RadioButton debitCard_Y;
    private RadioButton debitCard_N;
    private RadioButton creditCard_Y;
    private RadioButton creditCard_N;
    private RadioButton creditCash_Y;
    private RadioButton creditCash_N;
    private RadioButton mortgage_Y;
    private RadioButton mortgage_N;
    private RadioButton deposit_Y;
    private RadioButton deposit_N;
    private RadioButton investments_Y;
    private RadioButton investments_N;
    private RadioButton insurance_Y;
    private RadioButton insurance_N;
    private RadioButton forForeign_Y;
    private RadioButton forForeign_N;
    private EditText cityEdittext;
    private boolean acceptButtonClicked = false;

    private int sendPersonStatus;
    private int sendCreditCardStatus;
    private int sendDebitCardStatus;
    private int sendCreditCashStatus;
    private int sendMortgageStatus;
    private int sendDepositStatus;
    private int sendInvestmentStatus;
    private int sendInsuranceStatus;
    private int sendForeignStatus;
    private int sendCityStatus;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.filter_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        forPrivatePersonRadiobutton = view.findViewById(R.id.personal_radiobtn);
        forLegalRadiobutton = view.findViewById(R.id.legal_radiobtn);
        debitCard_Y = view.findViewById(R.id.debitCard_Y);
        debitCard_N = view.findViewById(R.id.debitCard_N);
        creditCard_Y = view.findViewById(R.id.creditCard_Y);
        creditCard_N = view.findViewById(R.id.creditCard_N);
        creditCash_Y = view.findViewById(R.id.creditCash_Y);
        creditCash_N = view.findViewById(R.id.creditCash_N);
        mortgage_Y = view.findViewById(R.id.mortgage_Y);
        mortgage_N = view.findViewById(R.id.mortgage_N);
        deposit_Y = view.findViewById(R.id.deposit_Y);
        deposit_N = view.findViewById(R.id.deposit_N);
        insurance_Y = view.findViewById(R.id.insurance_Y);
        insurance_N = view.findViewById(R.id.insurance_N);
        investments_Y = view.findViewById(R.id.investments_Y);
        investments_N = view.findViewById(R.id.investments_N);
        forForeign_Y = view.findViewById(R.id.forForeign_Y);
        forForeign_N = view.findViewById(R.id.forForeign_N);

        cityEdittext = view.findViewById(R.id.city_edittext);
        Button acceptButton = view.findViewById(R.id.acceptFilter_button);

        forPrivatePersonRadiobutton.setOnClickListener(radioButtonClickListener);
        forLegalRadiobutton.setOnClickListener(radioButtonClickListener);
        debitCard_Y.setOnClickListener(radioButtonClickListener);
        debitCard_N.setOnClickListener(radioButtonClickListener);
        creditCard_Y.setOnClickListener(radioButtonClickListener);
        creditCard_N.setOnClickListener(radioButtonClickListener);
        creditCash_Y.setOnClickListener(radioButtonClickListener);
        creditCash_N.setOnClickListener(radioButtonClickListener);
        mortgage_Y.setOnClickListener(radioButtonClickListener);
        mortgage_N.setOnClickListener(radioButtonClickListener);
        deposit_Y.setOnClickListener(radioButtonClickListener);
        deposit_N.setOnClickListener(radioButtonClickListener);
        insurance_Y.setOnClickListener(radioButtonClickListener);
        insurance_N.setOnClickListener(radioButtonClickListener);
        investments_Y.setOnClickListener(radioButtonClickListener);
        investments_N.setOnClickListener(radioButtonClickListener);
        forForeign_Y.setOnClickListener(radioButtonClickListener);
        forForeign_N.setOnClickListener(radioButtonClickListener);
        acceptButton.setOnClickListener(buttonAcceptOnClickListener);

    }

    private View.OnClickListener buttonAcceptOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            acceptButtonClicked = true;
            searchFilter();
            onPause();
        }
    };

    private View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rb = (RadioButton) v;
            switch (rb.getId()) {
                case R.id.personal_radiobtn: {
                    forPrivatePersonRadiobutton.setChecked(true);
                    forLegalRadiobutton.setChecked(false);
                }
                break;
                case R.id.legal_radiobtn: {
                    forPrivatePersonRadiobutton.setChecked(false);
                    forLegalRadiobutton.setChecked(true);
                }
                break;
                case R.id.creditCard_Y: {
                    creditCard_Y.setChecked(true);
                    creditCard_N.setChecked(false);
                }
                break;
                case R.id.creditCard_N: {
                    creditCard_N.setChecked(true);
                    creditCard_Y.setChecked(false);
                }
                break;
                case R.id.debitCard_Y: {
                    debitCard_Y.setChecked(true);
                    debitCard_N.setChecked(false);
                }
                break;
                case R.id.debitCard_N: {
                    debitCard_N.setChecked(true);
                    debitCard_Y.setChecked(false);
                }
                break;
                case R.id.creditCash_Y: {
                    creditCash_Y.setChecked(true);
                    creditCash_N.setChecked(false);
                }
                break;
                case R.id.creditCash_N: {
                    creditCash_N.setChecked(true);
                    creditCash_Y.setChecked(false);
                }
                break;
                case R.id.mortgage_Y: {
                    mortgage_Y.setChecked(true);
                    mortgage_N.setChecked(false);
                }
                break;
                case R.id.mortgage_N: {
                    mortgage_N.setChecked(true);
                    mortgage_Y.setChecked(false);
                }
                break;
                case R.id.deposit_Y: {
                    deposit_Y.setChecked(true);
                    deposit_N.setChecked(false);
                }
                break;
                case R.id.deposit_N: {
                    deposit_N.setChecked(true);
                    deposit_Y.setChecked(false);
                }
                break;
                case R.id.investments_Y: {
                    investments_Y.setChecked(true);
                    investments_N.setChecked(false);
                }
                break;
                case R.id.investments_N: {
                    investments_N.setChecked(true);
                    investments_Y.setChecked(false);
                }
                break;
                case R.id.insurance_Y: {
                    insurance_Y.setChecked(true);
                    insurance_N.setChecked(false);
                }
                break;
                case R.id.insurance_N: {
                    insurance_N.setChecked(true);
                    insurance_Y.setChecked(false);
                }
                break;
                case R.id.forForeign_Y: {
                    forForeign_Y.setChecked(true);
                    forForeign_N.setChecked(false);
                }
                break;
                case R.id.forForeign_N: {
                    forForeign_N.setChecked(true);
                    forForeign_Y.setChecked(false);
                }
                break;

                default:
                    break;
            }
        }
    };

    private void searchFilter() {

        if (forPrivatePersonRadiobutton.isChecked()) {
            sendPersonStatus = 1;
        } else {
            if (forLegalRadiobutton.isChecked()) {
                sendPersonStatus = 2;
            } else {
                sendPersonStatus = 0;
            }
        }

        if (creditCard_Y.isChecked()) {
            sendCreditCardStatus = 1;
        } else {
            if (creditCard_N.isChecked()) {
                sendCreditCardStatus = 2;
            } else {
                sendCreditCardStatus = 0;
            }
        }

        if (creditCash_Y.isChecked()) {
            sendCreditCashStatus = 1;
        } else {
            if (creditCash_N.isChecked()) {
                sendCreditCashStatus = 2;
            } else {
                sendCreditCashStatus = 0;
            }
        }

        if (deposit_Y.isChecked()) {
            sendDepositStatus = 1;
        } else {
            if (deposit_N.isChecked()) {
                sendDepositStatus = 2;
            } else {
                sendDepositStatus = 0;
            }
        }

        if (debitCard_Y.isChecked()) {
            sendDebitCardStatus = 1;
        } else {
            if (debitCard_N.isChecked()) {
                sendDebitCardStatus = 2;
            } else {
                sendDebitCardStatus = 0;
            }
        }

        if (mortgage_Y.isChecked()) {
            sendMortgageStatus = 1;
        } else {
            if (mortgage_N.isChecked()) {
                sendMortgageStatus = 2;
            } else {
                sendMortgageStatus = 0;
            }
        }

        if (investments_Y.isChecked()) {
            sendInvestmentStatus = 1;
        } else {
            if (investments_N.isChecked()) {
                sendInvestmentStatus = 2;
            } else {
                sendInvestmentStatus = 0;
            }
        }

        if (insurance_Y.isChecked()) {
            sendInsuranceStatus = 1;
        } else {
            if (insurance_N.isChecked()) {
                sendInsuranceStatus = 2;
            } else {
                sendInsuranceStatus = 0;
            }
        }

        if (forForeign_Y.isChecked()) {
            sendForeignStatus = 1;
        } else {
            if (forForeign_N.isChecked()) {
                sendForeignStatus = 2;
            } else {
                sendForeignStatus = 0;
            }
        }

        if (cityEdittext.getText().toString().equals("")) {
            sendCityStatus = 0;
        } else {
            sendCityStatus = 1;
        }
    }

    @Override
    public void onPause() {
        Intent intent = new Intent(PARAM_BROADCAST_NAME);
        intent.putExtra(PARAM_SWITCH_TYPE, PARAM_SWITCH_TYPE_FILTER_FRAGMENT);
        if (acceptButtonClicked) {
            intent.putExtra(PARAM_DEBIT_CARD, sendDebitCardStatus);
            intent.putExtra(PARAM_CREDIT_CARD, sendCreditCardStatus);
            intent.putExtra(PARAM_CREDIT_CASH, sendCreditCashStatus);
            intent.putExtra(PARAM_FOR_FOREIGNERS, sendForeignStatus);
            intent.putExtra(PARAM_MORTGAGE, sendMortgageStatus);
            intent.putExtra(PARAM_INVESTMENTS, sendInvestmentStatus);
            intent.putExtra(PARAM_DEPOSIT, sendDepositStatus);
            intent.putExtra(PARAM_INSURANCE, sendInsuranceStatus);
            intent.putExtra(PARAM_FOR_PRIVATE_PERSON, sendPersonStatus);
            intent.putExtra(PARAM_CITY_STATUS, sendCityStatus);
            intent.putExtra(PARAM_CITY, cityEdittext.getText().toString());
            Toast.makeText(getActivity(), "Фильтр отправил интент", Toast.LENGTH_SHORT).show();
        }
        intent.putExtra(PARAM_FILTER_CHANGED, acceptButtonClicked);
        acceptButtonClicked = false;
        if (getActivity() != null)
            getActivity().sendBroadcast(intent);
        super.onPause();
    }
}
