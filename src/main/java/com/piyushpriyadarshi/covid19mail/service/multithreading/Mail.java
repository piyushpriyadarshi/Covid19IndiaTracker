package com.piyushpriyadarshi.covid19mail.service.multithreading;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.piyushpriyadarshi.covid19mail.entity.EmailSubscriber;
import com.piyushpriyadarshi.covid19mail.entity.State;
import com.piyushpriyadarshi.covid19mail.entity.StateWiseData;
import com.piyushpriyadarshi.covid19mail.multithreading.Andaman;
import com.piyushpriyadarshi.covid19mail.multithreading.DynamicTemplatePersonalization;
import com.piyushpriyadarshi.covid19mail.multithreading.Kerla;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class Mail {

    @Value("${mailservice.templateid}")
    public String templateId;

    @Value("${mailservice.apikey}")
    public String apikey;


    @Autowired
    private StateWiseDataService stateWiseDataService;

    private static final Logger LOGGER = LoggerFactory.getLogger(Mail.class);


    public boolean sendEmailUsingDynamicTemplate(Personalization personalization) throws IOException {
        Email from = new Email();
        from.setEmail("priyadarship85@gmail.com");
        from.setName("Piyush Priyadarshi");
        String subject = "Covid19 India Daily Update ";
        com.sendgrid.helpers.mail.Mail mail = new com.sendgrid.helpers.mail.Mail();

        mail.setFrom(from);

        mail.addPersonalization(personalization);

        mail.setTemplateId(templateId);
        SendGrid sg = new SendGrid(apikey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            if (response.getStatusCode() == 202 || response.getStatusCode() == 201 || response.getStatusCode() == 200) {
                return true;
            }
        } catch (IOException ex) {
            throw ex;
        }
        return false;
    }

    public boolean sendmail(String email) throws IOException {
        Email from = new Email("priyadarship85@gmail.com");
        String subject = "Covid19 Daily updates Test Mail";
        Email to = new Email(email);
        Content content = new Content("text/plain", "Thank you for subscribing our Daily Data Updates, \nThis is test mail for Covid19 Application to you, \nPlease move this email to inbox.");
        com.sendgrid.helpers.mail.Mail mail = new com.sendgrid.helpers.mail.Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apikey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            if (response.getStatusCode() == 202 || response.getStatusCode() == 201 || response.getStatusCode() == 200) {
                return true;
            }
        } catch (IOException ex) {
            throw ex;
        }
        return false;
    }

    public boolean sendDailyMail(String email, String state) {
        Date now = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String mysqlDateString = formatter.format(now);
        Personalization personalization = new Personalization();
        if (state.equals("INDIA All States")) {
            List<StateWiseData> ll = stateWiseDataService.indiaTotal();

            personalization.addDynamicTemplateData("totalCured", ll.get(ll.size()-1).getCuredCase());
            personalization.addDynamicTemplateData("totalCase", ll.get(ll.size()-1).getTotalCase());
            personalization.addDynamicTemplateData("totalDeath", ll.get(ll.size()-1).getTotalDeath());
            LOGGER.info("for India today total data is "+ll.get(ll.size()-1));
        } else {
            List<StateWiseData> ll = stateWiseDataService.findByStateNameAndDate(mysqlDateString, state);
            if (ll.size() == 0) {
                LOGGER.info("Today StateWise Data is for " + state);
                return false;
            }
            personalization.addDynamicTemplateData("totalCured", ll.get(0).getCuredCase());
            personalization.addDynamicTemplateData("totalCase", ll.get(0).getTotalCase());
            personalization.addDynamicTemplateData("totalDeath", ll.get(0).getTotalDeath());

        }
        personalization.addDynamicTemplateData("Sender_Name", "@Piyush Priyadarshi");
        personalization.addDynamicTemplateData("Sender_Address", "Bangalore");
        personalization.addDynamicTemplateData("city", "Bangalore");
        personalization.addDynamicTemplateData("state", state);
        personalization.addDynamicTemplateData("today", mysqlDateString);

        Email to = new Email();
        to.setEmail(email);
        personalization.addTo(to);
        try {
            boolean status = this.sendEmailUsingDynamicTemplate(personalization);
            return status;
        } catch (Exception e) {
            LOGGER.warn("Some Exception Occured During Sending Daily Mail for " + email + " " + state + "" + e);
            return false;
        }
    }



}
