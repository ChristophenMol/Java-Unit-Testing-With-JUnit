package com.drive;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;


public class DriversManagerTest {

    private final DriversManager driversManager = new DriversManager();

    @Before
    public void setup(){
        driversManager.addPassenger( new Passenger( "Carlos", "44234", 100 ) );
        driversManager.addPassenger( new Passenger( "Elise", "533434", 100 ) );
        driversManager.addPassenger( new Passenger( "Ian", "5343433", 100 ) );
        driversManager.addPassenger( new Passenger( "Debbie", "44555521", 100 ) );
        driversManager.addPassenger( new Passenger( "Cleon", "559988", 100 ) );
        driversManager.addPassenger( new Passenger( "Santiago", "1203123", 100 ) );

        driversManager.addDriver( new Driver( "Emilio", "1234990", 10f ) );
        driversManager.addDriver( new Driver( "Pedro", "12312440", 12f ) );
        driversManager.addDriver( new Driver( "Constanza", "9824990", 11f ) );

    }

//Part 1
    @Test 
    public void passengerAddedTest(){ //Verify passenger is added correctly
        assertNotNull(driversManager.getPassenger("44234"));
    }

    @Test
    public void driverAddedTest(){ //Verify driver is added correctly

        assertNotNull(driversManager.getDriver("1234990"));
    }


    @Test
    public void startTripTest(){
        Driver driver = driversManager.getDriver("1234990"); //Get Emilio Drivers object from hash map by key
        Passenger passenger = driversManager.getPassenger("44234"); //Get Carlos passenger object from hash map by key

        driversManager.startTrip(passenger.getId(), driver.getId()); //start a trip with Emilio and Carlos

        assertFalse(driver.isAvailable()); //If our driver is on a trip they should not be available.
        assertTrue(passenger.isOnTrip()); //If our passenger is on a trip they should be on a trip :)
    }

    @Test
    public void endTripTest(){
        Driver driver = driversManager.getDriver("12312440"); //Get Pedro Drivers object from hash map by key
        Passenger passenger = driversManager.getPassenger("533434"); //Get Elise passenger object from hash map by key

        driversManager.startTrip(passenger.getId(), driver.getId()); //start a trip with Pedro and Elise so that we will have a trip running to end.

        driversManager.endTrip(passenger.getId(),driver.getId()); //End the trip

        assertTrue(driver.isAvailable());
        assertFalse(passenger.isOnTrip());
    }

    @Test
    public void nextAvailableDriverTest(){

        // assertNotNull(driversManager.getDriver(driversManager.findNextAvailableDriver())); //Will attempt to get the next available driver from our hash map if the ID isn't there the default null will be returned

    }

//Part 3
    @Test
    public void driverBalanceAdjusted(){ //Edge case 1 to be tested

        Driver driver = driversManager.getDriver("12312440"); //Get Pedro Drivers object from hash map by key
        Passenger passenger = driversManager.getPassenger("533434"); //Get Elise passenger object from hash map by key

        driversManager.startTrip(passenger.getId(), driver.getId()); //start a trip with Pedro and Elise so that we will have a trip running to end.

        driversManager.endTrip(passenger.getId(),driver.getId()); //End the trip

        assertThat(driver.getBalance(), is(equalTo(12d))); //Pedro balance should start at 0 and his fee should be added to balance after this ride.

    }

    @Test 
    public void passengerBalanceAdjusted (){ //Edge case 2 to be tested

        Driver driver = driversManager.getDriver("12312440"); //Get Pedro Drivers object from hash map by key
        Passenger passenger = driversManager.getPassenger("533434"); //Get Elise passenger object from hash map by key

        driversManager.startTrip(passenger.getId(), driver.getId()); //start a trip with Pedro and Elise so that we will have a trip running to end.

        driversManager.endTrip(passenger.getId(),driver.getId()); //End the trip

        assertThat(passenger.getBalance(), is(equalTo(100D-12D)));
    }   
}