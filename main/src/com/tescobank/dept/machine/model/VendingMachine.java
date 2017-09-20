package com.tescobank.dept.machine.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Encapsulates the state of a vending machine
 *
 * @author Alan Humphris
 */
public class VendingMachine {

    private boolean on = false;
    private Collection<Coin> insertedMoney;
    private Collection<Coin> availableChange;
    private Set<AvailableItem> stock;

    public VendingMachine() {
        super();
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public boolean isOn() {
        return on;
    }

    public boolean isOff() {
        return !on;
    }

    public Collection<Coin> getInsertedMoney() {
        if (this.insertedMoney == null) {
            this.insertedMoney = new ArrayList<Coin>();
        }
        return insertedMoney;
    }

    public void setInsertedMoney(Collection<Coin> insertedMoney) {
        this.insertedMoney = insertedMoney;
    }

    public Collection<Coin> getAvailableChange() {
        if (this.availableChange == null) {
            this.availableChange = new ArrayList<Coin>();
        }
        return availableChange;
    }

    public void setAvailableChange(Collection<Coin> availableChange) {
        this.availableChange = availableChange;
    }

    public Set<AvailableItem> getStock() {
        if (this.stock == null) {
            this.stock = new HashSet<AvailableItem>();
        }
        return stock;
    }

    public void setStock(Set<AvailableItem> stock) {
        this.stock = stock;
    }

    // *** convenience methods to act on properties ***
    
    public void addInsertedMoney(Coin coin) {
        getInsertedMoney().add(coin);
    }

    public void addInsertedMoney(Collection<Coin> coins) {
        getInsertedMoney().addAll(coins);
    }

    public void clearAllInsertedMoney() {
        getInsertedMoney().clear();
        setInsertedMoney(new ArrayList<Coin>());
    }

    public void addAvailableChange(Coin coin) {
        getAvailableChange().add(coin);
    }

    public void addAvailableChange(Collection<Coin> coins) {
        getAvailableChange().addAll(coins);
    }

    public void clearAllAvailableChange() {
        getAvailableChange().clear();
        setAvailableChange(new ArrayList<Coin>());
    }

    public void clearAllStock() {
        getStock().clear();
        setStock(new HashSet<AvailableItem>());
    }

}
