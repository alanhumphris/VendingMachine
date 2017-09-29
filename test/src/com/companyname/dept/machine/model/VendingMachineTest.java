package com.companyname.dept.machine.model;

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
    public void shouldBeDefaultStateIsOff() {
        assertFalse(vendingMachine.isOn());
    }

    @Test
    public void shouldBeOnTrue() {
        vendingMachine.setOn(true);
        assertTrue(vendingMachine.isOn());
        assertFalse(vendingMachine.isOff());
    }

    @Test
    public void shouldBeOnFalse() {
        vendingMachine.setOn(false);
        assertFalse(vendingMachine.isOn());
        assertTrue(vendingMachine.isOff());
    }

    @Test
    public void shouldGetInsertedMoney() {

        List<Coin> list = new ArrayList<>();
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED));

        vendingMachine.setInsertedMoney(list);

        assertNotNull(vendingMachine.getInsertedMoney());
        assertEquals(5, vendingMachine.getInsertedMoney().size());

        int i = 0;
        i = vendingMachine.getInsertedMoney().stream().filter((coin) -> (EnumDenomination.TEN.equals(coin.getDenomination()))).map((_item) -> 1).reduce(i, Integer::sum);
        assertEquals(2, i);

    }

    @Test
    public void shouldGetInsertedMoneyWhenNulled() {
        vendingMachine.setInsertedMoney(null);

        assertNotNull(vendingMachine.getInsertedMoney());
        assertEquals(0, vendingMachine.getInsertedMoney().size());
    }

    @Test
    public void shouldAddInsertedMoney() {
        vendingMachine.addInsertedMoney(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        assertEquals(1, vendingMachine.getInsertedMoney().size());
    }

    @Test
    public void shouldClearAllInsertedMoney() {
        List<Coin> list = new ArrayList<>();
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
    public void shouldGetAvailableChange() {

        List<Coin> list = new ArrayList<>();
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        list.add(new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED));

        vendingMachine.setAvailableChange(list);

        assertNotNull(vendingMachine.getAvailableChange());
        assertEquals(5, vendingMachine.getAvailableChange().size());

        int i = 0;
        i = vendingMachine.getAvailableChange().stream().filter((coin) -> (EnumDenomination.TEN.equals(coin.getDenomination()))).map((_item) -> 1).reduce(i, Integer::sum);
        assertEquals(2, i);

    }

    @Test
    public void shouldGetAvailableChangeWhenNulled() {
        vendingMachine.setAvailableChange(null);

        assertNotNull(vendingMachine.getAvailableChange());
        assertEquals(0, vendingMachine.getAvailableChange().size());
    }

    @Test
    public void shouldAddAvailableChangeMoney() {
        vendingMachine.addAvailableChange(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        assertEquals(1, vendingMachine.getAvailableChange().size());
    }

    @Test
    public void shouldClearAllAvailableChange() {
        List<Coin> list = new ArrayList<>();
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
    public void shouldGetStock() {
        Set<AvailableItem> list = new HashSet<>();
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
    public void shouldGetStockWhenNulled() {
        vendingMachine.setStock(null);

        assertNotNull(vendingMachine.getStock());
        assertEquals(0, vendingMachine.getStock().size());
    }

    @Test
    public void shouldClearAllStock() {
        Set<AvailableItem> list = new HashSet<>();
        list.add(new AvailableItem(EnumItemType.A, 6, EnumItemType.A.getDefaultCost()));
        list.add(new AvailableItem(EnumItemType.B, 3, EnumItemType.B.getDefaultCost()));
        list.add(new AvailableItem(EnumItemType.C, 2, EnumItemType.C.getDefaultCost()));

        vendingMachine.setStock(list);

        vendingMachine.clearAllStock();

        assertNotNull(vendingMachine.getStock());
        assertEquals(0, vendingMachine.getStock().size());

    }

}
