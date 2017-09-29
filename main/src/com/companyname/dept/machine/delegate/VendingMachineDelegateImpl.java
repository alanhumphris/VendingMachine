/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyname.dept.machine.delegate;

import com.companyname.dept.machine.model.Coin;
import com.companyname.dept.machine.model.EnumItemType;
import com.companyname.dept.machine.model.AvailableItem;
import com.companyname.dept.machine.model.VendingMachine;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Encapsulates the operations of a vending machine
 *
 * @author Alan Humphris
 */
public class VendingMachineDelegateImpl implements VendingMachineDelegate {

    @Override
    public EnumItemType vend(VendingMachine vendingMachine, EnumItemType selection) {
        EnumItemType result = EnumItemType.NO_VEND;

        if ((vendingMachine == null) || (selection == null)) {
            return result;
        }

        if (vendingMachine.isOff()) {
            return result;
        }

        Integer insertedTotal = getCurrentlyInsertedCoinValue(vendingMachine);
        Integer changeTotal = getAvailableChangeValue(vendingMachine);
        AvailableItem availableItem = findAvailableItemForType(vendingMachine, selection);
        Integer itemCost = availableItem.getCost();

        // check to see if stock available
        if (availableItem.getLevel() <= 0) {
            // no stock left
            return EnumItemType.NO_VEND;
        }

        // check to see if enough money was inserted
        if (itemCost > insertedTotal) {
            // not enough money inserted
            return EnumItemType.NO_VEND;
        }

        // check to see if exact money was inserted
        if (itemCost.equals(insertedTotal)) {
            doVend(vendingMachine, selection, itemCost, null);
            result = selection;
        } else // check to see if too much money was inserted
        if ((insertedTotal >= itemCost) && (changeTotal >= (insertedTotal - itemCost))) {
            // check to see if the correct change is available
            Map<Integer, List<List<Coin>>> changeToGive = new HashMap<>();
            List<Coin> partialSolution = new ArrayList<>();
            List<Coin> combinedCoins = new ArrayList<>();
            combinedCoins.addAll(vendingMachine.getAvailableChange());
            combinedCoins.addAll(vendingMachine.getInsertedMoney());
            int changeExpected = insertedTotal - itemCost;
            ChangeMachineHelper.change(changeToGive, changeExpected, changeExpected, combinedCoins, partialSolution);

            if (!changeToGive.get(changeExpected).isEmpty()) {
                doVend(vendingMachine, selection, itemCost, changeToGive);
                result = selection;
            }

        }

        return result;
    }

    protected void doVend(VendingMachine vendingMachine, EnumItemType selection, Integer itemCost, Map<Integer, List<List<Coin>>> changeToGive) {
        // selection okay so tidy up
        removeStockLevel(vendingMachine, selection, 1);
        vendingMachine.addAvailableChange(vendingMachine.getInsertedMoney());
        vendingMachine.clearAllInsertedMoney();
        if (changeToGive != null) {
            List<List<Coin>> options = changeToGive.get(itemCost);
            if ((options != null) && (!options.isEmpty())) {
                vendingMachine.getAvailableChange().removeAll(options.get(0));
            }
        }
    }

    @Override
    public void loadAvailableChange(VendingMachine vendingMachine, List<Coin> availableChange) {
        vendingMachine.clearAllAvailableChange();
        vendingMachine.addAvailableChange(availableChange);
    }

    @Override
    public void loadStock(VendingMachine vendingMachine, Set<AvailableItem> stock) {
        vendingMachine.clearAllStock();
        vendingMachine.setStock(stock);
    }

    @Override
    public boolean insertCoin(VendingMachine vendingMachine, Coin coin) {
        boolean result = true;
        vendingMachine.addInsertedMoney(coin);
        return result;
    }

    @Override
    public List<Coin> returnCoins(VendingMachine vendingMachine) {
        List<Coin> result = new ArrayList<>();
        result.addAll(vendingMachine.getInsertedMoney());
        vendingMachine.clearAllInsertedMoney();
        return result;
    }

    @Override
    public void turnOn(VendingMachine vendingMachine) {
        vendingMachine.setOn(true);
    }

    @Override
    public void turnOff(VendingMachine vendingMachine) {
        vendingMachine.setOn(false);
    }

    @Override
    public boolean isOn(VendingMachine vendingMachine) {
        return vendingMachine.isOn();
    }

    @Override
    public List<Coin> getAvailableChange(VendingMachine vendingMachine) {
        return vendingMachine.getAvailableChange();
    }

    @Override
    public Integer getCurrentlyInsertedCoinValue(VendingMachine vendingMachine) {
        Integer result = 0;

        for (Coin coin : vendingMachine.getInsertedMoney()) {
            result = result + coin.getDenomination().getValue();
        }

        return result;
    }

    @Override
    public Integer getAvailableChangeValue(VendingMachine vendingMachine) {
        Integer result = 0;

        for (Coin coin : vendingMachine.getAvailableChange()) {
            result = result + coin.getDenomination().getValue();
        }

        return result;
    }

    // *** protected methods ***
    protected void removeAvailableChange(VendingMachine vendingMachine, Coin coin) {
        if (coin == null) {
            return;
        }

        if (vendingMachine.getAvailableChange().contains(coin)) {
            vendingMachine.getAvailableChange().remove(coin);
        }
    }

    protected void removeAvailableChange(VendingMachine vendingMachine, Collection<Coin> coins) {
        if (coins == null) {
            return;
        }

        coins.forEach((coin) -> {
            removeAvailableChange(vendingMachine, coin);
        });
    }

    protected Integer getStockLevel(VendingMachine vendingMachine, EnumItemType itemType) {
        Integer result = 0;

        if ((vendingMachine == null) || (itemType == null)) {
            return result;
        }

        for (AvailableItem item : vendingMachine.getStock()) {
            if (item.getItemType().equals(itemType)) {
                result = item.getLevel();
                break;
            }
        }

        return result;
    }

    protected void addStockLevel(VendingMachine vendingMachine, EnumItemType itemType, Integer count) {
        if ((vendingMachine == null) || (itemType == null) || (count == null)) {
            return;
        }

        for (AvailableItem item : vendingMachine.getStock()) {
            if (item.getItemType().equals(itemType)) {
                item.setLevel(item.getLevel() + count);
                break;
            }
        }
    }

    protected void removeStockLevel(VendingMachine vendingMachine, EnumItemType itemType, Integer count) {
        if ((vendingMachine == null) || (itemType == null) || (count == null)) {
            return;
        }

        for (AvailableItem item : vendingMachine.getStock()) {
            if (item.getItemType().equals(itemType)) {
                item.setLevel(item.getLevel() - count);
                break;
            }
        }
    }

    protected AvailableItem findAvailableItemForType(VendingMachine vendingMachine, EnumItemType itemType) {
        AvailableItem result = null;

        for (AvailableItem item : vendingMachine.getStock()) {
            if (item.getItemType().equals(itemType)) {
                result = item;
                break;
            }
        }

        return result;
    }

}
