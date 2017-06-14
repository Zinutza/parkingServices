package org.zina.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zina.dao.UserDao;
import org.zina.model.User;
import org.zina.services.encryption.EncryptionService;

@Service
public class UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private EncryptionService encryptionService;

    public User read(Long id) {
        User user = dao.read(id);
        clearPasswordDetails(user);
        return user;
    }


    public User create(User user) {
        encryptPassword(user);
        User createdUser = dao.create(user);
        clearPasswordDetails(user);
        return createdUser;
    }

    private void clearPasswordDetails(User user) {
        user.setPasswordHash(null);
        user.setSalt(null);
    }

    private void encryptPassword(User user) {
        String salt = encryptionService.getSalt();
        String encryptedPassword = encryptionService.encrypt(user.getPasswordClearText(), salt);
        user.setPasswordHash(encryptedPassword);
        user.setSalt(salt);
        user.setPasswordClearText(null);
    }
}
