package com.piyushpriyadarshi.covid19mail.service.multithreading;

import com.piyushpriyadarshi.covid19mail.entity.Model;
import com.piyushpriyadarshi.covid19mail.entity.StateWiseData;
import com.piyushpriyadarshi.covid19mail.repository.StateWiseDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@Service
public class StateWiseDataService {
    @Autowired
    public StateWiseDataRepository stateWiseDataRepository;

    public List<StateWiseData> findByStateName(String name){
        return stateWiseDataRepository.findByStateName(name);
    }
    public List<StateWiseData> findByStateNameAndDate(String date,String name){
        return stateWiseDataRepository.findByStateNameAndDate(date,name);
    }

    public List<StateWiseData> indiaTotal(){
        List<Object[]> arr=stateWiseDataRepository.indiaTotal();
        List<StateWiseData> ll=new ArrayList<StateWiseData>();
        for (Object[] str:arr) {
            StateWiseData stateWiseData=new StateWiseData();
            //[1637,133,38,"2020-03-31T18:30:00.000+0000"]
            BigDecimal tc=(BigDecimal) str[0];
            BigDecimal cc=(BigDecimal) str[1];
            BigDecimal td=(BigDecimal) str[2];
            stateWiseData.setTotalCase(tc.intValue());
            stateWiseData.setCuredCase(cc.intValue());
            stateWiseData.setTotalDeath(td.intValue());
            Timestamp dd=(Timestamp)str[3];
            stateWiseData.setDataDate(new Date(dd.getTime()));
            ll.add(stateWiseData);

        }
        return ll;
    }
    public List<String> getAllStates(){
        List<String> allStates=new ArrayList<String>();
      String[] arr={"Andhra Pradesh","Andaman and Nicobar Islands","Bihar","Chandigarh","Chhattisgarh","Delhi","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Karnataka","Kerala","Ladakh","Madhya Pradesh","Maharashtra","Manipur","Mizoram","Odisha","Puducherry","Punjab","Rajasthan","Tamil Nadu","Telengana","Uttarakhand","Uttar Pradesh","West Bengal","Assam","Jharkhand","Arunachal Pradesh","Tripura","Nagaland","Meghalaya"};
      allStates= Arrays.asList(arr);
      return allStates;
    }
}
