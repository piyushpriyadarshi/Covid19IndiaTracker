package com.piyushpriyadarshi.covid19mail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping(value = {"/","/index","/index.html"})
    public String index() {
        return "index";
    }

    /**
     *
     * *
     *
     *
     *
     *
     * Andhra Pradesh 1
     * Andaman and Nicobar Islands 2
     * Bihar 3
     * Chandigarh 4
     * Chhattisgarh 5
     * Delhi 6
     * Goa 7
     * Gujarat 8
     * Haryana 9
     * Himachal Pradesh 10
     * Jammu and Kashmir 11
     * Karnataka 12
     * Kerala 13
     * Ladakh 14
     * Madhya Pradesh 15
     * Maharashtra
     * Manipur
     * Mizoram
     * Odisha
     * Puducherry
     * Punjab
     * Rajasthan
     * Tamil Nadu
     * Telengana
     * Uttarakhand
     * Uttar Pradesh
     * West Bengal
     */
}
