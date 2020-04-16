package com.piyushpriyadarshi.covid19mail.api;

import com.piyushpriyadarshi.covid19mail.entity.Model;
import com.piyushpriyadarshi.covid19mail.entity.StateWiseData;
import com.piyushpriyadarshi.covid19mail.service.multithreading.StateWiseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private StateWiseDataService stateWiseDataService;

    @GetMapping("/api/getAllStates")
    public List<String> getAllStates(){
        return stateWiseDataService.getAllStates();

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
       return stateWiseDataService.indiaTotal();

    }

}
