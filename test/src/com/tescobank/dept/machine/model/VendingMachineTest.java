package com.tescobank.dept.machine.model;

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
 * Unit tests for {@link VendingMachine}
 *
 * @author Alan Humphris
 */
public class VendingMachineTest {

    private VendingMachine vendingMachine = null;

    @Before
    public void setUp() throws Exception {
        vendingMachine = new VendingMachine();
    }

    @After
    public void tearDown() throws Exception {
        vendingMachine = null;
    }

    @Test
    public void defaultStateIsOff() {
        assertFalse(vendingMachine.isOn());
    }

    @Test
    public void isOnTrue() {
        vendingMachine.setOn(true);
        assertTrue(vendingMachine.isOn());
        assertFalse(vendingMachine.isOff());
    }

    @Test
    public void isOnFalse() {
        vendingMachine.setOn(false);
        assertFalse(vendingMachine.isOn());
        assertTrue(vendingMachine.isOff());
    }

    @Test
    public void testInsertedMoney() {

        Collection<Coin> list = new ArrayList<Coin>();
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED));

        vendingMachine.setInsertedMoney(list);

        assertNotNull(vendingMachine.getInsertedMoney());
        assertEquals(5, vendingMachine.getInsertedMoney().size());

        int i = 0;
        for (Coin coin : vendingMachine.getInsertedMoney()) {
            if (EnumDenomination.TEN.equals(coin.getValue())) {
                i++;
            }
        }
        assertEquals(2, i);

    }

    @Test
    public void insertedMoneyNulled() {
        vendingMachine.setInsertedMoney(null);

        assertNotNull(vendingMachine.getInsertedMoney());
        assertEquals(0, vendingMachine.getInsertedMoney().size());
    }

    @Test
    public void addInsertedMoney() {
        vendingMachine.addInsertedMoney(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        assertEquals(1, vendingMachine.getInsertedMoney().size());
    }

    @Test
    public void clearAllInsertedMoney() {
        Collection<Coin> list = new ArrayList<Coin>();
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED));

        vendingMachine.setInsertedMoney(list);
        vendingMachine.clearAllInsertedMoney();

        assertNotNull(vendingMachine.getInsertedMoney());
        assertEquals(0, vendingMachine.getInsertedMoney().size());
    }

    @Test
    public void testAvailableChange() {

        Collection<Coin> list = new ArrayList<Coin>();
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED));

        vendingMachine.setAvailableChange(list);

        assertNotNull(vendingMachine.getAvailableChange());
        assertEquals(5, vendingMachine.getAvailableChange().size());

        int i = 0;
        for (Coin coin : vendingMachine.getAvailableChange()) {
            if (EnumDenomination.TEN.equals(coin.getValue())) {
                i++;
            }
        }
        assertEquals(2, i);

    }

    @Test
    public void availableChangeNulled() {
        vendingMachine.setAvailableChange(null);

        assertNotNull(vendingMachine.getAvailableChange());
        assertEquals(0, vendingMachine.getAvailableChange().size());
    }

    @Test
    public void addAvailableChangeMoney() {
        vendingMachine.addAvailableChange(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        assertEquals(1, vendingMachine.getAvailableChange().size());
    }

    @Test
    public void clearAllAvailableChange() {
        Collection<Coin> list = new ArrayList<Coin>();
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED));

        vendingMachine.setAvailableChange(list);
        vendingMachine.clearAllAvailableChange();

        assertNotNull(vendingMachine.getAvailableChange());
        assertEquals(0, vendingMachine.getAvailableChange().size());
    }

    @Test
    public void testStock() {
        Set<AvailableItem> list = new HashSet<AvailableItem>();
        list.add(new AvailableItem(EnumItemType.A, 6, EnumItemType.A.getDefaultCost()));
        list.add(new AvailableItem(EnumItemType.A, 5, 1));
        list.add(new AvailableItem(EnumItemType.B, 3, EnumItemType.B.getDefaultCost()));
        list.add(new AvailableItem(EnumItemType.C, 2, EnumItemType.C.getDefaultCost()));

        vendingMachine.setStock(list);

        assertNotNull(vendingMachine.getStock());
        assertEquals(3, vendingMachine.getStock().size());

        AvailableItem foundOne = null;
        for (AvailableItem availableItem : vendingMachine.getStock()) {
            if (EnumItemType.A.equals(availableItem.getItemType())) {
                foundOne = availableItem;
            }
        }

        assertNotNull(foundOne);
        assertEquals((Integer) 60, foundOne.getCost());
        assertEquals((Integer) 6, foundOne.getLevel());

    }

    @Test
    public void stockNulled() {
        vendingMachine.setStock(null);

        assertNotNull(vendingMachine.getStock());
        assertEquals(0, vendingMachine.getStock().size());
    }

    @Test
    public void clearAllStock() {
        Set<AvailableItem> list = new HashSet<AvailableItem>();
        list.add(new AvailableItem(EnumItemType.A, 6, EnumItemType.A.getDefaultCost()));
        list.add(new AvailableItem(EnumItemType.B, 3, EnumItemType.B.getDefaultCost()));
        list.add(new AvailableItem(EnumItemType.C, 2, EnumItemType.C.getDefaultCost()));

        vendingMachine.setStock(list);

        vendingMachine.clearAllStock();

        assertNotNull(vendingMachine.getStock());
        assertEquals(0, vendingMachine.getStock().size());

    }

}
