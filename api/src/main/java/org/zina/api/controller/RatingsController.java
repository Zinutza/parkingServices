package org.zina.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zina.model.Rating;
import org.zina.services.RatingsService;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class RatingsController {

    @Autowired
    private RatingsService ratingService;

    @ResponseBody
    @RequestMapping(value="ratings", method = POST)
    public Rating createOrUpdate(@RequestBody Rating rating) {
        return ratingService.createOrUpdate(rating);
    }

    @ResponseBody
    @RequestMapping(value="ratings", method = GET)
    public ResponseEntity readUserRating(@RequestParam("locationId") Long locationId, @RequestParam("userId") Long userId) {
        Rating rating = ratingService.readUserRating(locationId, userId);
        if(rating == null) {
            return new ResponseEntity<String>("NO_RATING_FOUND", NOT_FOUND);
        }
        return new ResponseEntity<Integer>(rating.getRating(), OK);
    }

    @ResponseBody
    @RequestMapping(value="ratings/{locationId}/average", method = GET)
    public Float readAverageRating(@PathVariable("locationId") Long locationId) {
        return ratingService.readAverageRating(locationId);
    }
}
