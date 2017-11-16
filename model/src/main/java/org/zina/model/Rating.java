package org.zina.model;

import lombok.Data;

@Data
public class Rating {

    private Long locationId;

    private Long userId;

    private Integer rating;
}
