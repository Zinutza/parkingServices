package org.zina.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.zina.model.User;

@Repository
public class UserDao {

    private static final String READ_USER = "SELECT * FROM users WHERE id = ?";

    

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User read(Long id) {
        return (User) this.jdbcTemplate.queryForObject(READ_USER, new Object[] {id}, new BeanPropertyRowMapper(User.class));
    }

    public User create(User user) {

    }
}
