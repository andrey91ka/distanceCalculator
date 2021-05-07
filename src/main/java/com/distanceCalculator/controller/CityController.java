package com.distanceCalculator.controller;

import com.distanceCalculator.model.City;
import com.distanceCalculator.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @GetMapping("/cities")
    public String cities(Model model) {
        List<City> cityList = cityService.findAll();
        model.addAttribute("cityList", cityList);
        return "cities";
    }
}
