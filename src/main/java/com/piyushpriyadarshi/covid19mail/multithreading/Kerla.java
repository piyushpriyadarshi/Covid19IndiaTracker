package com.piyushpriyadarshi.covid19mail.multithreading;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.piyushpriyadarshi.covid19mail.entity.EmailSubscriber;
import com.piyushpriyadarshi.covid19mail.entity.State;
import com.piyushpriyadarshi.covid19mail.entity.StateWiseData;
import com.piyushpriyadarshi.covid19mail.repository.StateWiseDataRepository;
import com.piyushpriyadarshi.covid19mail.service.multithreading.EmailSubscribeService;
import com.piyushpriyadarshi.covid19mail.service.multithreading.Mail;
import com.piyushpriyadarshi.covid19mail.service.multithreading.StateService;
import com.piyushpriyadarshi.covid19mail.service.multithreading.StateWiseDataService;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class Kerla implements Runnable{
    @Autowired
    private StateWiseDataService stateWiseDataService;

    @Autowired
    private Mail mailService;

    @Autowired
    private EmailSubscribeService emailSubscribeService;

    @Autowired
    private StateService stateService;

    @Override
    public void run() {
        Date now = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String mysqlDateString = formatter.format(now);
        State s=stateService.getStateByName("Kerala");
        List<EmailSubscriber> emailList=emailSubscribeService.findEmailSubscriberByState(s);
        if(emailList.size()==0){
            System.out.println("No email subscribed for Kerala");
            return;
        }
        List<StateWiseData> ll=stateWiseDataService.findByStateNameAndDate(mysqlDateString,"Kerala");
        if(ll.size()==0){
            System.out.println("No Data for today");
            return;
        }

        Personalization personalization=new Personalization();
        personalization.addDynamicTemplateData("totalCured",ll.get(0).getCuredCase());
        personalization.addDynamicTemplateData("totalCase", ll.get(0).getTotalCase());
        personalization.addDynamicTemplateData("totalDeath", ll.get(0).getTotalDeath());
        personalization.addDynamicTemplateData("Sender_Name", "@Piyush Priyadarshi");
        personalization.addDynamicTemplateData("Sender_Address", "Bangalore");
        personalization.addDynamicTemplateData("city", "Bangalore");
        personalization.addDynamicTemplateData("state", "Kerala");
        personalization.addDynamicTemplateData("today", mysqlDateString);

        for (EmailSubscriber email: emailList) {
            Email e1=new Email();
            e1.setEmail(email.getEmailId());
            personalization.addBcc(e1);
        }


        Email to = new Email();
        to.setName("Sam");
        to.setEmail("pkpray2@gmail.com");
        personalization.addTo(to);
        try{
            boolean status=mailService.sendEmailUsingDynamicTemplate(personalization);
            System.out.println(status);
        }
        catch (Exception e){
            System.out.println("Some Error Ocuured");
        }
    }

}
