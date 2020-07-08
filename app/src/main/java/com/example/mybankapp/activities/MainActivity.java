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

import com.example.mybankapp.fragments.AddRequestFragment;
import com.example.mybankapp.classes.BankClass;
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
import static com.example.mybankapp.constants.Constants.PARAM_city;
import static com.example.mybankapp.constants.Constants.PARAM_creditCard;
import static com.example.mybankapp.constants.Constants.PARAM_creditCash;
import static com.example.mybankapp.constants.Constants.PARAM_debitCard;
import static com.example.mybankapp.constants.Constants.PARAM_deposit;
import static com.example.mybankapp.constants.Constants.PARAM_forForeigners;
import static com.example.mybankapp.constants.Constants.PARAM_forPrivatePerson;
import static com.example.mybankapp.constants.Constants.PARAM_insurance;
import static com.example.mybankapp.constants.Constants.PARAM_investments;
import static com.example.mybankapp.constants.Constants.PARAM_mortgage;

public class MainActivity extends AppCompatActivity {
    private FilterFragment filterFragment;
    private ListFragment listFragment;
    private ItemFragment itemFragment;
    private RequestFragment requestFragment;
    private AddRequestFragment addRequestFragment;
    private ArrayList<BankClass> bankClass;

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

    private final String PARAM_LIST_FRAGMENT = "ListFragment";
    private final String PARAM_FILTER_FRAGMENT = "FilterFragment";
    private final String PARAM_REQUEST_FRAGMENT = "RequestFragment";
    private final String PARAM_ITEM_FRAGMENT = "ItemFragment";
    private final String PARAM_ADD_REQUEST_FRAGMENT = "AddRequestFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.main_toolbar);

        Button listButton = findViewById(R.id.btn_list);
        Button filterButton = findViewById(R.id.btn_filter);
        Button requestButton = findViewById(R.id.btn_requests);
        setupToolbar(toolbar);

