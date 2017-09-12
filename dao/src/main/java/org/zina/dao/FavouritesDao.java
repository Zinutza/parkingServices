package org.zina.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import org.zina.model.FavouriteLocation;
import org.zina.model.ParkingLocation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FavouritesDao {

    private static final String LIST_FAVOURITES = "select locations.id, latitude, longitude, type, address from favourites join locations ON favourites.location_id = locations.id where user_id = ? ;";
    private static final String CREATE_FAVOURITE = "INSERT INTO favourites VALUES (?, ?);";
    private static final String DELETE_FAVOURITE = "DELETE FROM favourites WHERE user_id = ? AND location_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ParkingLocation> list(final Long userId) {
        return this.jdbcTemplate.query(new PreparedStatementCreator(){
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps =connection.prepareStatement(LIST_FAVOURITES);
                ps.setLong(1, userId);
                return ps;
            }
        }, new BeanPropertyRowMapper(ParkingLocation.class));
    }

    public FavouriteLocation create(final FavouriteLocation favouriteLocation) {
        this.jdbcTemplate.update(new PreparedStatementCreator(){
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(CREATE_FAVOURITE);
                ps.setLong(1, favouriteLocation.getUserId());
                ps.setLong(2, favouriteLocation.getLocationId());
                return ps;
            }
        });
        return favouriteLocation;
    }

    public void delete(final FavouriteLocation favouriteLocation) {
        this.jdbcTemplate.update(new PreparedStatementCreator(){
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(DELETE_FAVOURITE);
                ps.setLong(1, favouriteLocation.getUserId());
                ps.setLong(2, favouriteLocation.getLocationId());
                return ps;
            }
        });
    }
}
