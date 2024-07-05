package com.kayusgold.nubanjava.models;

import java.util.ArrayList;
import java.util.List;

public class BankUtil {

    public static List<Bank> getBanks() {
        List<Bank> banks = new ArrayList<>();

        banks.add(new Bank("ACCESS BANK", "044", "DMB"));
        banks.add(new Bank("CITIBANK", "023", "DMB"));
        banks.add(new Bank("DIAMOND BANK", "063", "DMB"));
        banks.add(new Bank("ECOBANK NIGERIA", "050", "DMB"));
        banks.add(new Bank("FIDELITY BANK", "070", "DMB"));
        banks.add(new Bank("FIRST BANK OF NIGERIA", "011", "DMB"));
        banks.add(new Bank("FIRST CITY MONUMENT BANK", "214", "DMB"));
        banks.add(new Bank("GUARANTY TRUST BANK", "058", "DMB"));
        banks.add(new Bank("HERITAGE BANK", "030", "DMB"));
        banks.add(new Bank("JAIZ BANK", "301", "DMB"));
        banks.add(new Bank("KEYSTONE BANK", "082", "DMB"));
        banks.add(new Bank("PROVIDUS BANK", "101", "DMB"));
        banks.add(new Bank("SKYE BANK", "076", "DMB"));
        banks.add(new Bank("STANBIC IBTC BANK", "221", "DMB"));
        banks.add(new Bank("STANDARD CHARTERED BANK", "068", "DMB"));
        banks.add(new Bank("STERLING BANK", "232", "DMB"));
        banks.add(new Bank("SUNTRUST", "100", "DMB"));
        banks.add(new Bank("UNION BANK OF NIGERIA", "032", "DMB"));
        banks.add(new Bank("UNITED BANK FOR AFRICA", "033", "DMB"));
        banks.add(new Bank("UNITY BANK", "215", "DMB"));
        banks.add(new Bank("WEMA BANK", "035", "DMB"));
        banks.add(new Bank("ZENITH BANK", "057", "DMB"));
        banks.add(new Bank("OFI BANK TEST", "50547", "OFI"));

        return banks;
    }
}
