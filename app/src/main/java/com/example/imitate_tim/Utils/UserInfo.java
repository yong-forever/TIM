package com.example.imitate_tim.Utils;

/**
 * Create by 2020/2/7.
 * 类描述:
 */
public class UserInfo {
    String username;
    String password;
    String phone;
    String time;

    public UserInfo(String username, String password, String phone, String time) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.time = time;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
