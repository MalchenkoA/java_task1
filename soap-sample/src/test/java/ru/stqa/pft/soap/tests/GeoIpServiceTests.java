package ru.stqa.pft.soap.tests;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("2a02:2168:8fd4:9c00:bd59:f85f:e075:53b6");
        assertEquals(ipLocation, "<GeoIP><Country>HK</Country><State>00</State></GeoIP>");
    }
}
