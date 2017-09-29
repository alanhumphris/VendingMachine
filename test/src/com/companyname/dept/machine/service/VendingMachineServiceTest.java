/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyname.dept.machine.service;

import com.companyname.dept.machine.model.AvailableItem;
import com.companyname.dept.machine.model.Coin;
import com.companyname.dept.machine.model.EnumCurrency;
import com.companyname.dept.machine.model.EnumDenomination;
import com.companyname.dept.machine.model.EnumItemType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    public void shouldRestockMachine() {
        restockMachine();
    }

    public void restockMachine() {
        Set<AvailableItem> itemList = new HashSet<>();
        itemList.add(new AvailableItem(EnumItemType.A, 6, EnumItemType.A.getDefaultCost()));
        itemList.add(new AvailableItem(EnumItemType.B, 3, EnumItemType.B.getDefaultCost()));
        itemList.add(new AvailableItem(EnumItemType.C, 2, EnumItemType.C.getDefaultCost()));

        List<Coin> changeList = new ArrayList<>();
        changeList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        changeList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        changeList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        changeList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        changeList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED));

        service.restockMachine(changeList, itemList);
        service.turnOn();

        List<Coin> result = service.getAvailableChange();

        assertNotNull(result);
        assertEquals(5, result.size());

        int i = 0;
        i = result.stream().filter((coin) -> (EnumDenomination.TEN.equals(coin.getDenomination()))).map((_item) -> 1).reduce(i, Integer::sum);
        assertEquals(2, i);
    }

    @Test
    public void shouldInsertCoin() {
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));

        assertEquals("0.60", service.displayCurrentlyInsertedCoinTotal());
    }

    @Test
    public void shouldInsertCoinWhenZero() {
        assertEquals("0.00", service.displayCurrentlyInsertedCoinTotal());
    }

    @Test
    public void shouldVendASuccess() {
        restockMachine();

        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));

        EnumItemType result = service.vendA();
        assertNotNull(result);
        assertEquals(EnumItemType.A, result);
    }

    public void shouldVendAFail() {
        restockMachine();

        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));

        EnumItemType result = service.vendA();
        assertNotNull(result);
        assertEquals(EnumItemType.NO_VEND, result);
    }

    @Test
    public void shouldVendBSuccess() {
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
    public void shouldVendBFail() {
        restockMachine();

        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));

        EnumItemType result = service.vendB();
        assertNotNull(result);
        assertEquals(EnumItemType.NO_VEND, result);
    }

    @Test
    public void shouldVendCSuccess() {
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
    public void shouldVendCFail() {
        restockMachine();

        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));

        EnumItemType result = service.vendC();
        assertNotNull(result);
        assertEquals(EnumItemType.NO_VEND, result);
    }

    @Test
    public void shouldReturnCoins() {
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        assertEquals("0.60", service.displayCurrentlyInsertedCoinTotal());

        List<Coin> result = service.returnCoins();

        assertEquals("0.00", service.displayCurrentlyInsertedCoinTotal());
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void shouldTurnOn() {
        service.turnOff();
        assertFalse(service.isOn());
        service.turnOn();
        assertTrue(service.isOn());
    }

    @Test
    public void shouldTurnOff() {
        service.turnOn();
        assertTrue(service.isOn());
        service.turnOff();
        assertFalse(service.isOn());
    }

    @Test
    public void shouldBeOnTrue() {
        service.turnOff();
        assertFalse(service.isOn());
        service.turnOn();
        assertTrue(service.isOn());
    }

    @Test
    public void shouldBeOnFalse() {
        assertFalse(service.isOn());
    }

    @Test
    public void shouldDisplayCurrentlyInsertedCoinTotal() {
        restockMachine();
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        service.insertCoin(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        assertEquals("0.60", service.displayCurrentlyInsertedCoinTotal());
    }

    @Test
    public void shouldDisplayAvailableChangeTotal() {
        restockMachine();
        assertEquals("1.90", service.displayAvailableChangeTotal());
    }

}
