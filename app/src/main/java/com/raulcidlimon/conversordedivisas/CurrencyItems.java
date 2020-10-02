package com.raulcidlimon.conversordedivisas;

public class CurrencyItems {
    private String currencyName;
    private int FlagImage;

    public CurrencyItems(String currencyName, int flagImage) {
        this.currencyName = currencyName;
        FlagImage = flagImage;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public int getFlagImage() {
        return FlagImage;
    }

}
