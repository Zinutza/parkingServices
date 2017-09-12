package org.zina.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zina.dao.FavouritesDao;
import org.zina.model.FavouriteLocation;
import org.zina.model.ParkingLocation;

import java.util.List;

@Service
public class FavouritesService {

    @Autowired
    private FavouritesDao favouritesDao;

    public List<ParkingLocation> list(Long userId) {
        return favouritesDao.list(userId);
    }

    public FavouriteLocation create(FavouriteLocation favouriteLocation) {
        return favouritesDao.create(favouriteLocation);
    }

    public void delete(FavouriteLocation favouriteLocation) {
        favouritesDao.delete(favouriteLocation);
    }
}
