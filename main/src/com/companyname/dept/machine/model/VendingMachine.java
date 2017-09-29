package com.companyname.dept.machine.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Encapsulates the state of a vending machine
 *
 * @author Alan Humphris
 */
public class VendingMachine {

    private boolean on = false;
    private List<Coin> insertedMoney;
    private List<Coin> availableChange;
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

    public List<Coin> getInsertedMoney() {
        if (this.insertedMoney == null) {
            this.insertedMoney = new ArrayList<>();
        }
        return insertedMoney;
    }

    public void setInsertedMoney(List<Coin> insertedMoney) {
        this.insertedMoney = insertedMoney;
    }

    public List<Coin> getAvailableChange() {
        if (this.availableChange == null) {
            this.availableChange = new ArrayList<>();
        }
        return availableChange;
    }

    public void setAvailableChange(List<Coin> availableChange) {
        this.availableChange = availableChange;
    }

    public Set<AvailableItem> getStock() {
        if (this.stock == null) {
            this.stock = new HashSet<>();
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

    public void addInsertedMoney(List<Coin> coins) {
        getInsertedMoney().addAll(coins);
    }

    public void clearAllInsertedMoney() {
        getInsertedMoney().clear();
        setInsertedMoney(new ArrayList<>());
    }

    public void addAvailableChange(Coin coin) {
        getAvailableChange().add(coin);
    }

    public void addAvailableChange(List<Coin> coins) {
        getAvailableChange().addAll(coins);
    }

    public void clearAllAvailableChange() {
        getAvailableChange().clear();
        setAvailableChange(new ArrayList<>());
    }

    public void clearAllStock() {
        getStock().clear();
        setStock(new HashSet<>());
    }

}
