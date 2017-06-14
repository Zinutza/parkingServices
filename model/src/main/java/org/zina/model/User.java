package org.zina.model;

import lombok.Data;

@Data
public class User {

    private Long id;

    private String email;

    private String passwordHash;

    private String salt;

    private String passwordClearText;
}
