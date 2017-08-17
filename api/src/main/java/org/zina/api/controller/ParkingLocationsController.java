package org.zina.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zina.model.ParkingLocation;
import org.zina.services.ParkingLocationsService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class ParkingLocationsController {

    @Autowired
    private ParkingLocationsService parkingLocationsService;

    @ResponseBody
    @RequestMapping(value="parkinglocations", method = GET)
    public List<ParkingLocation> queryParkingByLocation(
            @RequestParam("latitude") Double latitude,
            @RequestParam("longitude") Double longitude) {
        return parkingLocationsService.queryParkingByLocation(latitude, longitude);
    }
}
