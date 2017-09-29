/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyname.dept.machine.delegate;

import com.companyname.dept.machine.model.Coin;
import com.companyname.dept.machine.model.EnumCurrency;
import com.companyname.dept.machine.model.EnumDenomination;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Alan Humphris
 */
public class ChangeMachineHelperTest {

    /**
     * solution will contain for each amount a list of possible change
     * combinations. each change combination will be counted once
     */
    private Map<Integer, List<List<Coin>>> changeToGive = null;

    private boolean DEBUG = false;

    @Before
    public void setUp() throws Exception {
        changeToGive = new HashMap<>();
    }

    @After
    public void tearDown() throws Exception {
        if (DEBUG) {
            printSolution(changeToGive);
        }
        changeToGive = null;
    }

    @Test
    public void shouldHandleAllChange() {
        // given
        List<Coin> partialSolution = new ArrayList<>();
        List<Coin> availableChange = new ArrayList<>();
        loadDefaultAvailableChange(availableChange);
        int amount = getTotal(availableChange);

        // when 
        ChangeMachineHelper.change(changeToGive, amount, amount, availableChange, partialSolution);

        // then
        assertEquals(1, changeToGive.get(amount).size());
    }

    @Test
    public void shouldHandleZero() {
        // given
        int amount = 0;
        List<Coin> partialSolution = new ArrayList<>();
        List<Coin> availableChange = new ArrayList<>();
        loadDefaultAvailableChange(availableChange);

        // when 
        ChangeMachineHelper.change(changeToGive, amount, amount, availableChange, partialSolution);

        // then
        assertEquals(0, changeToGive.get(amount).size());
    }

    @Test
    public void shouldHandleBelowMinimum() {
        // given
        int amount = 1;
        List<Coin> partialSolution = new ArrayList<>();
        List<Coin> availableChange = new ArrayList<>();
        loadDefaultAvailableChange(availableChange);

        // when 
        ChangeMachineHelper.change(changeToGive, amount, amount, availableChange, partialSolution);

        // then
        assertEquals(0, changeToGive.get(amount).size());
    }

    @Test
    public void shouldBeTen() {
        // given
        int amount = 10;
        List<Coin> partialSolution = new ArrayList<>();
        List<Coin> availableChange = new ArrayList<>();
        loadDefaultAvailableChange(availableChange);

        // when 
        ChangeMachineHelper.change(changeToGive, amount, amount, availableChange, partialSolution);

        // then
        assertEquals(1, changeToGive.get(amount).size());
    }

    @Test
    public void shouldNotBeFiften() {
        // given
        int amount = 15;
        List<Coin> partialSolution = new ArrayList<>();
        List<Coin> availableChange = new ArrayList<>();
        loadDefaultAvailableChange(availableChange);

        // when 
        ChangeMachineHelper.change(changeToGive, amount, amount, availableChange, partialSolution);

        // then
        assertEquals(0, changeToGive.get(amount).size());
    }

    @Test
    public void shouldBeSeventy() {
        // given
        int amount = 70;
        List<Coin> partialSolution = new ArrayList<>();
        List<Coin> availableChange = new ArrayList<>();
        loadDefaultAvailableChange(availableChange);

        // when 
        ChangeMachineHelper.change(changeToGive, amount, amount, availableChange, partialSolution);

        // then
        assertEquals(1, changeToGive.get(amount).size());
    }

    @Test
    public void shouldBeEighty() {
        // given
        int amount = 80;
        List<Coin> partialSolution = new ArrayList<>();
        List<Coin> availableChange = new ArrayList<>();
        loadDefaultAvailableChange(availableChange);

        // when 
        ChangeMachineHelper.change(changeToGive, amount, amount, availableChange, partialSolution);

        // then
        assertEquals(1, changeToGive.get(amount).size());
    }

    @Test
    public void shouldNotBeNinety() {
        // given
        int amount = 90;
        List<Coin> partialSolution = new ArrayList<>();
        List<Coin> availableChange = new ArrayList<>();
        loadDefaultAvailableChange(availableChange);

        // when 
        ChangeMachineHelper.change(changeToGive, amount, amount, availableChange, partialSolution);

        // then
        assertEquals(0, changeToGive.get(amount).size());
    }

    @Test
    public void shouldNotBeOneHundredAndForty() {
        // given
        int amount = 140;
        List<Coin> partialSolution = new ArrayList<>();
        List<Coin> availableChange = new ArrayList<>();
        loadDefaultAvailableChange(availableChange);

        // when 
        ChangeMachineHelper.change(changeToGive, amount, amount, availableChange, partialSolution);

        // then
        assertEquals(0, changeToGive.get(amount).size());
    }

    @Test
    public void shouldBeOneHundredAndSeventy() {
        // given
        int amount = 170;
        List<Coin> partialSolution = new ArrayList<>();
        List<Coin> availableChange = new ArrayList<>();
        loadDefaultAvailableChange(availableChange);

        // when 
        ChangeMachineHelper.change(changeToGive, amount, amount, availableChange, partialSolution);

        // then
        assertEquals(1, changeToGive.get(amount).size());
    }

    @Test
    public void shouldNotBeTwoHundred() {
        // given
        int amount = 200;
        List<Coin> partialSolution = new ArrayList<>();
        List<Coin> availableChange = new ArrayList<>();
        loadDefaultAvailableChange(availableChange);

        // when 
        ChangeMachineHelper.change(changeToGive, amount, amount, availableChange, partialSolution);

        // then
        assertEquals(0, changeToGive.get(amount).size());
    }

    private int getTotal(List<Coin> availableChange) {
        int result = 0;
        for (Coin coin : availableChange) {
            result = result + coin.getDenomination().getValue();
        }
        return result;
    }

    private void loadDefaultAvailableChange(List<Coin> availableChange) {
        availableChange.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        //availableChange.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TEN));
        availableChange.add(new Coin(EnumCurrency.STERLING, EnumDenomination.TWENTY));
        availableChange.add(new Coin(EnumCurrency.STERLING, EnumDenomination.FIFTY));
        availableChange.add(new Coin(EnumCurrency.STERLING, EnumDenomination.HUNDRED));
    }

    private void printSolution(Map<Integer, List<List<Coin>>> solution) {

        System.out.println("===================================");

        solution.forEach((key, value) -> {

            System.out.println("Amount " + key + ":");
            System.out.println("Possible solutions: " + value.size());

            for (int i = 0; i < value.size(); i++) {
                System.out.print((i + 1) + ". ");
                System.out.println(value.get(i) + " ");
            }

            System.out.println();
        });

        System.out.println("===================================");

    }
}
