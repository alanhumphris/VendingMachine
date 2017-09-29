/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyname.dept.machine.delegate;

import com.companyname.dept.machine.model.AvailableItem;
import com.companyname.dept.machine.model.Coin;
import com.companyname.dept.machine.model.EnumCurrency;
import com.companyname.dept.machine.model.EnumDenomination;
import com.companyname.dept.machine.model.EnumItemType;
import com.companyname.dept.machine.model.VendingMachine;
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
public class VendingMachineDelegateTest {

    private VendingMachineDelegateImpl delegate = null;

    @Before
    public void setUp() throws Exception {
        delegate = new VendingMachineDelegateImpl();
    }

    @After
    public void tearDown() throws Exception {
        delegate = null;
    }

    @Test
    public void shouldLoadAvailableChange() {
        List<Coin> change = new ArrayList<>();
        loadDefaultChange(change);

        VendingMachine machine = new VendingMachine();

        delegate.loadAvailableChange(machine, change);

        assertNotNull(delegate.getAvailableChange(machine));
        assertEquals(5, delegate.getAvailableChange(machine).size());

        int i = 0;
        i = delegate.getAvailableChange(machine).stream().filter((coin) -> (EnumDenomination.TEN.equals(coin.getDenomination()))).map((_item) -> 1).reduce(i, Integer::sum);
        assertEquals(2, i);
    }

    @Test
    public void shouldLoadStock() {
        Set<AvailableItem> list = new HashSet<>();
        list.add(new AvailableItem(EnumItemType.A, 6, EnumItemType.A.getDefaultCost()));
        list.add(new AvailableItem(EnumItemType.B, 3, EnumItemType.B.getDefaultCost()));
        list.add(new AvailableItem(EnumItemType.C, 2, EnumItemType.C.getDefaultCost()));

        VendingMachine machine = new VendingMachine();

        delegate.loadStock(machine, list);

        assertNotNull(machine.getStock());
        assertEquals(3, machine.getStock().size());

        AvailableItem foundOne = null;
        for (AvailableItem availableItem : machine.getStock()) {
            if (EnumItemType.A.equals(availableItem.getItemType())) {
                foundOne = availableItem;
            }
        }

        assertNotNull(foundOne);
        assertEquals((Integer) 60, foundOne.getCost());
        assertEquals((Integer) 6, foundOne.getLevel());
    }

    @Test
    public void shouldInsertCoin() {
        VendingMachine machine = new VendingMachine();
        boolean result = delegate.insertCoin(machine, new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED));

