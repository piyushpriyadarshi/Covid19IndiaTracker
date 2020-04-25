package com.piyushpriyadarshi.covid19mail.multithreading;

import com.piyushpriyadarshi.covid19mail.entity.EmailSubscriber;
import com.piyushpriyadarshi.covid19mail.entity.State;
import com.piyushpriyadarshi.covid19mail.entity.StateWiseData;
import com.piyushpriyadarshi.covid19mail.service.multithreading.EmailSubscribeService;
import com.piyushpriyadarshi.covid19mail.service.multithreading.Mail;
import com.piyushpriyadarshi.covid19mail.service.multithreading.StateService;
import com.piyushpriyadarshi.covid19mail.service.multithreading.StateWiseDataService;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class AllIndia implements Runnable {
    private static final Logger LOGGER= LoggerFactory.getLogger(AllIndia.class);


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
        LOGGER.info("INDIA All States Thread Started");
        Date now = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String mysqlDateString = formatter.format(now);
        State s=stateService.getStateByName("INDIA All States");
        List<EmailSubscriber> emailList=emailSubscribeService.findEmailSubscriberByState(s);
        if(emailList.size()==0){
            LOGGER.info("Email List id empty for INDIA All States");
            return;
        }
        List<StateWiseData> ll = stateWiseDataService.indiaTotal();

        Personalization personalization=new Personalization();
        personalization.addDynamicTemplateData("totalCured", ll.get(ll.size()-1).getCuredCase());
        personalization.addDynamicTemplateData("totalCase", ll.get(ll.size()-1).getTotalCase());
        personalization.addDynamicTemplateData("totalDeath", ll.get(ll.size()-1).getTotalDeath());

        personalization.addDynamicTemplateData("Sender_Name", "@Piyush Priyadarshi");
        personalization.addDynamicTemplateData("Sender_Address", "Bangalore");
        personalization.addDynamicTemplateData("city", "Bangalore");
        personalization.addDynamicTemplateData("state", "INDIA ALL State");
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
            LOGGER.info("MAil Sent SuccessFully");
        }
        catch (Exception e){
            LOGGER.warn("Exception OCcured While Sending mail "+e);
            System.out.println("Some Error Ocuured");
        }
        LOGGER.info("INDIA Thread END");
    }
}
