package com.piyushpriyadarshi.covid19mail.repository;

import com.piyushpriyadarshi.covid19mail.entity.EmailSubscriber;
import com.piyushpriyadarshi.covid19mail.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscribeRepository extends JpaRepository<EmailSubscriber,String> {

    List<EmailSubscriber> findEmailSubscriberByState(State s);
}
