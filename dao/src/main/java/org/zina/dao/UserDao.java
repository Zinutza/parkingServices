package org.zina.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.zina.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Repository
public class UserDao {

    private static final String CREATE_USER = "INSERT INTO users (id, email, password_hash, salt) VALUES (nextval('user_id_sequence'), ?, ?, ?);";
    private static final String READ_USER = "SELECT * FROM users WHERE id = ?";
    private static final String READ_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User read(Long id) {
        return (User) this.jdbcTemplate.queryForObject(READ_USER, new Object[] {id}, new BeanPropertyRowMapper(User.class));
    }

    public User readByEmail(String email) {
        try {
            return (User) this.jdbcTemplate.queryForObject(READ_USER_BY_EMAIL, new Object[]{email}, new BeanPropertyRowMapper(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User create(final User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(new PreparedStatementCreator(){
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps =connection.prepareStatement(CREATE_USER, RETURN_GENERATED_KEYS);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPasswordHash());
                ps.setString(3, user.getSalt());
                return ps;
            }
        },keyHolder);
        user.setId((Long) keyHolder.getKeys().get("id"));
        return user;
    }
}
