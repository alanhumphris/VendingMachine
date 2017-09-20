/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tescobank.dept.machine.service;

import com.tescobank.dept.machine.model.Coin;
import com.tescobank.dept.machine.model.AvailableItem;
import com.tescobank.dept.machine.model.EnumItemType;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author Alan Humphris
 */
public interface VendingMachineService {

    EnumItemType vendA();

    EnumItemType vendB();

    EnumItemType vendC();

    boolean insertCoin(Coin coin);

    Collection<Coin> returnCoins();

    void turnOn();

    void turnOff();

    boolean isOn();

    Collection<Coin> getAvailableChange();

    String displayCurrentlyInsertedCoinTotal();

    String displayAvailableChangeTotal();

    void restockMachine(Collection<Coin> availableChange, Set<AvailableItem> stock);

}
