/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyname.dept.machine.delegate;

import com.companyname.dept.machine.model.Coin;
import java.util.ArrayList;

import java.util.List;

import java.util.Map;

/**
 *
 * @author Alan Humphris
 */
interface ChangeMachineHelper {

    static void addChangeToGive(Map<Integer, List<List<Coin>>> changeToGive, ArrayList<Coin> sol, int partialAmount) {

        int amount = sol.stream().mapToInt(coin -> coin.getDenomination().getValue()).sum() + partialAmount;

        List<List<Coin>> existingSolutions = changeToGive.get(amount);

        if ((existingSolutions != null) && !existingSolutions.contains(sol)) {

            existingSolutions.add(sol);

            changeToGive.put(amount, existingSolutions);
        }
    }

    static void change(Map<Integer, List<List<Coin>>> changeToGive, int originalAmount, int amount, List<Coin> coins, List<Coin> partialSolution) {

        // Check preconditions
        if (coins.isEmpty()) {
            return;
        }

        if (amount == 0) {
            if (originalAmount == amount) {
                changeToGive.put(amount, new ArrayList<>());
            }
            return;
        }

        if (amount > coins.stream().mapToInt(coin -> coin.getDenomination().getValue()).sum()) {
            if (originalAmount == amount) {
                changeToGive.put(amount, new ArrayList<>());
            }
            return;
        }

        if (amount < coins.stream().min(Coin::compareTo).get().getDenomination().getValue()) {
            if (originalAmount == amount) {
                changeToGive.put(amount, new ArrayList<>());
            }
            return;
        }

        List<List<Coin>> existingSolutions = changeToGive.get(amount);

        // initialize solutions array if null
        if (existingSolutions == null && partialSolution.isEmpty()) {

            existingSolutions = new ArrayList<>();

            changeToGive.put(amount, existingSolutions);
        }

        // try to build a solution adding each of the coins to the partial
        // solution
        for (Coin coin : coins) {
            ArrayList<Coin> currentSolution = new ArrayList<>(partialSolution);

            currentSolution.add(coin);

            currentSolution.sort(Coin::compareTo);

            // Build a solution using the each of the coins
            ArrayList<Coin> coinsSublist = new ArrayList<>(coins);

            coinsSublist.remove(coin);

            ArrayList<Coin> partialSolutionAugmented = new ArrayList<>(partialSolution);

            if (amount == coin.getDenomination().getValue()) {

                // this is the last coin for the solution
                // add this solution to top level
                addChangeToGive(changeToGive, currentSolution, 0);

                continue;
            }

            if (amount > coin.getDenomination().getValue()) {

                // use the coin for the solution
                partialSolutionAugmented.add(coin);

                change(changeToGive, originalAmount, amount - coin.getDenomination().getValue(), coinsSublist, partialSolutionAugmented);

                continue;
            }

            if (amount < coin.getDenomination().getValue()) {

                // do not use this coin for the solution
                change(changeToGive, originalAmount, amount, coinsSublist, partialSolution);

                continue;
            }

        }

    }

}
