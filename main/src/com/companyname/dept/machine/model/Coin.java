/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyname.dept.machine.model;

import java.util.Objects;

/**
 * A single coin entered into machine
 *
 * @author Alan Humphris
 */
public class Coin extends CoinBase<Coin> {

    // currency of coin
    private EnumCurrency currency;
    // value of coin in the common subunit of the currency (e.g. pence, cents)
    private EnumDenomination denomination;

    public Coin(EnumCurrency currency, EnumDenomination denomination) {
        this.currency = currency;
        this.denomination = denomination;
    }

    public EnumDenomination getDenomination() {
        return denomination;
    }

    public void setDenomination(EnumDenomination denomination) {
        this.denomination = denomination;
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

        result = ((this.getDenomination() == null) && (this.getCurrency() == null) && (coin.getDenomination() == null) && (coin.getCurrency() == null))
                || (((this.getDenomination() != null) && this.getDenomination().equals(coin.getDenomination())
                && (this.getCurrency() != null) && this.getCurrency().equals(coin.getCurrency())));

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.currency);
        hash = 67 * hash + Objects.hashCode(this.denomination);
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
        if (this.denomination != other.denomination) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Coin c2) {

        return this.getDenomination().compareTo(c2.getDenomination());

    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();

        result.append("currency: ");
        result.append(this.getCurrency());
        result.append(", denomination: ");
        result.append(this.getDenomination());

        return result.toString();
    }
}
