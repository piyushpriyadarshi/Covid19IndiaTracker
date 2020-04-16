package com.piyushpriyadarshi.covid19mail.service.multithreading;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.piyushpriyadarshi.covid19mail.multithreading.DynamicTemplatePersonalization;
import com.piyushpriyadarshi.covid19mail.multithreading.Kerla;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Mail {

    @Value("${mailservice.templateid}")
    public String templateId;

    @Value("${mailservice.apikey}")
    public String apikey;


    public boolean sendEmailUsingDynamicTemplate(Personalization personalization) throws IOException {
        Email from = new Email();
        from.setEmail("priyadarship85@gmail.com");
        from.setName("Piyush Priyadarshi");
        String subject = "Covid19 India Daily Update ";
        com.sendgrid.helpers.mail.Mail mail = new com.sendgrid.helpers.mail.Mail();

        mail.setFrom(from);

        mail.addPersonalization(personalization);

        Map<String, Object> map=personalization.getDynamicTemplateData();
        System.out.println(map.get("totalCured"));

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
            if(response.getStatusCode()==200){
                return true;
            }
        } catch (IOException ex) {
            throw ex;
        }
        return false;
    }



}