        filterFragment = new FilterFragment();
        listFragment = new ListFragment();
        itemFragment = new ItemFragment();
        requestFragment = new RequestFragment();
        addRequestFragment = new AddRequestFragment();
        fragmentAdd(filterFragment);

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentReplace(listFragment, PARAM_LIST_FRAGMENT);
            }
        });

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentReplace(filterFragment, PARAM_FILTER_FRAGMENT);
            }
        });
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestFragmentCalled = true;
                fragmentReplace(requestFragment, PARAM_REQUEST_FRAGMENT);
            }
        });
        bankClass = new ArrayList<>();
        requestCounter = 0;
        initBank(bankClass);
        startBroadcast();

    }

    private void fragmentAdd(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frame, fragment);
        ft.commit();
    }

    private void fragmentReplace(Fragment fragment, String fragmentClass) {
        Bundle args = new Bundle();
        switch (fragmentClass) {
            case PARAM_ADD_REQUEST_FRAGMENT: {
                args.putString(PARAM_BUNDLE_NAME, bankName);
                fragment.setArguments(args);
            }
            break;
            case PARAM_FILTER_FRAGMENT:
                break;
            case PARAM_ITEM_FRAGMENT: {
                args.putString(PARAM_BUNDLE_ADRESS, bankAdress);
                args.putInt(PARAM_BUNDLE_IMG, bankImage);
                args.putString(PARAM_BUNDLE_NAME, bankName);
                args.putString(PARAM_BUNDLE_LICENSE, bankLicense);
                args.putString(PARAM_BUNDLE_OGRN, bankOgrn);
                args.putString(PARAM_BUNDLE_SITE, bankSite);
                args.putString(PARAM_BUNDLE_MAP, bankMap);
                args.putString(PARAM_BUNDLE_CITY, city);
                fragment.setArguments(args);
            }
            break;
            case PARAM_LIST_FRAGMENT: {
                args.putStringArrayList(PARAM_BUNDLE_TO_LIST_FRAGMENT_BANK_ITEMS, bankNames);
                args.putIntegerArrayList(PARAM_BUNDLE_TO_LIST_FRAGMENT_BANK_IMAGES, bankImages);
                args.putBoolean(PARAM_FILTER_CHANGED, filterChanged);
                fragment.setArguments(args);
            }
            break;
            case PARAM_REQUEST_FRAGMENT: {
                args.putString(PARAM_BUNDLE_NAME, chosenBankName);
                args.putInt(PARAM_BUNDLE_TO_REQUEST_FRAGMENT_REQUEST_COUNTER, requestCounter);
                args.putInt(PARAM_BUNDLE_TO_REQUEST_FRAGMENT_MONEY_COUNT, moneyCount);
                args.putInt(PARAM_BUNDLE_TO_REQUEST_FRAGMENT_TIME, time);
                args.putBoolean(PARAM_FILTER_CHANGED, filterChanged);
                fragment.setArguments(args);
            }
            break;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

//    private void fragmentReplace(Fragment fragment, String bankName) {
//        // AddRequestFragment
//        Bundle args = new Bundle();
//        args.putString("bank_name", bankName);
//        fragment.setArguments(args);
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.frame, fragment);
//        ft.addToBackStack(null);
//        ft.commit();
//    }
//
//    private void fragmentReplace(Fragment fragment, String adres, int imgPath, String name, String license, String ogrn, String web,
//                                 String mapData) {
//        // Item Fragment
//        Bundle args = new Bundle();
//        args.putString(PARAM_BUNDLE_ADRESS, adres);
//        args.putInt(PARAM_BUNDLE_IMG, imgPath);
//        args.putString(PARAM_BUNDLE_NAME, name);
//        args.putString(PARAM_BUNDLE_LICENSE, license);
//        args.putString(PARAM_BUNDLE_OGRN, ogrn);
//        args.putString(PARAM_BUNDLE_SITE, web);
//        args.putString(PARAM_BUNDLE_MAP, mapData);
//        args.putString(PARAM_BUNDLE_CITY, city);
//        fragment.setArguments(args);
//
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.frame, fragment);
//        ft.addToBackStack(null);
//        ft.commit();
//    }
//
//    private void fragmentReplace(Fragment fragment, boolean filterChanged) {
//        // List Fragment
//        Bundle args = new Bundle();
//        args.putBoolean("filterChanged", filterChanged);
//        fragment.setArguments(args);
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.frame, fragment);
//        ft.addToBackStack(null);
//        ft.commit();
//    }
//
//    private void fragmentReplace(Fragment fragment, ArrayList<String> bankItems, ArrayList<Integer> bankImage, boolean filterChanged) {
//        // List Fragment
//        Bundle args = new Bundle();
//        args.putStringArrayList("bankItems", bankItems);
//        args.putIntegerArrayList("bankImages", bankImage);
//        args.putBoolean("filterChanged", filterChanged);
//        fragment.setArguments(args);
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.frame, fragment);
//        ft.addToBackStack(null);
//        ft.commit();
//    }
//
//    private void fragmentReplace(Fragment fragment, String bankname, int requestCounter, int moneycount, int time, boolean filterChanged) {
//        // Request Fragment
//        Bundle args = new Bundle();
//        args.putString("bank name", bankname);
//        args.putInt("requestCounter", requestCounter);
//        args.putInt("moneycount", moneycount);
//        args.putInt("time", time);
//        args.putBoolean("filterChanged", filterChanged);
//        fragment.setArguments(args);
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.frame, fragment);
//        ft.addToBackStack(null);
//        ft.commit();
//    }

    private void initBank(ArrayList<BankClass> bankClass) {

        BankClass sberbank = new BankClass(true, true, true, true, true, true,
                true, true, "Сбербанк", true, true, true, true,
                "117997, г. Москва,\nул. Вавилова, д. 19", R.drawable.ic_sberbank, "1481", "1027700132195", "https://www.sberbank.ru/", "geo:0,0?q=Сбербанк+");
        bankClass.add(sberbank);

        BankClass tinkoff = new BankClass(true, true, true, true, true, true,
                true, false, "Тинькофф", true, true, false, false,
                "123060, г. Москва,\n1-й Волоколамский пр-д,\nд. 10, стр. 1", R.drawable.ic_tinkoff, "2673", "1027739642281", "https://www.tinkoff.ru/", "geo:0,0?q=Тинькофф+");
        bankClass.add(tinkoff);

        BankClass alfabank = new BankClass(true, true, true, false, true, true,
                true, true, "Альфабанк", false, true, true, false,
                "107078, г. Москва,\nул. Каланчевская, д. 27", R.drawable.ic_alfabank, "1326", "1027700067328", "https://alfabank.ru/", "geo:0,0?q=Альфа-банк+");
        bankClass.add(alfabank);

        BankClass vtb = new BankClass(true, true, false, false, false, true,
                true, true, "ВТБ", false, true, true, false,
                "190000, г. Санкт-Петербург,\nул. Большая Морская,\nд. 29", R.drawable.ic_vtb, "1000", "1027739609391", "https://www.vtb.ru/", "geo:0,0?q=ВТБ+банк+");
        bankClass.add(vtb);

        BankClass gazprom = new BankClass(true, true, false, false, false, true,
                true, true, "Газпромбанк", false, true, true, false,
                "117420, г. Москва,\nул. Наметкина, д. 16, корп. 1.", R.drawable.ic_gazprombank, "354", "1027700167110", "https://www.gazprombank.ru/", "geo:0,0?q=Газпромбанк+");
        bankClass.add(gazprom);

        BankClass rosselhoz = new BankClass(true, true, false, false, false, true,
                true, true, "Россельхозбанк", false, true, true, false,
                "119034, г. Москва,\nГагаринский пер., д. 3", R.drawable.ic_rosselhoz, "3349", "1027700342890", "https://www.rshb.ru/", "geo:0,0?q=Россельхозбанк+");
        bankClass.add(rosselhoz);

        BankClass otkritie = new BankClass(true, true, false, false, false, true,
                true, true, "Открытие", false, true, true, false,
                "115114, г. Москва,\nул. Летниковская, д. 2, стр. 4", R.drawable.ic_otkritie, "2209", "1027739019208", "https://www.open.ru/", "geo:0,0?q=Открытие+банк+");
        bankClass.add(otkritie);

        BankClass unicredit = new BankClass(true, true, false, false, false, true,
                true, true, "ЮниКредит Банк", false, true, true, false,
                "119034, г. Москва,\nПречистенская наб., д. 9", R.drawable.ic_unicredit, "1", "1027739082106", "https://www.unicreditbank.ru/", "geo:0,0?q=ЮниКредит+банк+");
        bankClass.add(unicredit);

        BankClass raiffeisen = new BankClass(true, true, false, false, false, true,
                true, true, "Райффайзенбанк", false, true, true, false,
                "129090, г. Москва,\nул. Троицкая, д. 17, стр. 1", R.drawable.ic_raiffeisen, "3292", "1027739326449", "https://www.raiffeisen.ru/", "geo:0,0?q=Райффайзенбанк+");
        bankClass.add(raiffeisen);

        BankClass sovkombank = new BankClass(true, true, false, false, false, true,
                true, true, "Совкомбанк", false, true, true, false,
                "156000, г. Кострома,\nпросп. Текстильщиков, д. 46", R.drawable.ic_sovkombank, "963", "1144400000425", "https://www.sovcombank.ru/", "geo:0,0?q=Совкомбанк+");
        bankClass.add(sovkombank);

        BankClass rosbank = new BankClass(true, true, false, false, false, true,
                true, true, "Росбанк", false, true, true, false,
                "107078, г. Москва,\nул. Маши Порываевой, д. 34", R.drawable.ic_rosbank, "2272", "1027739460737", "https://www.rosbank.ru/", "geo:0,0?q=Росбанк+");
        bankClass.add(rosbank);

        BankClass rossiya = new BankClass(true, true, false, false, false, true,
                true, true, "Банк Россия", false, true, true, false,
                "191124, г. Санкт-Петербург,\nпл. Растрелли,\nд. 2, литер А", R.drawable.ic_rossiya, "328", "1027800000084", "https://www.abr.ru/", "geo:0,0?q=банк+Россия+");
        bankClass.add(rossiya);

        BankClass spbbank = new BankClass(true, true, false, false, false, true,
                true, true, "Банк Санкт-Петербург", false, true, true, false,
                "195112, г. Санкт-Петербург,\nМалоохтинский просп.,\nд. 64", R.drawable.ic_spbbank, "436", "1027800000140", "https://www.bspb.ru/", "geo:0,0?q=банк+Санкт-Петербург+");
        bankClass.add(spbbank);

        BankClass citibank = new BankClass(true, true, false, false, false, true,
                true, true, "Ситибанк", false, true, true, false,
                "125047, г. Москва,\nул. Гашека, д. 8—10", R.drawable.ic_citibank, "2557", "1027700431296", "https://www.citibank.ru/", "geo:0,0?q=Ситибанк+");
        bankClass.add(citibank);

        BankClass akbars = new BankClass(true, true, false, false, false, true,
                true, true, "Ак Барс Банк", false, true, true, false,
                "420066, г. Казань,\nул. Декабристов, д. 1", R.drawable.ic_akbars, "2590", "1021600000124", "https://www.akbars.ru/", "geo:0,0?q=Ак+Барс+Банк+");
        bankClass.add(akbars);

        BankClass smpbank = new BankClass(true, true, false, false, false, true,
                true, true, "СМП Банк", false, true, true, false,
                "113035, г. Москва,\nул. Садовническая,\nд. 71, стр. 11", R.drawable.ic_smpbank, "3368", "1097711000078", "https://www.smpbank.ru/", "geo:0,0?q=СМП+Банк+");
        bankClass.add(smpbank);

        BankClass novicom = new BankClass(true, true, false, false, false, true,
                true, true, "Новикомбанк", false, true, true, false,
                "119180, г. Москва,\nул. Полянка Большая,\nд. 50/1, стр. 1", R.drawable.ic_novikom, "2546", "1027739075891", "https://www.novikom.ru/", "geo:0,0?q=Новикомбанк+");
        bankClass.add(novicom);

        BankClass uralsib = new BankClass(true, true, false, false, false, true,
                true, true, "Уралсиб Банк", false, true, true, false,
                "119048, г. Москва,\nул. Ефремова, д. 8", R.drawable.ic_uralsib, "30", "1020280000190", "https://www.uralsib.ru/", "geo:0,0?q=Уралсил+Банк+");
        bankClass.add(uralsib);

        BankClass pochtabank = new BankClass(true, true, false, false, false, true,
                true, true, "Почта Банк", false, true, true, false,
                "107061, г. Москва,\nПреображенская пл., д. 8", R.drawable.ic_pochtabank, "650", "1023200000010", "https://www.pochtabank.ru/", "geo:0,0?q=Почта+Банк+");
        bankClass.add(pochtabank);

        BankClass domrf = new BankClass(true, true, false, false, false, true,
                true, true, "ДОМ.РФ Банк", false, true, true, false,
                "125009, г. Москва,\nул. Воздвиженка, д. 10", R.drawable.ic_domrf, "2312", "1037739527077", "https://www.domrfbank.ru/", "geo:0,0?q=Дом.рф+Банк+");
        bankClass.add(domrf);

        BankClass rstandart = new BankClass(true, true, false, false, false, true,
                true, true, "Русский Стандарт", false, true, true, false,
                "105187, г. Москва,\nул. Ткацкая, д. 36", R.drawable.ic_rstand, "2289", "1027739210630", "https://www.rsb.ru/", "geo:0,0?q=Русский+Стандарт+Банк+");
        bankClass.add(rstandart);

        BankClass absolut = new BankClass(true, true, false, false, false, true,
                true, true, "Абсолют Банк", false, true, true, false,
                "127051, г. Москва,\nЦветной б-р, д. 18", R.drawable.ic_absolut, "2306", "1027700024560", "https://www.absolutbank.ru/", "geo:0,0?q=Абсолют+Банк+");
        bankClass.add(absolut);

        BankClass vozrojdenie = new BankClass(true, true, false, false, false, true,
                true, true, "Банк Возрождение", false, true, true, false,
                "101000, г. Москва,\nЛучников пер., д. 7/4, стр. 1", R.drawable.ic_vozrojdenie, "1439", "1027700540680", "https://www.vbank.ru/", "geo:0,0?q=Возрождение+Банк+");
        bankClass.add(vozrojdenie);

        BankClass zenit = new BankClass(true, true, false, false, false, true,
                true, true, "Банк Зенит", false, true, true, false,
                "117638, г. Москва,\nОдесская ул., д. 2", R.drawable.ic_zenit, "3255", "1027739056927", "https://www.zenit.ru/", "geo:0,0?q=Зенит+Банк+");
        bankClass.add(zenit);

        BankClass mts = new BankClass(true, true, false, false, false, true,
                true, true, "МТС-Банк", false, true, true, false,
                "115432, г. Москва,\nпросп. Андропова,\nд. 18, корп. 1", R.drawable.ic_mts, "2268", "1027739053704", "https://www.mtsbank.ru/", "geo:0,0?q=МТС+Банк+");
        bankClass.add(mts);

        BankClass reneissanse = new BankClass(true, true, false, false, false, true,
                true, true, "Ренессанс Кредит", false, true, true, false,
                "115114, г. Москва,\nул. Кожевническая, д. 14", R.drawable.ic_reneissanse, "3354", "1027739586291", "https://www.rencredit.ru/", "geo:0,0?q=Ренессанс+Кредит+");
        bankClass.add(reneissanse);
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
                            debitCard = intent.getIntExtra(PARAM_debitCard, PARAM_DEFAULT);
                            creditCard = intent.getIntExtra(PARAM_creditCard, PARAM_DEFAULT);
                            creditCash = intent.getIntExtra(PARAM_creditCash, PARAM_DEFAULT);
                            forForeigners = intent.getIntExtra(PARAM_forForeigners, PARAM_DEFAULT);
                            mortgage = intent.getIntExtra(PARAM_mortgage, PARAM_DEFAULT);
                            deposit = intent.getIntExtra(PARAM_deposit, PARAM_DEFAULT);
                            insurance = intent.getIntExtra(PARAM_insurance, PARAM_DEFAULT);
                            investments = intent.getIntExtra(PARAM_investments, PARAM_DEFAULT);
                            forPrivatePerson = intent.getIntExtra(PARAM_forPrivatePerson, PARAM_DEFAULT);
                            city = intent.getStringExtra(PARAM_city);
//                            cityStatus = intent.getIntExtra(PARAM_city_status, PARAM_DEFAULT);
                            searchBank();
                        } else {
                            if (!requestFragmentCalled) {
                                fragmentReplace(listFragment, PARAM_LIST_FRAGMENT);
                            } else {
                                requestFragmentCalled = false;
                            }
                        }
                        filterChanged = false;
                    }
                    break;
                    case PARAM_SWITCH_TYPE_ITEM_FRAGMENT: {
                        bankName = intent.getStringExtra(PARAM_BUNDLE_NAME);
                        for (int i = 0; i < bankClass.size(); i++) {
                            if (bankClass.get(i).getName().equals(bankName)) {
                                bankAdress = bankClass.get(i).getAdress();
                                bankImage = bankClass.get(i).getImage();
                                bankLicense = bankClass.get(i).getLicense();
                                bankMap = bankClass.get(i).getMap();
                                bankOgrn = bankClass.get(i).getOgrn();
                                bankSite = bankClass.get(i).getSite();
                            }
                        }
                        fragmentReplace(itemFragment, PARAM_ITEM_FRAGMENT);
                    }
                    break;
                    case PARAM_SWITCH_TYPE_REQUEST_FRAGMENT: {
                        chosenBankName = intent.getStringExtra(PARAM_BUNDLE_NAME);
                        fragmentReplace(addRequestFragment, PARAM_ADD_REQUEST_FRAGMENT);
                    }
                    break;
                    case PARAM_SWITCH_TYPE_REQUEST_ADDED: {
                        time = intent.getIntExtra(PARAM_BUNDLE_TO_REQUEST_FRAGMENT_TIME, 1);
                        requestCounter++;
                        moneyCount = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra(PARAM_BUNDLE_TO_REQUEST_FRAGMENT_MONEY_COUNT)));
                        fragmentReplace(requestFragment, PARAM_REQUEST_FRAGMENT);
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
//        boolean forCity = false;
        for (int i = 0; i < bankClass.size(); i++) {

            switch (forPrivatePerson) {
                case 0: {
                    forPerson = true;
                }
                break;
                case 1: {
                    forPerson = bankClass.get(i).isForPrivatePerson();
                }
                break;
                case 2: {
                    forPerson = !bankClass.get(i).isForPrivatePerson();
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
                    forCreditCard = bankClass.get(i).isCreditCard();
                }
                break;
                case 2: {
                    forCreditCard = !bankClass.get(i).isCreditCard();
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
                    forDebitCard = bankClass.get(i).isDebitCard();
                }
                break;
                case 2: {
                    forDebitCard = !bankClass.get(i).isDebitCard();
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
                    forCreditCash = bankClass.get(i).isCreditCash();
                }
                break;
                case 2: {
                    forCreditCash = !bankClass.get(i).isCreditCash();
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
                    forDeposit = bankClass.get(i).isDeposit();
                }
                break;
                case 2: {
                    forDeposit = !bankClass.get(i).isDeposit();
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
                    forMortgage = bankClass.get(i).isMortgage();
                }
                break;
                case 2: {
                    forMortgage = !bankClass.get(i).isMortgage();
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
                    forInvestments = bankClass.get(i).isInvestments();
                }
                break;
                case 2: {
                    forInvestments = !bankClass.get(i).isInvestments();
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
                    forInsurance = bankClass.get(i).isInsurance();
                }
                break;
                case 2: {
                    forInsurance = !bankClass.get(i).isInsurance();
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
                    forForeign = bankClass.get(i).isForForeignClients();
                }
                break;
                case 2: {
                    forForeign = !bankClass.get(i).isForForeignClients();
                }
                break;
                default:
                    break;
            }

            if (forCreditCard && forCreditCash && forDebitCard && forDeposit && forForeign && forInsurance && forInvestments &&
                    forPerson && forMortgage) {
                ifFind = true;
                bankNames.add(bankClass.get(i).getName());
                bankImages.add(bankClass.get(i).getImage());
            }

        }

        if (ifFind) {
            fragmentReplace(listFragment, PARAM_LIST_FRAGMENT);
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
}
