/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tescobank.dept.machine.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Alan Humphris
 */
public class AvailableItemTest {

    @Test
    public void testItemType() {
        AvailableItem item = new AvailableItem(EnumItemType.A, 0, 0);
        assertEquals(EnumItemType.A, item.getItemType());
    }
    
    @Test
    public void testItemCount() {
        AvailableItem item = new AvailableItem(EnumItemType.A, 5, 0);
        assertEquals((Integer)5, item.getLevel());
    }

    @Test
    public void testItemCost() {
        AvailableItem item = new AvailableItem(EnumItemType.A, 0, 170);
        assertEquals((Integer)170, item.getCost());
    }
}
