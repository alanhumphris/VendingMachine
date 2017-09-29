package com.companyname.dept.machine.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Unit tests for {@link Coin}
 *
 * @author Alan Humphris
 */
public class CoinTest {

    @Test
    public void shouldGetValue() {
        Coin coin = new Coin(EnumCurrency.STERLING, EnumDenomination.TEN);
        assertEquals(EnumDenomination.TEN, coin.getDenomination());
        assertEquals((Integer) 10, coin.getDenomination().getValue());
    }

    @Test
    public void shouldGetCurrency() {
        Coin coin = new Coin(EnumCurrency.STERLING, EnumDenomination.TEN);
        assertEquals(EnumCurrency.STERLING, coin.getCurrency());
    }

    @Test
    public void testIsEqualSuccess() {
        Coin coin1 = new Coin(EnumCurrency.STERLING, EnumDenomination.TEN);
        Coin coin2 = new Coin(EnumCurrency.STERLING, EnumDenomination.TEN);

        assertTrue(coin1.isEqual(coin2));
        assertTrue(coin2.isEqual(coin1));
    }

    @Test
    public void shouldIsEqualFail() {
        Coin coin1 = new Coin(EnumCurrency.STERLING, EnumDenomination.TEN);
        Coin coin2 = new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY);

        assertFalse(coin1.isEqual(coin2));
        assertFalse(coin2.isEqual(coin1));
    }

    @Test
    public void shouldIsEqualAllNulls() {
        Coin coin1 = new Coin(null, null);
        Coin coin2 = new Coin(null, null);

        assertTrue(coin1.isEqual(coin2));
        assertTrue(coin2.isEqual(coin1));
    }

    @Test
    public void shouldIsEqualNulls() {
        Coin coin1 = new Coin(EnumCurrency.STERLING, EnumDenomination.TEN);
        Coin coin2 = new Coin(null, null);

        assertFalse(coin1.isEqual(coin2));
        assertFalse(coin2.isEqual(coin1));
    }
}
