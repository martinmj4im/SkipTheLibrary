package com.stl.skipthelibrary;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {
    private Location location;
    private double testLat;
    private double testLon;

    @Before
    public void setup(){
        testLat = 53.525151;
        testLon = -113.527350;
        location = new Location(testLat, testLon);
    }

    @Test
    public void testConstructor() {
        assertEquals(testLat, location.getLatitude(), 0);
        assertEquals(testLon, location.getLongitude(), 0);
    }

    @Test
    public void testSetters() {

        location.setLatitude(69.420);
        assertEquals(69.420, location.getLatitude(), 0);
        location.setLongitude(-69.420);
        assertEquals(-69.420, location.getLongitude(),0);
    }
}