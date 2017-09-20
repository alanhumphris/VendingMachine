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
import java.util.Collection;
import java.util.Set;

/**
 * Encapsulates the operations of a vending machine
 *
 * @author Alan Humphris
 */
public interface VendingMachineDelegate {
    
    EnumItemType vend(VendingMachine vendingMachine, EnumItemType selection);
    
    boolean insertCoin(VendingMachine vendingMachine, Coin coin);
    
    Collection<Coin> returnCoins(VendingMachine vendingMachine);
    
    void turnOn(VendingMachine vendingMachine);
    
    void turnOff(VendingMachine vendingMachine);
    
    boolean isOn(VendingMachine vendingMachine);
    
    Collection<Coin> getAvailableChange(VendingMachine vendingMachine);

    Integer getCurrentlyInsertedCoinValue(VendingMachine vendingMachine);
    
    Integer getAvailableChangeValue(VendingMachine vendingMachine);
   
    void loadAvailableChange(VendingMachine vendingMachine, Collection<Coin> availableChange);
    
    void loadStock(VendingMachine vendingMachine, Set<AvailableItem> stock);
    
}
