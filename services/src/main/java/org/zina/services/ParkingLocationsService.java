package org.zina.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zina.dao.ParkingLocationDao;
import org.zina.model.ParkingLocation;

import java.util.List;

@Service
public class ParkingLocationsService {

    // Degree coordinate precision of search
    private static double DEFAULT_PRECISION = 1;

    @Autowired
    private ParkingLocationDao parkingLocationDao;

    @Autowired
    private FavouritesService favouritesService;

    public List<ParkingLocation> queryParkingByLocation(Double latitude, Double longitude) {
        return queryParkingByLocationAndPrecision(latitude, longitude, DEFAULT_PRECISION);
    }

    public List<ParkingLocation> queryParkingByLocationAndPrecision(Double latitude, Double longitude, Double precision) {
        Double latitudeLower = latitude - precision;
        Double latitudeUpper = latitude + precision;
        Double longitudeLower = longitude - precision;
        Double longitudeUpper = longitude + precision;
        return parkingLocationDao.queryParkingByLocationGrid(latitudeLower, latitudeUpper,longitudeLower, longitudeUpper);
    }


    public ParkingLocation create(ParkingLocation parkingLocation) {
        return parkingLocationDao.create(parkingLocation);
    }

    public void delete(Long locationId) {
        favouritesService.delete(locationId);
        parkingLocationDao.delete(locationId);
    }
}
