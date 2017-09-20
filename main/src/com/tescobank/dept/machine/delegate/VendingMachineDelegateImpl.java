/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tescobank.dept.machine.delegate;

import com.tescobank.dept.machine.model.Coin;
import com.tescobank.dept.machine.model.EnumItemType;
import com.tescobank.dept.machine.model.AvailableItem;
import com.tescobank.dept.machine.model.VendingMachine;
import java.util.ArrayList;
import java.util.Collection;
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
            result = selection;
        } else // check to see if too much money was inserted
        if (itemCost < insertedTotal) {
            // check to see if enough change is left
            if (changeTotal < (insertedTotal - itemCost)) {
                // not enough change left
                return EnumItemType.NO_VEND;
            }

            // here should be some clever code to determine if the correct coins for the change are available.
            // (clever code available in version 2)
            if (true) {
                result = selection;
            }
        }

        // check to see if the selection was okay
        if (!EnumItemType.NO_VEND.equals(result)) {
            // selection okay so tidy up
            removeStockLevel(vendingMachine, result, 1);
            vendingMachine.addAvailableChange(vendingMachine.getInsertedMoney());
            vendingMachine.clearAllInsertedMoney();
            // there should be code here to return the change as well
        }

        return result;
    }

    @Override
    public void loadAvailableChange(VendingMachine vendingMachine, Collection<Coin> availableChange) {
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
    public Collection<Coin> returnCoins(VendingMachine vendingMachine) {
        Collection<Coin> result = new ArrayList<Coin>();
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
    public Collection<Coin> getAvailableChange(VendingMachine vendingMachine) {
        return vendingMachine.getAvailableChange();
    }

    @Override
    public Integer getCurrentlyInsertedCoinValue(VendingMachine vendingMachine) {
        Integer result = 0;

        for (Coin coin : vendingMachine.getInsertedMoney()) {
            result = result + coin.getValue().getValue();
        }

        return result;
    }

    @Override
    public Integer getAvailableChangeValue(VendingMachine vendingMachine) {
        Integer result = 0;

        for (Coin coin : vendingMachine.getAvailableChange()) {
            result = result + coin.getValue().getValue();
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

        for (Coin coin : coins) {
            removeAvailableChange(vendingMachine, coin);
        }
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
