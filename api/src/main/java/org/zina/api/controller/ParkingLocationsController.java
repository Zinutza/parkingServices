package org.zina.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zina.model.ParkingLocation;
import org.zina.services.ParkingLocationsService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    @ResponseBody
    @RequestMapping(value = "parkinglocations", method = POST)
    public ResponseEntity create(@RequestBody ParkingLocation parkingLocation) {
        ParkingLocation createdParkingLocation = parkingLocationsService.create(parkingLocation);
        return new ResponseEntity<ParkingLocation>(createdParkingLocation, CREATED);
    }
}
