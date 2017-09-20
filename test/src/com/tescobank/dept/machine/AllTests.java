/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tescobank.dept.machine;

import com.tescobank.dept.machine.delegate.VendingMachineDelegateTest;
import com.tescobank.dept.machine.model.CoinTest;
import com.tescobank.dept.machine.model.AvailableItemTest;
import com.tescobank.dept.machine.model.VendingMachineTest;
import com.tescobank.dept.machine.service.VendingMachineServiceTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Alan Humphris
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    CoinTest.class,
    AvailableItemTest.class,
    VendingMachineTest.class,
    VendingMachineDelegateTest.class,
    VendingMachineServiceTest.class
})
public class AllTests {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}
