/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tescobank.dept.machine.delegate;

import com.tescobank.dept.machine.model.AvailableItem;
import com.tescobank.dept.machine.model.Coin;
import com.tescobank.dept.machine.model.EnumCurrency;
import com.tescobank.dept.machine.model.EnumDenomination;
import com.tescobank.dept.machine.model.EnumItemType;
import com.tescobank.dept.machine.model.VendingMachine;
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
    public void loadAvailableChange() {
        Collection<Coin> list = new ArrayList<Coin>();
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED));

        VendingMachine machine = new VendingMachine();

        delegate.loadAvailableChange(machine, list);

        assertNotNull(delegate.getAvailableChange(machine));
        assertEquals(5, delegate.getAvailableChange(machine).size());

        int i = 0;
        for (Coin coin : delegate.getAvailableChange(machine)) {
            if (EnumDenomination.TEN.equals(coin.getValue())) {
                i++;
            }
        }
        assertEquals(2, i);
    }

    @Test
    public void loadStock() {
        Set<AvailableItem> list = new HashSet<AvailableItem>();
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
    public void insertCoin() {
        VendingMachine machine = new VendingMachine();
        boolean result = delegate.insertCoin(machine, new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED));

        assertTrue(result);
        assertEquals(1, machine.getInsertedMoney().size());
    }

    @Test
    public void returnCoins() {
        Collection<Coin> list = new ArrayList<Coin>();
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED));

        VendingMachine machine = new VendingMachine();
        machine.setInsertedMoney(list);

        Collection<Coin> result = delegate.returnCoins(machine);

        assertNotNull(result);
        assertEquals(5, result.size());

        int i = 0;
        for (Coin coin : result) {
            if (EnumDenomination.TEN.equals(coin.getValue())) {
                i++;
            }
        }
        assertEquals(2, i);

        assertNotNull(machine.getInsertedMoney());
        assertEquals(0, machine.getInsertedMoney().size());
    }

    @Test
    public void turnOn() {
        VendingMachine machine = new VendingMachine();
        machine.setOn(false);
        delegate.turnOn(machine);
        assertTrue(machine.isOn());
    }

    @Test
    public void turnOff() {
        VendingMachine machine = new VendingMachine();
        machine.setOn(true);
        delegate.turnOff(machine);
        assertFalse(machine.isOn());
    }

    @Test
    public void isOnTrue() {
        VendingMachine machine = new VendingMachine();
        machine.setOn(false);
        delegate.turnOn(machine);
        assertTrue(delegate.isOn(machine));
    }

    @Test
    public void isOnFalse() {
        VendingMachine machine = new VendingMachine();
        machine.setOn(true);
        delegate.turnOff(machine);
        assertFalse(delegate.isOn(machine));
    }

    @Test
    public void getAvailableChange() {
        Collection<Coin> list = new ArrayList<Coin>();
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED));

        VendingMachine machine = new VendingMachine();
        machine.setAvailableChange(list);

        Collection<Coin> result = delegate.getAvailableChange(machine);

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
    public void getCurrentlyInsertedCoinValue() {
        Collection<Coin> list = new ArrayList<Coin>();
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED));

        VendingMachine machine = new VendingMachine();
        machine.setInsertedMoney(list);

        Integer result = delegate.getCurrentlyInsertedCoinValue(machine);

        assertEquals((Integer) 190, result);
    }

    @Test
    public void getAvailableChangeValue() {
        Collection<Coin> list = new ArrayList<Coin>();
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED));

        VendingMachine machine = new VendingMachine();
        machine.setAvailableChange(list);

        Integer result = delegate.getAvailableChangeValue(machine);

        assertEquals((Integer) 190, result);
    }

    // *** protected methods ***
    @Test
    public void removeAvailableChange() {
        Collection<Coin> list = new ArrayList<Coin>();
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED));

        VendingMachine machine = new VendingMachine();
        machine.setAvailableChange(list);

        delegate.removeAvailableChange(machine, new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));

        assertNotNull(machine.getAvailableChange());
        assertEquals(4, machine.getAvailableChange().size());
        assertFalse(machine.getAvailableChange().contains(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY)));
    }

    @Test
    public void removeAvailableChangeCollection() {
        Collection<Coin> list = new ArrayList<Coin>();
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED));

        VendingMachine machine = new VendingMachine();
        machine.setAvailableChange(list);

        Collection<Coin> removeList = new ArrayList<Coin>();
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
    public void getStockLevel() {
        Set<AvailableItem> list = new HashSet<AvailableItem>();
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
    public void addStockLevel() {
        Set<AvailableItem> list = new HashSet<AvailableItem>();
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
    public void removeStockLevel() {
        Set<AvailableItem> list = new HashSet<AvailableItem>();
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
    public void findAvailableItemForType() {
        Set<AvailableItem> list = new HashSet<AvailableItem>();
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
    public void vendSuccessExactMoney1() {
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

        Collection<Coin> insertedList = new ArrayList<Coin>();
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));

        VendingMachine machine = new VendingMachine();
        machine.setStock(itemList);
        machine.setAvailableChange(changeList);
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
    public void vendSuccessExactMoney2() {
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

        Collection<Coin> insertedList = new ArrayList<Coin>();
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));

        VendingMachine machine = new VendingMachine();
        machine.setStock(itemList);
        machine.setAvailableChange(changeList);
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
    public void vendSuccessTooMuchMoney() {
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

        Collection<Coin> insertedList = new ArrayList<Coin>();
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));

        VendingMachine machine = new VendingMachine();
        machine.setStock(itemList);
        machine.setAvailableChange(changeList);
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
    public void vendFailTooLittleMoney() {
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

        Collection<Coin> insertedList = new ArrayList<Coin>();
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));

        VendingMachine machine = new VendingMachine();
        machine.setStock(itemList);
        machine.setAvailableChange(changeList);
        machine.setInsertedMoney(insertedList);

        Integer changeBefore = delegate.getAvailableChangeValue(machine);
        delegate.turnOn(machine);
        EnumItemType result = delegate.vend(machine, EnumItemType.A);
        assertNotNull(result);
        assertEquals(EnumItemType.NO_VEND, result);
        assertEquals(changeBefore, delegate.getAvailableChangeValue(machine));
    }

    @Test
    public void vendFailNoStock() {
        Set<AvailableItem> itemList = new HashSet<AvailableItem>();
        itemList.add(new AvailableItem(EnumItemType.A, 0, EnumItemType.A.getDefaultCost()));
        itemList.add(new AvailableItem(EnumItemType.B, 3, EnumItemType.B.getDefaultCost()));
        itemList.add(new AvailableItem(EnumItemType.C, 2, EnumItemType.C.getDefaultCost()));

        Collection<Coin> changeList = new ArrayList<Coin>();
        changeList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        changeList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        changeList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        changeList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        changeList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED));

        Collection<Coin> insertedList = new ArrayList<Coin>();
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));

        VendingMachine machine = new VendingMachine();
        machine.setStock(itemList);
        machine.setAvailableChange(changeList);
        machine.setInsertedMoney(insertedList);

        Integer changeBefore = delegate.getAvailableChangeValue(machine);
        delegate.turnOn(machine);
        EnumItemType result = delegate.vend(machine, EnumItemType.A);
        assertNotNull(result);
        assertEquals(EnumItemType.NO_VEND, result);
        assertEquals(changeBefore, delegate.getAvailableChangeValue(machine));
    }

    @Test
    public void vendFailNoStockAfterRepeats() {
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

        Collection<Coin> insertedList = new ArrayList<Coin>();
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));

        VendingMachine machine = new VendingMachine();
        machine.setStock(itemList);
        machine.setAvailableChange(changeList);
        machine.setInsertedMoney(insertedList);

        delegate.turnOn(machine);
        EnumItemType result = delegate.vend(machine, EnumItemType.B);
        assertNotNull(result);
        assertEquals(EnumItemType.B, result);

        insertedList = new ArrayList<Coin>();
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        machine.setInsertedMoney(insertedList);
        result = delegate.vend(machine, EnumItemType.B);
        assertNotNull(result);
        assertEquals(EnumItemType.B, result);

        insertedList = new ArrayList<Coin>();
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        machine.setInsertedMoney(insertedList);
        result = delegate.vend(machine, EnumItemType.B);
        assertNotNull(result);
        assertEquals(EnumItemType.B, result);

        // this one should be out of stock
        insertedList = new ArrayList<Coin>();
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        insertedList.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        machine.setInsertedMoney(insertedList);

        Integer changeBefore = delegate.getAvailableChangeValue(machine);
        result = delegate.vend(machine, EnumItemType.B);
        assertNotNull(result);
        assertEquals(EnumItemType.NO_VEND, result);
        assertEquals(changeBefore, delegate.getAvailableChangeValue(machine));
    }

}
