package com.example.mybankapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mybankapp.bcreceiver.MyBroadcastReceiver;
import com.example.mybankapp.fragments.AddRequestFragment;
import com.example.mybankapp.interfaces.MyBroadcastListener;
import com.example.mybankapp.models.BankClass;
import com.example.mybankapp.fragments.FilterFragment;
import com.example.mybankapp.fragments.ItemFragment;
import com.example.mybankapp.fragments.ListFragment;
import com.example.mybankapp.R;
import com.example.mybankapp.fragments.RequestFragment;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.mybankapp.constants.Constants.PARAM_BROADCAST_NAME;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_ADRESS;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_CITY;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_IMG;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_LICENSE;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_MAP;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_NAME;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_OGRN;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_SITE;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_TO_LIST_FRAGMENT_BANK_IMAGES;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_TO_LIST_FRAGMENT_BANK_ITEMS;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_TO_REQUEST_FRAGMENT_MONEY_COUNT;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_TO_REQUEST_FRAGMENT_REQUEST_COUNTER;
import static com.example.mybankapp.constants.Constants.PARAM_BUNDLE_TO_REQUEST_FRAGMENT_TIME;
import static com.example.mybankapp.constants.Constants.PARAM_DEFAULT;
import static com.example.mybankapp.constants.Constants.PARAM_FILTER_CHANGED;
import static com.example.mybankapp.constants.Constants.PARAM_SWITCH_TYPE;
import static com.example.mybankapp.constants.Constants.PARAM_SWITCH_TYPE_FILTER_FRAGMENT;
import static com.example.mybankapp.constants.Constants.PARAM_SWITCH_TYPE_ITEM_FRAGMENT;
import static com.example.mybankapp.constants.Constants.PARAM_SWITCH_TYPE_REQUEST_ADDED;
import static com.example.mybankapp.constants.Constants.PARAM_SWITCH_TYPE_REQUEST_FRAGMENT;
import static com.example.mybankapp.constants.Constants.PARAM_CITY;
import static com.example.mybankapp.constants.Constants.PARAM_CREDIT_CARD;
import static com.example.mybankapp.constants.Constants.PARAM_CREDIT_CASH;
import static com.example.mybankapp.constants.Constants.PARAM_DEBIT_CARD;
import static com.example.mybankapp.constants.Constants.PARAM_DEPOSIT;
import static com.example.mybankapp.constants.Constants.PARAM_FOR_FOREIGNERS;
import static com.example.mybankapp.constants.Constants.PARAM_FOR_PRIVATE_PERSON;
import static com.example.mybankapp.constants.Constants.PARAM_INSURANCE;
import static com.example.mybankapp.constants.Constants.PARAM_INVESTMENTS;
import static com.example.mybankapp.constants.Constants.PARAM_MORTGAGE;

public class MainActivity extends AppCompatActivity implements MyBroadcastListener {
    private ArrayList<BankClass> bankClassItems;

    private boolean filterChanged;
    private boolean requestFragmentCalled;

    private int debitCard;
    private int creditCard;
    private int creditCash;
    private int forForeigners;
    private int mortgage;
    private int deposit;
    private int insurance;
    private int investments;
    private int forPrivatePerson;
    private int switchType;
    private int time;
    private int moneyCount;
    private int requestCounter;
    private int bankImage;

    private String bankName;
    private String bankOgrn;
    private String bankAdress;
    private String bankSite;
    private String bankMap;
    private String bankLicense;
    private String chosenBankName;
    private String city;

    private ArrayList<String> bankNames = new ArrayList<>();
    private ArrayList<Integer> bankImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.main_toolbar);

        Button listButton = findViewById(R.id.btn_list);
        Button filterButton = findViewById(R.id.btn_filter);
        Button requestButton = findViewById(R.id.btn_requests);
        setupToolbar(toolbar);
        BankClass.initBankItems();
        final MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(PARAM_BROADCAST_NAME);
        registerReceiver(myBroadcastReceiver, intentFilter);
        fragmentAdd(new FilterFragment());

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentReplace(new ListFragment());
            }
        });

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentReplace(new FilterFragment());
            }
        });
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBroadcastReceiver.setRequestFragmentCalled(true);
                fragmentReplace(new RequestFragment());
            }
        });

        bankClassItems = new ArrayList<>();
        requestCounter = 0;
        bankClassItems = BankClass.bankClass;