        assertTrue(result);
        assertEquals(1, machine.getInsertedMoney().size());
    }

    @Test
    public void shouldReturnCoins() {
        List<Coin> change = new ArrayList<>();
        loadDefaultChange(change);

        VendingMachine machine = new VendingMachine();
        machine.setInsertedMoney(change);

        List<Coin> result = delegate.returnCoins(machine);

        assertNotNull(result);
        assertEquals(5, result.size());

        int i = 0;
        i = result.stream().filter((coin) -> (EnumDenomination.TEN.equals(coin.getDenomination()))).map((_item) -> 1).reduce(i, Integer::sum);
        assertEquals(2, i);

        assertNotNull(machine.getInsertedMoney());
        assertEquals(0, machine.getInsertedMoney().size());
    }

    @Test
    public void shouldTurnOn() {
        VendingMachine machine = new VendingMachine();
        machine.setOn(false);
        delegate.turnOn(machine);
        assertTrue(machine.isOn());
    }

    @Test
    public void shouldTurnOff() {
        VendingMachine machine = new VendingMachine();
        machine.setOn(true);
        delegate.turnOff(machine);
        assertFalse(machine.isOn());
    }

    @Test
    public void shouldBeOnTrue() {
        VendingMachine machine = new VendingMachine();
        machine.setOn(false);
        delegate.turnOn(machine);
        assertTrue(delegate.isOn(machine));
    }

    @Test
    public void shouldBeOnFalse() {
        VendingMachine machine = new VendingMachine();
        machine.setOn(true);
        delegate.turnOff(machine);
        assertFalse(delegate.isOn(machine));
    }

    @Test
    public void shouldGetAvailableChange() {
        List<Coin> availableChange = new ArrayList<>();
        loadDefaultChange(availableChange);

        VendingMachine machine = new VendingMachine();
        machine.setAvailableChange(availableChange);

        List<Coin> result = delegate.getAvailableChange(machine);

        assertNotNull(result);
        assertEquals(5, result.size());

        int i = 0;
        i = result.stream().filter((coin) -> (EnumDenomination.TEN.equals(coin.getDenomination()))).map((_item) -> 1).reduce(i, Integer::sum);
        assertEquals(2, i);
    }

    @Test
    public void shouldGetCurrentlyInsertedCoinValue() {
        List<Coin> change = new ArrayList<>();
        loadDefaultChange(change);

        VendingMachine machine = new VendingMachine();
        machine.setInsertedMoney(change);

        Integer result = delegate.getCurrentlyInsertedCoinValue(machine);

        assertEquals((Integer) 190, result);
    }

    @Test
    public void shouldGetAvailableChangeValue() {
        List<Coin> change = new ArrayList<>();
        loadDefaultChange(change);

        VendingMachine machine = new VendingMachine();
        machine.setAvailableChange(change);

        Integer result = delegate.getAvailableChangeValue(machine);

        assertEquals((Integer) 190, result);
    }

    // *** protected methods ***
    @Test
    public void shouldRemoveAvailableChange() {
        List<Coin> change = new ArrayList<>();
        loadDefaultChange(change);

        VendingMachine machine = new VendingMachine();
        machine.setAvailableChange(change);

        delegate.removeAvailableChange(machine, new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));

        assertNotNull(machine.getAvailableChange());
        assertEquals(4, machine.getAvailableChange().size());
        assertFalse(machine.getAvailableChange().contains(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY)));
    }

    @Test
    public void shouldRemoveAvailableChangeCollection() {
        List<Coin> change = new ArrayList<>();
        loadDefaultChange(change);

        VendingMachine machine = new VendingMachine();
        machine.setAvailableChange(change);

        List<Coin> removeList = new ArrayList<>();
        removeList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        removeList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        removeList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));

        assertEquals(5, machine.getAvailableChange().size());

        delegate.removeAvailableChange(machine, removeList);

        assertNotNull(machine.getAvailableChange());
        assertEquals(2, machine.getAvailableChange().size());
        assertFalse(machine.getAvailableChange().contains(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY)));
        assertFalse(machine.getAvailableChange().contains(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY)));

        assertTrue(machine.getAvailableChange().contains(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN)));
        assertTrue(machine.getAvailableChange().contains(new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED)));
    }

    @Test
    public void shouldGetStockLevel() {
        Set<AvailableItem> list = new HashSet<>();
        list.add(new AvailableItem(EnumItemType.A, 6, EnumItemType.A.getDefaultCost()));
        list.add(new AvailableItem(EnumItemType.B, 3, EnumItemType.B.getDefaultCost()));
        list.add(new AvailableItem(EnumItemType.C, 2, EnumItemType.C.getDefaultCost()));

        VendingMachine machine = new VendingMachine();
        machine.setStock(list);

        assertEquals((Integer) 6, delegate.getStockLevel(machine, EnumItemType.A));
        assertEquals((Integer) 3, delegate.getStockLevel(machine, EnumItemType.B));
        assertEquals((Integer) 2, delegate.getStockLevel(machine, EnumItemType.C));

    }

    @Test
    public void shouldAddStockLevel() {
        Set<AvailableItem> list = new HashSet<>();
        list.add(new AvailableItem(EnumItemType.A, 6, EnumItemType.A.getDefaultCost()));
        list.add(new AvailableItem(EnumItemType.B, 3, EnumItemType.B.getDefaultCost()));
        list.add(new AvailableItem(EnumItemType.C, 2, EnumItemType.C.getDefaultCost()));

        VendingMachine machine = new VendingMachine();
        machine.setStock(list);

        delegate.addStockLevel(machine, EnumItemType.A, 20);
        delegate.addStockLevel(machine, EnumItemType.B, 20);
        delegate.addStockLevel(machine, EnumItemType.C, 20);

        assertEquals((Integer) 26, delegate.getStockLevel(machine, EnumItemType.A));
        assertEquals((Integer) 23, delegate.getStockLevel(machine, EnumItemType.B));
        assertEquals((Integer) 22, delegate.getStockLevel(machine, EnumItemType.C));
    }

    @Test
    public void shouldRemoveStockLevel() {
        Set<AvailableItem> list = new HashSet<>();
        list.add(new AvailableItem(EnumItemType.A, 6, EnumItemType.A.getDefaultCost()));
        list.add(new AvailableItem(EnumItemType.B, 3, EnumItemType.B.getDefaultCost()));
        list.add(new AvailableItem(EnumItemType.C, 2, EnumItemType.C.getDefaultCost()));

        VendingMachine machine = new VendingMachine();
        machine.setStock(list);

        delegate.removeStockLevel(machine, EnumItemType.A, 2);
        delegate.removeStockLevel(machine, EnumItemType.B, 2);
        delegate.removeStockLevel(machine, EnumItemType.C, 2);

        assertEquals((Integer) 4, delegate.getStockLevel(machine, EnumItemType.A));
        assertEquals((Integer) 1, delegate.getStockLevel(machine, EnumItemType.B));
        assertEquals((Integer) 0, delegate.getStockLevel(machine, EnumItemType.C));
    }

    @Test
    public void shouldFindAvailableItemForType() {
        Set<AvailableItem> list = new HashSet<>();
        list.add(new AvailableItem(EnumItemType.A, 6, EnumItemType.A.getDefaultCost()));
        list.add(new AvailableItem(EnumItemType.B, 3, EnumItemType.B.getDefaultCost()));
        list.add(new AvailableItem(EnumItemType.C, 2, EnumItemType.C.getDefaultCost()));

        VendingMachine machine = new VendingMachine();
        machine.setStock(list);

        AvailableItem result = delegate.findAvailableItemForType(machine, EnumItemType.B);

        assertNotNull(result);
        assertEquals(EnumItemType.B, result.getItemType());
    }

    // *** vend tests ***    
    @Test
    public void shouldVendSuccessExactMoney1() {
        Set<AvailableItem> itemList = new HashSet<>();
        itemList.add(new AvailableItem(EnumItemType.A, 6, EnumItemType.A.getDefaultCost()));
        itemList.add(new AvailableItem(EnumItemType.B, 3, EnumItemType.B.getDefaultCost()));
        itemList.add(new AvailableItem(EnumItemType.C, 2, EnumItemType.C.getDefaultCost()));

        List<Coin> change = new ArrayList<>();
        loadDefaultChange(change);

        List<Coin> insertedList = new ArrayList<>();
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));

        VendingMachine machine = new VendingMachine();
        machine.setStock(itemList);
        machine.setAvailableChange(change);
        machine.setInsertedMoney(insertedList);

        Integer changeBefore = delegate.getAvailableChangeValue(machine);
        delegate.turnOn(machine);
        EnumItemType result = delegate.vend(machine, EnumItemType.A);
        assertNotNull(result);
        assertEquals(EnumItemType.A, result);
        Integer expectedChange = changeBefore + 60;
        assertEquals(expectedChange, delegate.getAvailableChangeValue(machine));
    }

    @Test
    public void shouldVendSuccessExactMoney2() {
        Set<AvailableItem> itemList = new HashSet<>();
        itemList.add(new AvailableItem(EnumItemType.A, 6, EnumItemType.A.getDefaultCost()));
        itemList.add(new AvailableItem(EnumItemType.B, 3, EnumItemType.B.getDefaultCost()));
        itemList.add(new AvailableItem(EnumItemType.C, 2, EnumItemType.C.getDefaultCost()));

        List<Coin> change = new ArrayList<>();
        loadDefaultChange(change);

        List<Coin> insertedList = new ArrayList<>();
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));

        VendingMachine machine = new VendingMachine();
        machine.setStock(itemList);
        machine.setAvailableChange(change);
        machine.setInsertedMoney(insertedList);

        Integer changeBefore = delegate.getAvailableChangeValue(machine);
        delegate.turnOn(machine);
        EnumItemType result = delegate.vend(machine, EnumItemType.A);
        assertNotNull(result);
        assertEquals(EnumItemType.A, result);
        Integer expectedChange = changeBefore + 60;
        assertEquals(expectedChange, delegate.getAvailableChangeValue(machine));
    }

    @Test
    public void shouldVendSuccessTooMuchMoney() {
        Set<AvailableItem> itemList = new HashSet<>();
        itemList.add(new AvailableItem(EnumItemType.A, 6, EnumItemType.A.getDefaultCost()));
        itemList.add(new AvailableItem(EnumItemType.B, 3, EnumItemType.B.getDefaultCost()));
        itemList.add(new AvailableItem(EnumItemType.C, 2, EnumItemType.C.getDefaultCost()));

        List<Coin> change = new ArrayList<>();
        loadDefaultChange(change);

        List<Coin> insertedList = new ArrayList<>();
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));

        VendingMachine machine = new VendingMachine();
        machine.setStock(itemList);
        machine.setAvailableChange(change);
        machine.setInsertedMoney(insertedList);

        Integer changeBefore = delegate.getAvailableChangeValue(machine);

        delegate.turnOn(machine);
        EnumItemType result = delegate.vend(machine, EnumItemType.A);
        assertNotNull(result);
        assertEquals(EnumItemType.A, result);
        Integer expectedChange = changeBefore + 150;
        assertEquals(expectedChange, delegate.getAvailableChangeValue(machine));
    }

    @Test
    public void shouldVendFailTooLittleMoney() {
        Set<AvailableItem> itemList = new HashSet<>();
        itemList.add(new AvailableItem(EnumItemType.A, 6, EnumItemType.A.getDefaultCost()));
        itemList.add(new AvailableItem(EnumItemType.B, 3, EnumItemType.B.getDefaultCost()));
        itemList.add(new AvailableItem(EnumItemType.C, 2, EnumItemType.C.getDefaultCost()));

        List<Coin> change = new ArrayList<>();
        loadDefaultChange(change);

        List<Coin> insertedList = new ArrayList<>();
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));

        VendingMachine machine = new VendingMachine();
        machine.setStock(itemList);
        machine.setAvailableChange(change);
        machine.setInsertedMoney(insertedList);

        Integer changeBefore = delegate.getAvailableChangeValue(machine);
        delegate.turnOn(machine);
        EnumItemType result = delegate.vend(machine, EnumItemType.A);
        assertNotNull(result);
        assertEquals(EnumItemType.NO_VEND, result);
        assertEquals(changeBefore, delegate.getAvailableChangeValue(machine));
    }

    @Test
    public void shouldVendFailNoStock() {
        Set<AvailableItem> itemList = new HashSet<>();
        itemList.add(new AvailableItem(EnumItemType.A, 0, EnumItemType.A.getDefaultCost()));
        itemList.add(new AvailableItem(EnumItemType.B, 3, EnumItemType.B.getDefaultCost()));
        itemList.add(new AvailableItem(EnumItemType.C, 2, EnumItemType.C.getDefaultCost()));

        List<Coin> change = new ArrayList<>();
        loadDefaultChange(change);

        List<Coin> insertedList = new ArrayList<>();
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));

        VendingMachine machine = new VendingMachine();
        machine.setStock(itemList);
        machine.setAvailableChange(change);
        machine.setInsertedMoney(insertedList);

        Integer changeBefore = delegate.getAvailableChangeValue(machine);
        delegate.turnOn(machine);
        EnumItemType result = delegate.vend(machine, EnumItemType.A);
        assertNotNull(result);
        assertEquals(EnumItemType.NO_VEND, result);
        assertEquals(changeBefore, delegate.getAvailableChangeValue(machine));
    }

    @Test
    public void shouldVendFailNoStockAfterRepeats() {
        Set<AvailableItem> itemList = new HashSet<>();
        itemList.add(new AvailableItem(EnumItemType.A, 6, EnumItemType.A.getDefaultCost()));
        itemList.add(new AvailableItem(EnumItemType.B, 3, EnumItemType.B.getDefaultCost()));
        itemList.add(new AvailableItem(EnumItemType.C, 2, EnumItemType.C.getDefaultCost()));

        List<Coin> change = new ArrayList<>();
        loadDefaultChange(change);

        List<Coin> insertedList = new ArrayList<>();
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));

        VendingMachine machine = new VendingMachine();
        machine.setStock(itemList);
        machine.setAvailableChange(change);
        machine.setInsertedMoney(insertedList);

        delegate.turnOn(machine);
        EnumItemType result = delegate.vend(machine, EnumItemType.B);
        assertNotNull(result);
        assertEquals(EnumItemType.B, result);

        insertedList = new ArrayList<>();
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        machine.setInsertedMoney(insertedList);
        result = delegate.vend(machine, EnumItemType.B);
        assertNotNull(result);
        assertEquals(EnumItemType.B, result);

        insertedList = new ArrayList<>();
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        machine.setInsertedMoney(insertedList);
        result = delegate.vend(machine, EnumItemType.B);
        assertNotNull(result);
        assertEquals(EnumItemType.B, result);

        // this one should be out of stock
        insertedList = new ArrayList<>();
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        machine.setInsertedMoney(insertedList);

        Integer changeBefore = delegate.getAvailableChangeValue(machine);
        result = delegate.vend(machine, EnumItemType.B);
        assertNotNull(result);
        assertEquals(EnumItemType.NO_VEND, result);
        assertEquals(changeBefore, delegate.getAvailableChangeValue(machine));
    }

    private void loadDefaultChange(List<Coin> availableChange) {
        availableChange.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        availableChange.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        availableChange.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        availableChange.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        availableChange.add(new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED));
    }
}
