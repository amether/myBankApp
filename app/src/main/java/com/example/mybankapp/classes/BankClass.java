package com.example.mybankapp.classes;

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



    public BankClass(boolean debitCard, boolean creditCard, boolean creditCash, boolean forForeignClients, boolean mortgage, boolean deposit,
                     boolean forPrivatePerson, boolean insurance, String name, boolean investments, boolean legalCredits,
                     boolean legalAcquiring, boolean salaryAccount, String adress, int image, String license, String ogrn, String site,
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
}
