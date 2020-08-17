package com.example.mybankapp.bcreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.mybankapp.R;
import com.example.mybankapp.activities.MainActivity;
import com.example.mybankapp.fragments.ListFragment;
import com.example.mybankapp.interfaces.MyBroadcastListener;
import com.example.mybankapp.models.BankClass;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_NAME;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_TO_REQUEST_FRAGMENT_MONEY_COUNT;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_TO_REQUEST_FRAGMENT_TIME;
import static com.example.mybankapp.constants.Constants.PARAM_CITY;
import static com.example.mybankapp.constants.Constants.PARAM_CREDIT_CARD;
import static com.example.mybankapp.constants.Constants.PARAM_CREDIT_CASH;
import static com.example.mybankapp.constants.Constants.PARAM_DEBIT_CARD;
import static com.example.mybankapp.constants.Constants.PARAM_DEFAULT;
import static com.example.mybankapp.constants.Constants.PARAM_DEPOSIT;
import static com.example.mybankapp.constants.Constants.PARAM_FILTER_CHANGED;
import static com.example.mybankapp.constants.Constants.PARAM_FOR_FOREIGNERS;
import static com.example.mybankapp.constants.Constants.PARAM_FOR_PRIVATE_PERSON;
import static com.example.mybankapp.constants.Constants.PARAM_INSURANCE;
import static com.example.mybankapp.constants.Constants.PARAM_INVESTMENTS;
import static com.example.mybankapp.constants.Constants.PARAM_MORTGAGE;
import static com.example.mybankapp.constants.Constants.PARAM_SWITCH_TYPE;
import static com.example.mybankapp.constants.Constants.PARAM_SWITCH_TYPE_FILTER_FRAGMENT;
import static com.example.mybankapp.constants.Constants.PARAM_SWITCH_TYPE_ITEM_FRAGMENT;
import static com.example.mybankapp.constants.Constants.PARAM_SWITCH_TYPE_REQUEST_ADDED;
import static com.example.mybankapp.constants.Constants.PARAM_SWITCH_TYPE_REQUEST_FRAGMENT;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private int requestCounter;
    public boolean requestFragmentCalled;
    private ArrayList<BankClass> bankClass;
    private int moneyCount;
    private int time;
    public boolean ifFind;


    @Override
    public void onReceive(Context context, Intent intent) {
        int switchType = intent.getIntExtra(PARAM_SWITCH_TYPE, PARAM_DEFAULT);
        switch (switchType) {
            case PARAM_SWITCH_TYPE_FILTER_FRAGMENT: {
                boolean filterChanged = intent.getBooleanExtra(PARAM_FILTER_CHANGED, false);
                if (filterChanged) {
                    int debitCard = intent.getIntExtra(PARAM_DEBIT_CARD, PARAM_DEFAULT);
                    int creditCard = intent.getIntExtra(PARAM_CREDIT_CARD, PARAM_DEFAULT);
                    int creditCash = intent.getIntExtra(PARAM_CREDIT_CASH, PARAM_DEFAULT);
                    int forForeigners = intent.getIntExtra(PARAM_FOR_FOREIGNERS, PARAM_DEFAULT);
                    int mortgage = intent.getIntExtra(PARAM_MORTGAGE, PARAM_DEFAULT);
                    int deposit = intent.getIntExtra(PARAM_DEPOSIT, PARAM_DEFAULT);
                    int insurance = intent.getIntExtra(PARAM_INSURANCE, PARAM_DEFAULT);
                    int investments = intent.getIntExtra(PARAM_INVESTMENTS, PARAM_DEFAULT);
                    int forPrivatePerson = intent.getIntExtra(PARAM_FOR_PRIVATE_PERSON, PARAM_DEFAULT);
                    String city = intent.getStringExtra(PARAM_CITY);
                    Toast.makeText(context, "Получил интент", Toast.LENGTH_SHORT).show();
                    BankClass.searchBankItems(debitCard,creditCard,creditCash,forForeigners,mortgage,deposit,insurance,investments,forPrivatePerson);
                    ifFind = BankClass.isIfFind();
                    MyBroadcastListener myBroadcastListener = (MyBroadcastListener) context;
                    myBroadcastListener.ifFind(BankClass.isIfFind());
//                    if (ifFind) {
//                        MainActivity mainActivity = new MainActivity();
//                        mainActivity.fragmentReplace(new ListFragment());
//                    } else {
//                        Toast.makeText(this, R.string.main_toast_filter_coincidence, Toast.LENGTH_SHORT).show();
//                    }
                } else {
                    if (!requestFragmentCalled) {
                        // вызов fragmentReplace(new ListFragment());
                    } else {
                        requestFragmentCalled = false;
                    }
                }
                filterChanged = false;
            }
            break;
            case PARAM_SWITCH_TYPE_ITEM_FRAGMENT: {
                String bankName = intent.getStringExtra(PARAM_BUNDLE_NAME);
                for (int i = 0; i < bankClass.size(); i++) {
                    if (bankClass.get(i).getName().equals(bankName)) {
                        String bankAdress = bankClass.get(i).getAdress();
                        int bankImage = bankClass.get(i).getImage();
                        String bankLicense = bankClass.get(i).getLicense();
                        String bankMap = bankClass.get(i).getMap();
                        String bankOgrn = bankClass.get(i).getOgrn();
                        String bankSite = bankClass.get(i).getSite();
                    }
                }
                // вызов fragmentReplace(new ItemFragment());
            }
            break;
            case PARAM_SWITCH_TYPE_REQUEST_FRAGMENT: {
                String chosenBankName = intent.getStringExtra(PARAM_BUNDLE_NAME);
                // вызов fragmentReplace(new AddRequestFragment());
            }
            break;
            case PARAM_SWITCH_TYPE_REQUEST_ADDED: {
                time = intent.getIntExtra(PARAM_BUNDLE_TO_REQUEST_FRAGMENT_TIME, 1);
                requestCounter++;
                moneyCount = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra(PARAM_BUNDLE_TO_REQUEST_FRAGMENT_MONEY_COUNT)));
                //вызов fragmentReplace(new RequestFragment());
            }
            break;

            default:
                break;
        }
    }

    public void getReceivingData(){

    }

    public void setRequestFragmentCalled(boolean state){
        this.requestFragmentCalled = state;
    }
}
