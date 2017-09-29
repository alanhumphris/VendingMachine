/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyname.dept.machine.service;

import com.companyname.dept.machine.delegate.VendingMachineDelegate;
import com.companyname.dept.machine.delegate.VendingMachineDelegateImpl;
import com.companyname.dept.machine.model.Coin;
import com.companyname.dept.machine.model.EnumItemType;
import com.companyname.dept.machine.model.AvailableItem;
import com.companyname.dept.machine.model.VendingMachine;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Alan Humphris
 */
public class VendingMachineServiceImpl implements VendingMachineService {

    // should be autowired
    //@Autowired
    private VendingMachineDelegate vendingMachineDelegate = new VendingMachineDelegateImpl();

    // this service could be state free, but for purposes of this test create state
    private VendingMachine vendingMachine;

    public VendingMachineServiceImpl() {
        vendingMachine = new VendingMachine();
    }

    @Override
    public EnumItemType vendA() {
        EnumItemType result = vendingMachineDelegate.vend(getVendingMachine(), EnumItemType.A);
        return result;
    }

    @Override
    public EnumItemType vendB() {
        EnumItemType result = vendingMachineDelegate.vend(getVendingMachine(), EnumItemType.B);
        return result;
    }

    @Override
    public EnumItemType vendC() {
        EnumItemType result = vendingMachineDelegate.vend(getVendingMachine(), EnumItemType.C);
        return result;
    }

    @Override
    public boolean insertCoin(Coin coin) {
        boolean result = vendingMachineDelegate.insertCoin(getVendingMachine(), coin);
        return result;
    }

    @Override
    public List<Coin> returnCoins() {
        List<Coin> result = vendingMachineDelegate.returnCoins(getVendingMachine());
        return result;
    }

    @Override
    public void turnOn() {
        vendingMachineDelegate.turnOn(getVendingMachine());
    }

    @Override
    public void turnOff() {
        vendingMachineDelegate.turnOff(getVendingMachine());
    }

    @Override
    public boolean isOn() {
        return vendingMachineDelegate.isOn(getVendingMachine());
    }

    @Override
    public List<Coin> getAvailableChange() {
        List<Coin> result = vendingMachineDelegate.getAvailableChange(getVendingMachine());
        return result;
    }

    @Override
    public String displayCurrentlyInsertedCoinTotal() {
        Integer total = vendingMachineDelegate.getCurrentlyInsertedCoinValue(getVendingMachine());
        String result = "0.00";

        if (total != null) {
            DecimalFormat df = new DecimalFormat("##0.00");
            result = df.format(total.doubleValue() / 100);
        }

        return result;
    }

    @Override
    public String displayAvailableChangeTotal() {
        Integer total = vendingMachineDelegate.getAvailableChangeValue(getVendingMachine());
        String result = "0.00";

        if (total != null) {
            DecimalFormat df = new DecimalFormat("##0.00");
            result = df.format(total.doubleValue() / 100);
        }
        
        return result;
    }

    @Override
    public void restockMachine(List<Coin> balance, Set<AvailableItem> stock) {
        vendingMachineDelegate.returnCoins(getVendingMachine());
        vendingMachineDelegate.loadAvailableChange(getVendingMachine(), balance);
        vendingMachineDelegate.loadStock(getVendingMachine(), stock);
    }

    protected VendingMachine getVendingMachine() {
        return vendingMachine;
    }
}
