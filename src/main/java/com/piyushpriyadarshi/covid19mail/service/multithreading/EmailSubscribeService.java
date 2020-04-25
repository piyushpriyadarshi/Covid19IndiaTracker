package com.piyushpriyadarshi.covid19mail.service.multithreading;

import com.piyushpriyadarshi.covid19mail.entity.EmailSubscriber;
import com.piyushpriyadarshi.covid19mail.entity.State;
import com.piyushpriyadarshi.covid19mail.repository.SubscribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailSubscribeService {
    @Autowired
    private SubscribeRepository subscribeRepository;

    public void saveEmailSubscriber(EmailSubscriber emailSubscriber){
        subscribeRepository.save(emailSubscriber);
    }


    public List<EmailSubscriber> findEmailSubscriberByState(State s){
        return subscribeRepository.findEmailSubscriberByState(s);
    }
}
