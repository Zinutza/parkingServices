package org.zina.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import org.zina.model.ParkingLocation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ParkingLocationDao {

    private static final String QUERY_BY_GRID = "SELECT * FROM locations WHERE latitude >= ? AND latitude <= ? AND longitude >= ? AND longitude <=?;";

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
}
