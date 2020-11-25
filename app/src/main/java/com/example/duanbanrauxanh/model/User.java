package com.example.duanbanrauxanh.model;

public class User {
    public String account;
    public String passWord;
    public String Email;

    public User() {
    }

    public User(String account, String passWord, String email) {
        this.account = account;
        this.passWord = passWord;
        Email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
