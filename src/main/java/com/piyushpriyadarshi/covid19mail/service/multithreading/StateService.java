package com.piyushpriyadarshi.covid19mail.service.multithreading;

import com.piyushpriyadarshi.covid19mail.entity.State;
import com.piyushpriyadarshi.covid19mail.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;

    public State getStateByName(String name){
        return stateRepository.findStatesByName(name);
    }
}
