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
import java.util.List;
import java.util.Set;

/**
 * Encapsulates the operations of a vending machine
 *
 * @author Alan Humphris
 */
public interface VendingMachineDelegate {
    
    EnumItemType vend(VendingMachine vendingMachine, EnumItemType selection);
    
    boolean insertCoin(VendingMachine vendingMachine, Coin coin);
    
    List<Coin> returnCoins(VendingMachine vendingMachine);
    
    void turnOn(VendingMachine vendingMachine);
    
    void turnOff(VendingMachine vendingMachine);
    
    boolean isOn(VendingMachine vendingMachine);
    
    List<Coin> getAvailableChange(VendingMachine vendingMachine);

    Integer getCurrentlyInsertedCoinValue(VendingMachine vendingMachine);
    
    Integer getAvailableChangeValue(VendingMachine vendingMachine);
   
    void loadAvailableChange(VendingMachine vendingMachine, List<Coin> availableChange);
    
    void loadStock(VendingMachine vendingMachine, Set<AvailableItem> stock);
    
}
