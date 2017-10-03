package org.zina.model;

import lombok.Data;

@Data
public class FavouriteLocation {

    private Long userId;

    private Long locationId;

    public FavouriteLocation() {
        // Purposefully empty
    }

    public FavouriteLocation(Long userId, Long locationId) {
        this.userId = userId;
        this.locationId = locationId;
    }
}
