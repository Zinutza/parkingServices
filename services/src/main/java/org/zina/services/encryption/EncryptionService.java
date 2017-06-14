package org.zina.services.encryption;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Component
public class EncryptionService {

    public String encrypt(String password, String salt) {
        if (password == null || salt == null) {
            return null;
        }

        byte[] saltByte = salt.getBytes();
        byte[] input = null;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(saltByte);
            input = digest.digest(password.getBytes("UTF-8"));
            for (int i = 0; i < 10; i++) {
                digest.reset();
                input = digest.digest(input);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }

        String result = new String(input);

        result = result.replace('"', 'h');
        result = result.replace('\\', 'w');
        result = result.replace('|', 'n');
        result = result.replace('\'', 'a');
        result = result.replace('\n', '4');

        result.toLowerCase();
        return result;
    }

    public String getSalt() {
        Random random = new Random();
        char[] symbols = new char[36];

        for (int idx = 0; idx < 10; ++idx) {
            symbols[idx] = (char) ('0' + idx);
        }
        for (int idx = 10; idx < 36; ++idx) {
            symbols[idx] = (char) ('a' + idx - 10);
        }

        char[] buf = new char[3];

        for (int idx = 0; idx < 3; ++idx) {
            buf[idx] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buf);

    }

}
