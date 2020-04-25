package com.piyushpriyadarshi.covid19mail.api;

public class EmailModel {
    private String email;

    private String state;

    public EmailModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public EmailModel(String email, String state) {
        this.email = email;
        this.state = state;
    }
}
