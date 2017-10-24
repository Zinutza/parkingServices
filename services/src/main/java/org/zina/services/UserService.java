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

    public User create(User user) {
        encryptPassword(user);
        User createdUser = dao.create(user);
        user.clearPasswordDetails();
        return createdUser;
    }


    private void encryptPassword(User user) {
        String salt = encryptionService.getSalt();
        String encryptedPassword = encryptionService.encrypt(user.getPasswordClearText(), salt);
        user.setPasswordHash(encryptedPassword);
        user.setSalt(salt);
        user.setPasswordClearText(null);
    }
}
