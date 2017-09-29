/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.companyname.dept.machine;

import com.companyname.dept.machine.model.CoinTest;
import com.companyname.dept.machine.model.AvailableItemTest;
import com.companyname.dept.machine.model.VendingMachineTest;
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
    VendingMachineTest.class
})
public class AllModelTests {

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
