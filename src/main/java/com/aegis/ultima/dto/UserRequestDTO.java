package com.aegis.ultima.dto;

import com.aegis.ultima.model.User;
import com.aegis.ultima.util.BaseRequest;

public class UserRequestDTO extends BaseRequest {

    private String username;
    private String password;
    private String email;
    private String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUserFromDto(){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setName(name);

        return user;
    }

}