//        if (myBroadcastReceiver.ifFind){
//            fragmentReplace(new ListFragment());
//        } else {
//            Toast.makeText(this, R.string.main_toast_filter_coincidence, Toast.LENGTH_SHORT).show();
//        }
    }

    private void fragmentAdd(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frame, fragment);
        ft.commit();
    }

    public void fragmentReplace(Fragment fragment) {
        Bundle args = new Bundle();
        if (fragment instanceof FilterFragment) {

        } else if (fragment instanceof AddRequestFragment) {
            args.putString(PARAM_BUNDLE_NAME, bankName);
        } else if (fragment instanceof ItemFragment) {
            args.putString(PARAM_BUNDLE_ADRESS, bankAdress);
            args.putInt(PARAM_BUNDLE_IMG, bankImage);
            args.putString(PARAM_BUNDLE_NAME, bankName);
            args.putString(PARAM_BUNDLE_LICENSE, bankLicense);
            args.putString(PARAM_BUNDLE_OGRN, bankOgrn);
            args.putString(PARAM_BUNDLE_SITE, bankSite);
            args.putString(PARAM_BUNDLE_MAP, bankMap);
            args.putString(PARAM_BUNDLE_CITY, city);
        } else if (fragment instanceof ListFragment) {
            args.putStringArrayList(PARAM_BUNDLE_TO_LIST_FRAGMENT_BANK_ITEMS, bankNames);
            args.putIntegerArrayList(PARAM_BUNDLE_TO_LIST_FRAGMENT_BANK_IMAGES, bankImages);
            args.putBoolean(PARAM_FILTER_CHANGED, filterChanged);
        } else if (fragment instanceof RequestFragment){
            args.putString(PARAM_BUNDLE_NAME, chosenBankName);
            args.putInt(PARAM_BUNDLE_TO_REQUEST_FRAGMENT_REQUEST_COUNTER, requestCounter);
            args.putInt(PARAM_BUNDLE_TO_REQUEST_FRAGMENT_MONEY_COUNT, moneyCount);
            args.putInt(PARAM_BUNDLE_TO_REQUEST_FRAGMENT_TIME, time);
            args.putBoolean(PARAM_FILTER_CHANGED, filterChanged);
        }
        fragment.setArguments(args);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private void startBroadcast() {

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switchType = intent.getIntExtra(PARAM_SWITCH_TYPE, PARAM_DEFAULT);
                switch (switchType) {
                    case PARAM_SWITCH_TYPE_FILTER_FRAGMENT: {
                        filterChanged = intent.getBooleanExtra(PARAM_FILTER_CHANGED, false);
                        if (filterChanged) {
                            debitCard = intent.getIntExtra(PARAM_DEBIT_CARD, PARAM_DEFAULT);
                            creditCard = intent.getIntExtra(PARAM_CREDIT_CARD, PARAM_DEFAULT);
                            creditCash = intent.getIntExtra(PARAM_CREDIT_CASH, PARAM_DEFAULT);
                            forForeigners = intent.getIntExtra(PARAM_FOR_FOREIGNERS, PARAM_DEFAULT);
                            mortgage = intent.getIntExtra(PARAM_MORTGAGE, PARAM_DEFAULT);
                            deposit = intent.getIntExtra(PARAM_DEPOSIT, PARAM_DEFAULT);
                            insurance = intent.getIntExtra(PARAM_INSURANCE, PARAM_DEFAULT);
                            investments = intent.getIntExtra(PARAM_INVESTMENTS, PARAM_DEFAULT);
                            forPrivatePerson = intent.getIntExtra(PARAM_FOR_PRIVATE_PERSON, PARAM_DEFAULT);
                            city = intent.getStringExtra(PARAM_CITY);
                            searchBank();
                        } else {
                            if (!requestFragmentCalled) {
                                fragmentReplace(new ListFragment());
                            } else {
                                requestFragmentCalled = false;
                            }
                        }
                        filterChanged = false;
                    }
                    break;
                    case PARAM_SWITCH_TYPE_ITEM_FRAGMENT: {
                        bankName = intent.getStringExtra(PARAM_BUNDLE_NAME);
                        for (int i = 0; i < bankClassItems.size(); i++) {
                            if (bankClassItems.get(i).getName().equals(bankName)) {
                                bankAdress = bankClassItems.get(i).getAdress();
                                bankImage = bankClassItems.get(i).getImage();
                                bankLicense = bankClassItems.get(i).getLicense();
                                bankMap = bankClassItems.get(i).getMap();
                                bankOgrn = bankClassItems.get(i).getOgrn();
                                bankSite = bankClassItems.get(i).getSite();
                            }
                        }
                        fragmentReplace(new ItemFragment());
                    }
                    break;
                    case PARAM_SWITCH_TYPE_REQUEST_FRAGMENT: {
                        chosenBankName = intent.getStringExtra(PARAM_BUNDLE_NAME);
                        fragmentReplace(new AddRequestFragment());
                    }
                    break;
                    case PARAM_SWITCH_TYPE_REQUEST_ADDED: {
                        time = intent.getIntExtra(PARAM_BUNDLE_TO_REQUEST_FRAGMENT_TIME, 1);
                        requestCounter++;
                        moneyCount = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra(PARAM_BUNDLE_TO_REQUEST_FRAGMENT_MONEY_COUNT)));
                        fragmentReplace(new RequestFragment());
                    }
                    break;

                    default:
                        break;
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter(PARAM_BROADCAST_NAME);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private void searchBank() {

        boolean ifFind = false;
        boolean forPerson = false;
        boolean forDebitCard = false;
        boolean forCreditCard = false;
        boolean forCreditCash = false;
        boolean forDeposit = false;
        boolean forMortgage = false;
        boolean forInsurance = false;
        boolean forInvestments = false;
        boolean forForeign = false;
        for (int i = 0; i < bankClassItems.size(); i++) {

            switch (forPrivatePerson) {
                case 0: {
                    forPerson = true;
                }
                break;
                case 1: {
                    forPerson = bankClassItems.get(i).isForPrivatePerson();
                }
                break;
                case 2: {
                    forPerson = !bankClassItems.get(i).isForPrivatePerson();
                }
                break;
                default:
                    break;
            }

            switch (creditCard) {
                case 0: {
                    forCreditCard = true;
                }
                break;
                case 1: {
                    forCreditCard = bankClassItems.get(i).isCreditCard();
                }
                break;
                case 2: {
                    forCreditCard = !bankClassItems.get(i).isCreditCard();
                }
                break;
                default:
                    break;
            }

            switch (debitCard) {
                case 0: {
                    forDebitCard = true;
                }
                break;
                case 1: {
                    forDebitCard = bankClassItems.get(i).isDebitCard();
                }
                break;
                case 2: {
                    forDebitCard = !bankClassItems.get(i).isDebitCard();
                }
                break;
                default:
                    break;
            }

            switch (creditCash) {
                case 0: {
                    forCreditCash = true;
                }
                break;
                case 1: {
                    forCreditCash = bankClassItems.get(i).isCreditCash();
                }
                break;
                case 2: {
                    forCreditCash = !bankClassItems.get(i).isCreditCash();
                }
                break;
                default:
                    break;
            }

            switch (deposit) {
                case 0: {
                    forDeposit = true;
                }
                break;
                case 1: {
                    forDeposit = bankClassItems.get(i).isDeposit();
                }
                break;
                case 2: {
                    forDeposit = !bankClassItems.get(i).isDeposit();
                }
                break;
                default:
                    break;
            }

            switch (mortgage) {
                case 0: {
                    forMortgage = true;
                }
                break;
                case 1: {
                    forMortgage = bankClassItems.get(i).isMortgage();
                }
                break;
                case 2: {
                    forMortgage = !bankClassItems.get(i).isMortgage();
                }
                break;
                default:
                    break;
            }

            switch (investments) {
                case 0: {
                    forInvestments = true;
                }
                break;
                case 1: {
                    forInvestments = bankClassItems.get(i).isInvestments();
                }
                break;
                case 2: {
                    forInvestments = !bankClassItems.get(i).isInvestments();
                }
                break;
                default:
                    break;
            }

            switch (insurance) {
                case 0: {
                    forInsurance = true;
                }
                break;
                case 1: {
                    forInsurance = bankClassItems.get(i).isInsurance();
                }
                break;
                case 2: {
                    forInsurance = !bankClassItems.get(i).isInsurance();
                }
                break;
                default:
                    break;
            }

            switch (forForeigners) {
                case 0: {
                    forForeign = true;
                }
                break;
                case 1: {
                    forForeign = bankClassItems.get(i).isForForeignClients();
                }
                break;
                case 2: {
                    forForeign = !bankClassItems.get(i).isForForeignClients();
                }
                break;
                default:
                    break;
            }

            if (forCreditCard && forCreditCash && forDebitCard && forDeposit && forForeign && forInsurance && forInvestments &&
                    forPerson && forMortgage) {
                ifFind = true;
                bankNames.add(bankClassItems.get(i).getName());
                bankImages.add(bankClassItems.get(i).getImage());
            }

        }

        if (ifFind) {
            fragmentReplace(new ListFragment());
        } else {
            Toast.makeText(this, R.string.main_toast_filter_coincidence, Toast.LENGTH_SHORT).show();
        }
    }

    protected void setupToolbar(Toolbar toolbar) {
        toolbar.setTitle(R.string.main_toolbar_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public void ifFind(boolean ifFind) {
        if (ifFind){
            fragmentReplace(new ListFragment());
        } else {
            Toast.makeText(this, R.string.main_toast_filter_coincidence, Toast.LENGTH_SHORT).show();
        }
    }
}
