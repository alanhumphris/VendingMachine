/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyname.dept.machine.service;

import com.companyname.dept.machine.model.Coin;
import com.companyname.dept.machine.model.AvailableItem;
import com.companyname.dept.machine.model.EnumItemType;
import java.util.List;
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

    List<Coin> returnCoins();

    void turnOn();

    void turnOff();

    boolean isOn();

    List<Coin> getAvailableChange();

    String displayCurrentlyInsertedCoinTotal();

    String displayAvailableChangeTotal();

    void restockMachine(List<Coin> availableChange, Set<AvailableItem> stock);

}
