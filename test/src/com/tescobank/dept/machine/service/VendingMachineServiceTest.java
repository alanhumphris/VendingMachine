/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tescobank.dept.machine.service;

import com.tescobank.dept.machine.model.AvailableItem;
import com.tescobank.dept.machine.model.Coin;
import com.tescobank.dept.machine.model.EnumCurrency;
import com.tescobank.dept.machine.model.EnumDenomination;
import com.tescobank.dept.machine.model.EnumItemType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Alan Humphris
 */
public class VendingMachineServiceTest {

    private VendingMachineService service = null;

    @Before
    public void setUp() throws Exception {
        service = new VendingMachineServiceImpl();
    }

    @After
    public void tearDown() throws Exception {
        service = null;
    }

    @Test
    public void restockMachine() {
        Set<AvailableItem> itemList = new HashSet<AvailableItem>();
        itemList.add(new AvailableItem(EnumItemType.A, 6, EnumItemType.A.getDefaultCost()));
        itemList.add(new AvailableItem(EnumItemType.B, 3, EnumItemType.B.getDefaultCost()));
        itemList.add(new AvailableItem(EnumItemType.C, 2, EnumItemType.C.getDefaultCost()));

        Collection<Coin> changeList = new ArrayList<Coin>();
        changeList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        changeList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        changeList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        changeList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        changeList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED));

        service.restockMachine(changeList, itemList);
        service.turnOn();

        Collection<Coin> result = service.getAvailableChange();

        assertNotNull(result);
        assertEquals(5, result.size());

        int i = 0;
        for (Coin coin : result) {
            if (EnumDenomination.TEN.equals(coin.getValue())) {
                i++;
            }
        }
        assertEquals(2, i);
    }

    @Test
    public void insertCoin() {
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));

        assertEquals("0.60", service.displayCurrentlyInsertedCoinTotal());
    }

    @Test
    public void insertCoinZero() {
        assertEquals("0.00", service.displayCurrentlyInsertedCoinTotal());
    }

    @Test
    public void vendASuccess() {
        restockMachine();

        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));

        EnumItemType result = service.vendA();
        assertNotNull(result);
        assertEquals(EnumItemType.A, result);
    }

    public void vendAFail() {
        restockMachine();

        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));

        EnumItemType result = service.vendA();
        assertNotNull(result);
        assertEquals(EnumItemType.NO_VEND, result);
    }

    @Test
    public void vendBSuccess() {
        restockMachine();

        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));

        EnumItemType result = service.vendB();
        assertNotNull(result);
        assertEquals(EnumItemType.B, result);
    }

    @Test
    public void vendBFail() {
        restockMachine();

        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));

        EnumItemType result = service.vendB();
        assertNotNull(result);
        assertEquals(EnumItemType.NO_VEND, result);
    }

    @Test
    public void vendCSuccess() {
        restockMachine();

        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));

        EnumItemType result = service.vendC();
        assertNotNull(result);
        assertEquals(EnumItemType.C, result);
    }

    @Test
    public void vendCFail() {
        restockMachine();

        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));

        EnumItemType result = service.vendC();
        assertNotNull(result);
        assertEquals(EnumItemType.NO_VEND, result);
    }

    @Test
    public void returnCoins() {
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        assertEquals("0.60", service.displayCurrentlyInsertedCoinTotal());

        Collection<Coin> result = service.returnCoins();

        assertEquals("0.00", service.displayCurrentlyInsertedCoinTotal());
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void turnOn() {
        service.turnOff();
        assertFalse(service.isOn());
        service.turnOn();
        assertTrue(service.isOn());
    }

    @Test
    public void turnOff() {
        service.turnOn();
        assertTrue(service.isOn());
        service.turnOff();
        assertFalse(service.isOn());
    }

    @Test
    public void isOnTrue() {
        service.turnOff();
        assertFalse(service.isOn());
        service.turnOn();
        assertTrue(service.isOn());
    }

    @Test
    public void isOnFalse() {
         assertFalse(service.isOn());
    }

    @Test
    public void displayCurrentlyInsertedCoinTotal() {
        restockMachine();
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        assertEquals("0.60", service.displayCurrentlyInsertedCoinTotal());
    }

    @Test
    public void displayAvailableChangeTotal() {
        restockMachine();
        assertEquals("1.90", service.displayAvailableChangeTotal());
    }

}
