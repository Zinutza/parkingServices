package org.zina.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zina.dao.RatingsDao;
import org.zina.model.Rating;

import java.util.List;

@Service
public class RatingsService {

    @Autowired
    private RatingsDao ratingsDao;

    public Rating createOrUpdate(Rating rating) {
        return ratingsDao.create(rating);
    }

    public Rating readUserRating(Long locationId, Long userId) {
        return ratingsDao.readUserRating(locationId, userId);
    }

    public Float readAverageRating(Long locationId) {
        List<Rating> ratings = ratingsDao.readAll(locationId);
        if(ratings.size() == 0) {
            return 0f;
        }
        float sum = 0f;
        for(Rating rating : ratings) {
            sum = sum + rating.getRating();
        }
        return sum / ratings.size();
    }
}
