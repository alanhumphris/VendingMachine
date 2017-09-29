package com.companyname.dept.machine.model;

import java.util.HashMap;
import java.util.Map;

public enum EnumCurrency {

    // known currencies
    // if a client passes a type not in this list it will get set to unknown
    UNKNOWN("Unknown Currency", "UNK", false), //
    STERLING("Pounds Sterling", "GBP", true);//

    private final String description;
    private final String currencyCode;
    private final boolean canBeUsed;

    // reverse-lookup map for getting a EnumCurrency from a currency code
    private static final Map<String, EnumCurrency> lookup = new HashMap<String, EnumCurrency>();

    static {
        for (EnumCurrency cet : EnumCurrency.values()) {
            lookup.put(cet.getCurrencyCode(), cet);
        }
    }

    EnumCurrency(String description, String currencyCode, boolean canBeUsed) {
        this.description = description;
        this.currencyCode = currencyCode;
        this.canBeUsed = canBeUsed;
    }

    public String getDescription() {
        return description;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public boolean canBeUsed() {
        return canBeUsed;
    }

    public static EnumCurrency get(String currencyCode) {
        EnumCurrency result = lookup.get(currencyCode);

        if (result == null) {
            result = EnumCurrency.UNKNOWN;
        }

        return result;
    }

    public static boolean contains(String currencyCode) {
        boolean result = lookup.get(currencyCode) != null;

        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("name: ");
        result.append(this.name());
        result.append(", currencyCode: ");
        result.append(getCurrencyCode());
        result.append(", canBeUsed: ");
        result.append(canBeUsed());

        return result.toString();
    }

}
