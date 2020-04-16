package com.piyushpriyadarshi.covid19mail.multithreading;

import com.piyushpriyadarshi.covid19mail.entity.StateWiseData;
import com.piyushpriyadarshi.covid19mail.service.multithreading.Mail;
import com.piyushpriyadarshi.covid19mail.service.multithreading.StateWiseDataService;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class Telengana implements Runnable {

    @Autowired
    private StateWiseDataService stateWiseDataService;

    @Autowired
    private Mail mailService;

    @Override
    public void run() {

        Date now = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String mysqlDateString = formatter.format(now);
        List<StateWiseData> ll=stateWiseDataService.findByStateNameAndDate(mysqlDateString,"Telengana");

        Personalization personalization=new Personalization();
        personalization.addDynamicTemplateData("totalCured",ll.get(0).getCuredCase());
        personalization.addDynamicTemplateData("totalCase", ll.get(0).getTotalCase());
        personalization.addDynamicTemplateData("totalDeath", ll.get(0).getTotalDeath());
        personalization.addDynamicTemplateData("Sender_Name", "@Piyush Priyadarshi");
        personalization.addDynamicTemplateData("Sender_Address", "Bangalore");
        personalization.addDynamicTemplateData("city", "Bangalore");
        personalization.addDynamicTemplateData("state", "Telengana");
        personalization.addDynamicTemplateData("today", mysqlDateString);




        Email e1=new Email();
        e1.setEmail("priyadarship4@gmail.com");
        Email e2=new Email();
        e2.setEmail("Preetipriyanka24@gmail.com");
        personalization.addBcc(e1);
        personalization.addBcc(e2);

        Email to = new Email();
        to.setName("Piyush");
        to.setEmail("priyadarship85@gmail.com");
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
