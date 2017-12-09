package org.zina.model;

import lombok.Data;

@Data
public class ParkingLocation {

    private Long id;

    private Double latitude;

    private Double longitude;

    private ParkingType type;

    private String address;

    private Long creatorId;
}
