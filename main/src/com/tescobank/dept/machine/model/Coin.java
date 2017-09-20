/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tescobank.dept.machine.model;

import java.util.Objects;

/**
 * A single coin entered into machine
 * 
 * @author Alan Humphris
 */
public class Coin {

    // currency of coin
    private EnumCurrency currency;
    // value of coin in the common subunit of the currency (e.g. pence, cents)
    private EnumDenomination value;

    public Coin(EnumCurrency currency, EnumDenomination value) {
        this.currency = currency;
        this.value = value;
    }

    public EnumDenomination getValue() {
        return value;
    }

    public void setValue(EnumDenomination value) {
        this.value = value;
    }

    public EnumCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(EnumCurrency currency) {
        this.currency = currency;
    }

    public boolean isEqual(Coin coin) {
        boolean result = false;

        if (coin == null) {
            return false;
        }

        result = ((this.getValue() == null) && (this.getCurrency() == null) && (coin.getValue() == null) && (coin.getCurrency() == null))
                || (((this.getValue() != null) && this.getValue().equals(coin.getValue())
                && (this.getCurrency() != null) && this.getCurrency().equals(coin.getCurrency())));

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.currency);
        hash = 67 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coin other = (Coin) obj;
        if (this.currency != other.currency) {
            return false;
        }
        if (this.value != other.value) {
            return false;
        }
        return true;
    }

}
