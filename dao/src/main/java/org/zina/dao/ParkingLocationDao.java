package org.zina.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.zina.model.ParkingLocation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Repository
public class ParkingLocationDao {

    private static final String QUERY_BY_GRID = "SELECT * FROM locations WHERE latitude >= ? AND latitude <= ? AND longitude >= ? AND longitude <=?;";
    private static final String CREATE_LOCATION = "INSERT INTO locations (id, latitude, longitude, type, address) VALUES (nextval('location_id_sequence'), ?, ?, ?,?);";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ParkingLocation> queryParkingByLocationGrid(final Double latitudeLower, final Double latitudeUpper, final Double longitudeLower, final Double longitudeUpper) {
        return this.jdbcTemplate.query(new PreparedStatementCreator(){
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps =connection.prepareStatement(QUERY_BY_GRID);
                ps.setDouble(1, latitudeLower);
                ps.setDouble(2, latitudeUpper);
                ps.setDouble(3, longitudeLower);
                ps.setDouble(4, longitudeUpper);
                return ps;
            }
        }, new BeanPropertyRowMapper(ParkingLocation.class));

    }

    public ParkingLocation create(final ParkingLocation parkingLocation) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(new PreparedStatementCreator(){
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(CREATE_LOCATION, RETURN_GENERATED_KEYS);
                ps.setDouble(1, parkingLocation.getLatitude());
                ps.setDouble(2, parkingLocation.getLongitude());
                ps.setString(3, parkingLocation.getType().toString());
                ps.setString(4, parkingLocation.getAddress());
                return ps;
            }
        },keyHolder);
        parkingLocation.setId((Long) keyHolder.getKeys().get("id"));
        return parkingLocation;
    }
}
