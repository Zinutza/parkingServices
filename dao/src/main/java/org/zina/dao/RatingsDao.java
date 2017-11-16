package org.zina.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import org.zina.model.Rating;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RatingsDao {

    private static final String CREATE_RATING = "INSERT INTO ratings VALUES (?,?,?);";
    private static final String UPDATE_RATING = "UPDATE ratings SET rating = ? WHERE location_id = ? AND user_id = ?;";
    private static final String READ_USER_RATING = "SELECT * FROM ratings WHERE location_id = ? and user_id = ?;";
    private static final String READ_ALL_RATINGS = "SELECT * FROM ratings WHERE location_id = ?;";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Rating create(final Rating rating) {
        try {
            this.jdbcTemplate.update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection connection)
                        throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(CREATE_RATING);
                    ps.setLong(1, rating.getLocationId());
                    ps.setLong(2, rating.getUserId());
                    ps.setLong(3, rating.getRating());
                    return ps;
                }
            });
            return rating;
        } catch (DuplicateKeyException e) {
            return this.update(rating);
        }
    }

    private Rating update(final Rating rating) {
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(UPDATE_RATING);
                ps.setLong(1, rating.getRating());
                ps.setLong(2, rating.getLocationId());
                ps.setLong(3, rating.getUserId());
                return ps;
            }
        });
        return rating;
    }

    public Rating readUserRating(final Long locationId, final Long userId) {
        try {
            return (Rating) this.jdbcTemplate.queryForObject(READ_USER_RATING, new Object[]{locationId, userId}, new BeanPropertyRowMapper(Rating.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Rating> readAll(final Long locationId) {
        return this.jdbcTemplate.query(new PreparedStatementCreator(){
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps =connection.prepareStatement(READ_ALL_RATINGS);
                ps.setLong(1, locationId);
                return ps;
            }
        }, new BeanPropertyRowMapper(Rating.class));
    }
}
