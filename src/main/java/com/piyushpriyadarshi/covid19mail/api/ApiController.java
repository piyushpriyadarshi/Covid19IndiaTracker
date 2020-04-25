package com.piyushpriyadarshi.covid19mail.api;

import com.piyushpriyadarshi.covid19mail.entity.EmailSubscriber;
import com.piyushpriyadarshi.covid19mail.entity.Model;
import com.piyushpriyadarshi.covid19mail.entity.State;
import com.piyushpriyadarshi.covid19mail.entity.StateWiseData;
import com.piyushpriyadarshi.covid19mail.service.multithreading.EmailSubscribeService;
import com.piyushpriyadarshi.covid19mail.service.multithreading.Mail;
import com.piyushpriyadarshi.covid19mail.service.multithreading.StateService;
import com.piyushpriyadarshi.covid19mail.service.multithreading.StateWiseDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private StateWiseDataService stateWiseDataService;

    @Autowired
    private StateService stateService;

    @Autowired
    private EmailSubscribeService emailSubscribeService;

    @Autowired
    private Mail mail;

    @GetMapping("/api/getAllStates")
    public List<String> getAllStates(){
        List<String> allStates=stateWiseDataService.getAllStates();
        if(allStates.size()==0){
            LOGGER.warn("Get All Sates Methods returned "+allStates);
        }
        LOGGER.info("Get All Sates Methods returned is "+allStates);
        return allStates;
    }
    @GetMapping("/api/getStateWiseData/{id}")
    public List<StateWiseData> getAllStates(@PathVariable("id") int id) {
        System.out.println(id);
        if(id>=0 && id<=stateWiseDataService.getAllStates().size()){
            List<StateWiseData> data= stateWiseDataService.findByStateName(stateWiseDataService.getAllStates().get(id));
            return data;
        }
        return null;

    }

    @GetMapping("/api/getIndiaTotal")
    public List<StateWiseData> indiaTotal() {
        List<StateWiseData> indiWiseData=stateWiseDataService.indiaTotal();
        if(indiWiseData.size()==0){
            LOGGER.warn("Inside /api/getIndiaTotal api call but returned "+indiWiseData);
        }
        LOGGER.info("Inside /api/getIndiaTotal api call  returned value is "+indiWiseData);
       return indiWiseData;
    }

    @PostMapping("/api/subscribe")
    public Message subscribe(@ModelAttribute EmailModel emailModel) throws IOException {
        LOGGER.info("Method /api/subscribe started for "+emailModel.getEmail());


        if(emailModel.getState()!=null && emailModel.getEmail() !=null){
            State s=stateService.getStateByName(emailModel.getState());
            LOGGER.info("Method /api/subscribe started");
            EmailSubscriber emailSubscriber=new EmailSubscriber();
            emailSubscriber.setEmailId(emailModel.getEmail());
            emailSubscriber.setState(s);
            emailSubscribeService.saveEmailSubscriber(emailSubscriber);
            Message m=new Message();
            m.setMessage("Successfully Added the Email for Daily Updates");
            try{
                boolean status=mail.sendmail(emailModel.getEmail());
                boolean status2=mail.sendDailyMail(emailModel.getEmail(),emailModel.getState());
                LOGGER.info("Mail Send Successfully to"+emailModel.getEmail());
            }
            catch (IOException e){
                LOGGER.warn("Exception while Sending mail to "+emailModel.getEmail()+" "+e);
                throw e;
            }

            return m;
        }
        LOGGER.warn("Data Incorrect   "+emailModel);
        Message m=new Message();
        m.setMessage("Invalid Data");
        return m;
    }

}
