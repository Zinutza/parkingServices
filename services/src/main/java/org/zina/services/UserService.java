package org.zina.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zina.dao.UserDao;
import org.zina.model.User;

@Service
public class UserService {

    @Autowired
    private UserDao dao;

    public User read(Long id) {
        return dao.read(id);
    }

    public User create(User user) {
        return dao.create(user);
    }
}
