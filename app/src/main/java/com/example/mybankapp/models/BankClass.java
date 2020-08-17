package com.example.mybankapp.models;

import android.widget.Toast;

import com.example.mybankapp.R;
import com.example.mybankapp.fragments.ListFragment;

import java.util.ArrayList;
import java.util.Objects;

public class BankClass {
    private boolean debitCard;
    private boolean creditCard;
    private boolean creditCash;
    private boolean forForeignClients;
    private boolean mortgage;
    private boolean deposit;
    private boolean forPrivatePerson;
    private boolean insurance;
    private String name;
    private boolean investments;
    private String adress;
    private int image;
    private String license;
    private String ogrn;
    private String site;
    private String map;
    public static ArrayList<BankClass> bankClass;
    public ArrayList<BankClass> filteredBankClassItems;
    public static boolean ifFind;
    public static ArrayList<String> bankNames;
    public static ArrayList<Integer> bankImages;

    public BankClass(boolean debitCard, boolean creditCard, boolean creditCash, boolean forForeignClients, boolean mortgage, boolean deposit,
                     boolean forPrivatePerson, boolean insurance, String name, boolean investments, String adress, int image, String license, String ogrn, String site,
                     String map) {
        this.debitCard = debitCard;
        this.creditCard = creditCard;
        this.creditCash = creditCash;
        this.forForeignClients = forForeignClients;
        this.mortgage = mortgage;
        this.deposit = deposit;
        this.forPrivatePerson = forPrivatePerson;
        this.insurance = insurance;
        this.name = name;
        this.investments = investments;

        this.adress = adress;
        this.image = image;
        this.license = license;
        this.ogrn = ogrn;
        this.site = site;
        this.map = map;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankClass bankClass = (BankClass) o;
        return debitCard == bankClass.debitCard &&
                creditCard == bankClass.creditCard &&
                creditCash == bankClass.creditCash &&
                forForeignClients == bankClass.forForeignClients &&
                mortgage == bankClass.mortgage &&
                deposit == bankClass.deposit &&
                forPrivatePerson == bankClass.forPrivatePerson &&
                insurance == bankClass.insurance &&
                investments == bankClass.investments &&
                image == bankClass.image &&
                name.equals(bankClass.name) &&
                adress.equals(bankClass.adress) &&
                license.equals(bankClass.license) &&
                ogrn.equals(bankClass.ogrn) &&
                site.equals(bankClass.site) &&
                map.equals(bankClass.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(debitCard, creditCard, creditCash, forForeignClients, mortgage, deposit, forPrivatePerson, insurance, name, investments, adress, image, license, ogrn, site, map);
    }

    @Override
    public String toString() {
        return "BankClass{" +
                "debitCard=" + debitCard +
                ", creditCard=" + creditCard +
                ", creditCash=" + creditCash +
                ", forForeignClients=" + forForeignClients +
                ", mortgage=" + mortgage +
                ", deposit=" + deposit +
                ", forPrivatePerson=" + forPrivatePerson +
                ", insurance=" + insurance +
                ", name='" + name + '\'' +
                ", investments=" + investments +
                ", adress='" + adress + '\'' +
                ", image=" + image +
                ", license='" + license + '\'' +
                ", ogrn='" + ogrn + '\'' +
                ", site='" + site + '\'' +
                ", map='" + map + '\'' +
                '}';
    }



    public boolean isDebitCard() {
        return debitCard;
    }

    public boolean isCreditCard() {
        return creditCard;
    }

    public boolean isCreditCash() {
        return creditCash;
    }

    public boolean isForForeignClients() {
        return forForeignClients;
    }

    public boolean isMortgage() {
        return mortgage;
    }

    public boolean isDeposit() {
        return deposit;
    }

    public boolean isForPrivatePerson() {
        return forPrivatePerson;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public String getName() {
        return name;
    }

    public boolean isInvestments() {
        return investments;
    }

    public String getAdress() {
        return adress;
    }

    public int getImage() {
        return image;
    }

    public String getLicense() {
        return license;
    }

    public String getOgrn() {
        return ogrn;
    }

    public String getSite() {
        return site;
    }

    public String getMap() {
        return map;
    }

    public BankClass() {
    }

    public static void initBankItems(){
        bankClass = new ArrayList<>();
        BankClass sberbank = new BankClass(true, true, true, true, true, true,
                true, true, "Сбербанк", true,
                "117997, г. Москва,\nул. Вавилова, д. 19", R.drawable.ic_sberbank, "1481", "1027700132195", "https://www.sberbank.ru/", "geo:0,0?q=Сбербанк+");
        bankClass.add(sberbank);

        BankClass tinkoff = new BankClass(true, true, true, true, true, true,
                true, false, "Тинькофф", true,
                "123060, г. Москва,\n1-й Волоколамский пр-д,\nд. 10, стр. 1", R.drawable.ic_tinkoff, "2673", "1027739642281", "https://www.tinkoff.ru/", "geo:0,0?q=Тинькофф+");
        bankClass.add(tinkoff);

        BankClass alfabank = new BankClass(true, true, true, false, true, true,
                true, true, "Альфабанк", false,
                "107078, г. Москва,\nул. Каланчевская, д. 27", R.drawable.ic_alfabank, "1326", "1027700067328", "https://alfabank.ru/", "geo:0,0?q=Альфа-банк+");
        bankClass.add(alfabank);

        BankClass vtb = new BankClass(true, true, false, false, false, true,
                true, true, "ВТБ", false,
                "190000, г. Санкт-Петербург,\nул. Большая Морская,\nд. 29", R.drawable.ic_vtb, "1000", "1027739609391", "https://www.vtb.ru/", "geo:0,0?q=ВТБ+банк+");
        bankClass.add(vtb);

        BankClass gazprom = new BankClass(true, true, false, false, false, true,
                true, true, "Газпромбанк", false, "117420, г. Москва,\nул. Наметкина, д. 16, корп. 1.", R.drawable.ic_gazprombank, "354", "1027700167110", "https://www.gazprombank.ru/", "geo:0,0?q=Газпромбанк+");
        bankClass.add(gazprom);

        BankClass rosselhoz = new BankClass(true, true, false, false, false, true,
                true, true, "Россельхозбанк", false,
                "119034, г. Москва,\nГагаринский пер., д. 3", R.drawable.ic_rosselhoz, "3349", "1027700342890", "https://www.rshb.ru/", "geo:0,0?q=Россельхозбанк+");
        bankClass.add(rosselhoz);

        BankClass otkritie = new BankClass(true, true, false, false, false, true,
                true, true, "Открытие", false,
                "115114, г. Москва,\nул. Летниковская, д. 2, стр. 4", R.drawable.ic_otkritie, "2209", "1027739019208", "https://www.open.ru/", "geo:0,0?q=Открытие+банк+");
        bankClass.add(otkritie);

        BankClass unicredit = new BankClass(true, true, false, false, false, true,
                true, true, "ЮниКредит Банк", false,
                "119034, г. Москва,\nПречистенская наб., д. 9", R.drawable.ic_unicredit, "1", "1027739082106", "https://www.unicreditbank.ru/", "geo:0,0?q=ЮниКредит+банк+");
        bankClass.add(unicredit);

        BankClass raiffeisen = new BankClass(true, true, false, false, false, true,
                true, true, "Райффайзенбанк", false,
                "129090, г. Москва,\nул. Троицкая, д. 17, стр. 1", R.drawable.ic_raiffeisen, "3292", "1027739326449", "https://www.raiffeisen.ru/", "geo:0,0?q=Райффайзенбанк+");
        bankClass.add(raiffeisen);

        BankClass sovkombank = new BankClass(true, true, false, false, false, true,
                true, true, "Совкомбанк", false,
                "156000, г. Кострома,\nпросп. Текстильщиков, д. 46", R.drawable.ic_sovkombank, "963", "1144400000425", "https://www.sovcombank.ru/", "geo:0,0?q=Совкомбанк+");
        bankClass.add(sovkombank);

        BankClass rosbank = new BankClass(true, true, false, false, false, true,
                true, true, "Росбанк", false,
                "107078, г. Москва,\nул. Маши Порываевой, д. 34", R.drawable.ic_rosbank, "2272", "1027739460737", "https://www.rosbank.ru/", "geo:0,0?q=Росбанк+");
        bankClass.add(rosbank);

        BankClass rossiya = new BankClass(true, true, false, false, false, true,
                true, true, "Банк Россия", false,
                "191124, г. Санкт-Петербург,\nпл. Растрелли,\nд. 2, литер А", R.drawable.ic_rossiya, "328", "1027800000084", "https://www.abr.ru/", "geo:0,0?q=банк+Россия+");
        bankClass.add(rossiya);

        BankClass spbbank = new BankClass(true, true, false, false, false, true,
                true, true, "Банк Санкт-Петербург", false,
                "195112, г. Санкт-Петербург,\nМалоохтинский просп.,\nд. 64", R.drawable.ic_spbbank, "436", "1027800000140", "https://www.bspb.ru/", "geo:0,0?q=банк+Санкт-Петербург+");
        bankClass.add(spbbank);

        BankClass citibank = new BankClass(true, true, false, false, false, true,
                true, true, "Ситибанк", false,
                "125047, г. Москва,\nул. Гашека, д. 8—10", R.drawable.ic_citibank, "2557", "1027700431296", "https://www.citibank.ru/", "geo:0,0?q=Ситибанк+");
        bankClass.add(citibank);

        BankClass akbars = new BankClass(true, true, false, false, false, true,
                true, true, "Ак Барс Банк", false,
                "420066, г. Казань,\nул. Декабристов, д. 1", R.drawable.ic_akbars, "2590", "1021600000124", "https://www.akbars.ru/", "geo:0,0?q=Ак+Барс+Банк+");
        bankClass.add(akbars);

        BankClass smpbank = new BankClass(true, true, false, false, false, true,
                true, true, "СМП Банк", false,
                "113035, г. Москва,\nул. Садовническая,\nд. 71, стр. 11", R.drawable.ic_smpbank, "3368", "1097711000078", "https://www.smpbank.ru/", "geo:0,0?q=СМП+Банк+");
        bankClass.add(smpbank);

        BankClass novicom = new BankClass(true, true, false, false, false, true,
                true, true, "Новикомбанк", false,
                "119180, г. Москва,\nул. Полянка Большая,\nд. 50/1, стр. 1", R.drawable.ic_novikom, "2546", "1027739075891", "https://www.novikom.ru/", "geo:0,0?q=Новикомбанк+");
        bankClass.add(novicom);

        BankClass uralsib = new BankClass(true, true, false, false, false, true,
                true, true, "Уралсиб Банк", false,
                "119048, г. Москва,\nул. Ефремова, д. 8", R.drawable.ic_uralsib, "30", "1020280000190", "https://www.uralsib.ru/", "geo:0,0?q=Уралсил+Банк+");
        bankClass.add(uralsib);

        BankClass pochtabank = new BankClass(true, true, false, false, false, true,
                true, true, "Почта Банк", false,
                "107061, г. Москва,\nПреображенская пл., д. 8", R.drawable.ic_pochtabank, "650", "1023200000010", "https://www.pochtabank.ru/", "geo:0,0?q=Почта+Банк+");
        bankClass.add(pochtabank);

        BankClass domrf = new BankClass(true, true, false, false, false, true,
                true, true, "ДОМ.РФ Банк", false,
                "125009, г. Москва,\nул. Воздвиженка, д. 10", R.drawable.ic_domrf, "2312", "1037739527077", "https://www.domrfbank.ru/", "geo:0,0?q=Дом.рф+Банк+");
        bankClass.add(domrf);

        BankClass rstandart = new BankClass(true, true, false, false, false, true,
                true, true, "Русский Стандарт", false,
                "105187, г. Москва,\nул. Ткацкая, д. 36", R.drawable.ic_rstand, "2289", "1027739210630", "https://www.rsb.ru/", "geo:0,0?q=Русский+Стандарт+Банк+");
        bankClass.add(rstandart);

        BankClass absolut = new BankClass(true, true, false, false, false, true,
                true, true, "Абсолют Банк", false,
                "127051, г. Москва,\nЦветной б-р, д. 18", R.drawable.ic_absolut, "2306", "1027700024560", "https://www.absolutbank.ru/", "geo:0,0?q=Абсолют+Банк+");
        bankClass.add(absolut);

        BankClass vozrojdenie = new BankClass(true, true, false, false, false, true,
                true, true, "Банк Возрождение", false,
                "101000, г. Москва,\nЛучников пер., д. 7/4, стр. 1", R.drawable.ic_vozrojdenie, "1439", "1027700540680", "https://www.vbank.ru/", "geo:0,0?q=Возрождение+Банк+");
        bankClass.add(vozrojdenie);

        BankClass zenit = new BankClass(true, true, false, false, false, true,
                true, true, "Банк Зенит", false,
                "117638, г. Москва,\nОдесская ул., д. 2", R.drawable.ic_zenit, "3255", "1027739056927", "https://www.zenit.ru/", "geo:0,0?q=Зенит+Банк+");
        bankClass.add(zenit);

        BankClass mts = new BankClass(true, true, false, false, false, true,
                true, true, "МТС-Банк", false,
                "115432, г. Москва,\nпросп. Андропова,\nд. 18, корп. 1", R.drawable.ic_mts, "2268", "1027739053704", "https://www.mtsbank.ru/", "geo:0,0?q=МТС+Банк+");
        bankClass.add(mts);

        BankClass reneissanse = new BankClass(true, true, false, false, false, true,
                true, true, "Ренессанс Кредит", false,
                "115114, г. Москва,\nул. Кожевническая, д. 14", R.drawable.ic_reneissanse, "3354", "1027739586291", "https://www.rencredit.ru/", "geo:0,0?q=Ренессанс+Кредит+");
        bankClass.add(reneissanse);
    }

    public static boolean isIfFind() {
        return ifFind;
    }

    public static void searchBankItems(int switchDebitCard, int switchCreditCard, int switchCreditCash, int switchForeigner, int switchMortgage, int switchDeposit,
                                int switchInsurance, int switchInvestments, int switchPersonality){
        ifFind = false;
        boolean forPerson = false;
        boolean forDebitCard = false;
        boolean forCreditCard = false;
        boolean forCreditCash = false;
        boolean forDeposit = false;
        boolean forMortgage = false;
        boolean forInsurance = false;
        boolean forInvestments = false;
        boolean forForeign = false;
        for (int i = 0; i < bankClass.size(); i++) {

            switch (switchPersonality) {
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

            switch (switchCreditCard) {
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

            switch (switchDebitCard) {
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

            switch (switchCreditCash) {
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

            switch (switchDeposit) {
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

            switch (switchMortgage) {
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

            switch (switchInvestments) {
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

            switch (switchInsurance) {
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

            switch (switchForeigner) {
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
    }
}
