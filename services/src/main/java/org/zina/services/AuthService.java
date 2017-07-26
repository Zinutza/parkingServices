package org.zina.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zina.dao.UserDao;
import org.zina.model.User;
import org.zina.services.encryption.EncryptionService;

@Service
public class AuthService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private EncryptionService encryptionService;

    public User authenticateUser(User user) {
        // Read user from database by email
        User storedUser = userDao.readByEmail(user.getEmail());
        // if user doesnt exist, bail!
        if(storedUser == null) {
            return null;
        }
        // Encrypt their plaintext password
        String encryptedPassword = encryptionService.encrypt(user.getPasswordClearText(), storedUser.getSalt());
        // Compare encrypted and stored password
        if(encryptedPassword.equals(storedUser.getPasswordHash())) {
            storedUser.clearPasswordDetails();
            return storedUser;
        }
        return null;
    }
}
