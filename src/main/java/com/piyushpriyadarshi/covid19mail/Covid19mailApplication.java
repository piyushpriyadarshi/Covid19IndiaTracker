package com.piyushpriyadarshi.covid19mail;

import com.piyushpriyadarshi.covid19mail.entity.EmailSubscriber;
import com.piyushpriyadarshi.covid19mail.entity.State;
import com.piyushpriyadarshi.covid19mail.entity.StateWiseData;
import com.piyushpriyadarshi.covid19mail.multithreading.*;
import com.piyushpriyadarshi.covid19mail.repository.StateRepository;
import com.piyushpriyadarshi.covid19mail.repository.StateWiseDataRepository;
import com.piyushpriyadarshi.covid19mail.repository.SubscribeRepository;
import com.piyushpriyadarshi.covid19mail.service.multithreading.StateWiseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class Covid19mailApplication{

    @Autowired
    private SubscribeRepository subscribeRepository;

    @Autowired
    private StateRepository stateRepository;

    public static void main(String[] args) {
        SpringApplication.run(Covid19mailApplication.class, args);
    }

    @PostConstruct
    public void init(){
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("IST"));
    }



}
