package com.piyushpriyadarshi.covid19mail;

import com.piyushpriyadarshi.covid19mail.entity.StateWiseData;
import com.piyushpriyadarshi.covid19mail.multithreading.*;
import com.piyushpriyadarshi.covid19mail.repository.StateWiseDataRepository;
import com.piyushpriyadarshi.covid19mail.service.multithreading.StateWiseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@EnableAsync
@SpringBootApplication
public class Covid19mailApplication implements CommandLineRunner {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private StateWiseDataService stateWiseDataService;

    public static void main(String[] args) {
        SpringApplication.run(Covid19mailApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        Kerla kerla=applicationContext.getBean(Kerla.class);
//        Telengana telengana=applicationContext.getBean(Telengana.class);
//
//
//        Thread t1=new Thread(telengana);
//        t1.start();


//        TN tn=applicationContext.getBean(TN.class);


//        Karnataka kr=applicationContext.getBean(Karnataka.class);
//
//        Thread t1=new Thread(kr);
//        t1.start();


    }
}
