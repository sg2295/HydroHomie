/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sgavr
 */
public class UserDataTest {
    
    public UserDataTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addRecord method, of class UserData.
     */
    @Test
    public void testAddRecord() {
        System.out.println("addRecord");
        float fluidVolume = 330.0F;
        Date date = new Date();
        String type = "WATER";        
        UserData instance = new UserData();
        instance.addRecord(fluidVolume, date, type);
        if (instance.records.size() == 1){
            return;
        }
        fail("Didn't add record to instance.");
    }
    
    /**
     * Test of addGoal method, of class UserData.
     */
    @Test
    public void testAddGoal() {
        System.out.println("addGoal");
        Float volume = 330.0f;
        GoalType type = GoalType.DAILY;
        UserData instance = new UserData();
        instance.addGoal(volume, type);
        if (instance.dailyGoals.size() == 1){
            return;
        }
        fail("Goal not added successfully.");
    }

    /**
     * Test of getCurrentDailyGoal method, of class UserData.
     */
    @Test
    public void testGetCurrentDailyGoal() {
        System.out.println("getCurrentDailyGoal");
        UserData instance = new UserData();
        Date date = new Date();
        instance.dailyGoals.put(instance.stripToMidnight(date), new Goal(330.f, GoalType.DAILY, 10));
        Goal expResult = instance.dailyGoals.get(instance.stripToMidnight(date));
        Goal result = instance.getCurrentDailyGoal();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRecord method, of class UserData.
     */
    @Test
    public void testGetRecord() {
        System.out.println("getRecord");
        Date date = new Date();
        LiquidType type = LiquidType.WATER;
        Float vol = 330f;
        UserData instance = new UserData();
        Record record = new Record(vol, date, type);
        instance.records.add(record);
        Record expResult = record;
        Record result = instance.getRecord(date, type, vol);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteRecord method, of class UserData.
     */
    @Test
    public void testDeleteRecord() {
        System.out.println("deleteRecord");
        Float vol = 330f;
        Date date = new Date();
        LiquidType type = LiquidType.WATER;
        UserData instance = new UserData();
        instance.addRecord(vol, date, "WATER");
        instance.deleteRecord(instance.getRecord(date, type, vol));
        if (instance.records.isEmpty()){
            return;
        }
        fail("Didn't delete record");
    }

    /**
     * Test of WriteFile method, of class UserData.
     */
    @Test
    public void testWriteFile() {
        System.out.println("WriteFile");
        UserData instance = new UserData();
        instance.WriteFile();
    }

    /**
     * Test of addPoints method, of class UserData.
     */
    @Test
    public void testAddPoints() {
        System.out.println("addPoints");
        int amount = 10;
        UserData instance = new UserData();
        int expResult = amount;
        int result = instance.addPoints(amount);
        assertEquals(expResult, result);
    }

    /**
     * Test of setPoints method, of class UserData.
     */
    @Test
    public void testSetPoints() {
        System.out.println("setPoints");
        int amount = 10;
        UserData instance = new UserData();
        int expResult = amount;
        int result = instance.setPoints(amount);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPoints method, of class UserData.
     */
    @Test
    public void testGetPoints() {
        System.out.println("getPoints");
        UserData instance = new UserData();
        int expResult = instance.setPoints(10);
        int result = instance.getPoints();
        assertEquals(expResult, result);
    }

    /**
     * Test of FromFile method, of class UserData.
     */
    @Test
    public void testFromFile() {
        System.out.println("FromFile");
        String filepath = "data/data.txt";
        UserData result = UserData.FromFile(filepath);
        if (result != null){
            return;
        }
        // TODO review the generated test code and remove the default call to fail.
        fail("FromFile returned null");
    }

    /**
     * Test of doesDailyGoalExist method, of class UserData.
     */
    @Test
    public void testDoesDailyGoalExist() {
        System.out.println("doesDailyGoalExist");
        Date date = new Date();
        UserData instance = new UserData();
        instance.addGoal(330F, GoalType.DAILY);
        boolean expResult = true;
        boolean result = instance.doesDailyGoalExist(date);
        assertEquals(expResult, result);
    }

    /**
     * Test of stripToMidnight method, of class UserData.
     */
    @Test
    public void testStripToMidnight() {
        System.out.println("stripToMidnight");
        Date date = new Date();
        UserData instance = new UserData();
        Date result = instance.stripToMidnight(date);
        if (result.compareTo(date)<2){
            return;
        }  
        // TODO review the generated test code and remove the default call to fail.
        fail("Strip to midnight didn't work");
    }

    /**
     * Test of getTotalGoals method, of class UserData.
     */
    @Test
    public void testGetTotalGoals() {
        System.out.println("getTotalGoals");
        UserData instance = new UserData();
        instance.addGoal(330f, GoalType.DAILY);
        instance.addGoal(330f, GoalType.WEEKLY);

        int expResult = 2;
        int result = instance.getTotalGoals();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDailyGoalsMet method, of class UserData.
     */
    @Test
    public void testGetDailyGoalsMet() {
        System.out.println("getDailyGoalsMet");
        UserData instance = new UserData();
        int expResult = 0;
        int result = instance.getDailyGoalsMet();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWeeklyGoalsMet method, of class UserData.
     */
    @Test
    public void testGetWeeklyGoalsMet() {
        System.out.println("getWeeklyGoalsMet");
        UserData instance = new UserData();
        int expResult = 0;
        int result = instance.getWeeklyGoalsMet();
        assertEquals(expResult, result);
    }

    /**
     * Test of percentageOfGoalsCompleted method, of class UserData.
     */
    @Test
    public void testPercentageOfGoalsCompleted() {
        System.out.println("percentageOfGoalsCompleted");
        UserData instance = new UserData();
        instance.addGoal(330f, GoalType.DAILY);
        float expResult = 0f;
        float result = instance.percentageOfGoalsCompleted();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFavouriteDrink method, of class UserData.
     */
    @Test
    public void testGetFavouriteDrink() {
        System.out.println("getFavouriteDrink");
        UserData instance = new UserData();
        instance.addRecord(330f, new Date(), "Milk");
        String expResult = "Milk";
        String result = instance.getFavouriteDrink();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAverageDailyVolume method, of class UserData.
     */
    @Test
    public void testGetAverageDailyVolume() {
        System.out.println("getAverageDailyVolume");
        UserData instance = new UserData();
        instance.addRecord(330f, new Date(), "Water");
        int expResult = 330;
        int result = instance.getAverageDailyVolume();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDailyVolume method, of class UserData.
     */
    @Test
    public void testGetDailyVolume() {
        System.out.println("getDailyVolume");
        UserData instance = new UserData();
        instance.addRecord(10f, new Date(), "Water");        
        instance.addRecord(10f, new Date(), "Water");
        // this one needs doing
        HashMap<Date, Float> hashMap = new HashMap<>();
        hashMap.put(instance.stripToMidnight(new Date()), 20f);
        HashMap<Date, Float> expResult = hashMap;
        HashMap<Date, Float> result = instance.getDailyVolume();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVolumeOn method, of class UserData.
     */
    @Test
    public void testGetVolumeOn() {
        System.out.println("getVolumeOn");
        Date date = new Date();
        UserData instance = new UserData();
        instance.addRecord(330f, new Date(), "Water");
        float expResult = 330f;
        float result = instance.getVolumeOn(date);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getVolumeOverDays method, of class UserData.
     */
    @Test
    public void testGetVolumeOverDays() {
        System.out.println("getVolumeOverDays");
        int days = 0;
        UserData instance = new UserData();
        float expResult = 0.0F;
        float result = instance.getVolumeOverDays(days);
        assertEquals(expResult, result, 0.0F);
    }

    /**
     * Test of calculateProgress method, of class UserData.
     */
    @Test
    public void testCalculateProgress() {
        System.out.println("calculateProgress");
        Goal goal = new Goal(240.0f, GoalType.DAILY,  10);
        int days = 0;
        UserData instance = new UserData();
        int expResult = 0;
        int result = instance.calculateProgress(goal, days);
        assertEquals(expResult, result);
    }
}
