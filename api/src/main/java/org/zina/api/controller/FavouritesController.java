package org.zina.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zina.model.FavouriteLocation;
import org.zina.model.ParkingLocation;
import org.zina.services.FavouritesService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class FavouritesController {

    @Autowired
    private FavouritesService favouritesService;

    @ResponseBody
    @RequestMapping(value="favourites", method = GET)
    public List<ParkingLocation> list(@RequestParam("userId") Long userId) {
        return favouritesService.list(userId);
    }

    @ResponseBody
    @RequestMapping(value = "favourites", method = POST)
    public ResponseEntity create(@RequestBody FavouriteLocation favouriteLocation) {
        FavouriteLocation createdFavouriteLocation = favouritesService.create(favouriteLocation);
        return new ResponseEntity<FavouriteLocation>(createdFavouriteLocation, CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "favourites", method = DELETE)
    public ResponseEntity delete(@RequestParam("userId") Long userId, @RequestParam("locationId") Long locationId) {
        favouritesService.delete(new FavouriteLocation(userId, locationId));
        return new ResponseEntity(NO_CONTENT);
    }
}
