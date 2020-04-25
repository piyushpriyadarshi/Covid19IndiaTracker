package com.piyushpriyadarshi.covid19mail.entity;

import javax.persistence.*;

import com.piyushpriyadarshi.covid19mail.entity.State;
@Entity
@Table
public class EmailSubscriber {
    @Id
    private String emailId;


    @OneToOne
    private State state;

    public EmailSubscriber() {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
