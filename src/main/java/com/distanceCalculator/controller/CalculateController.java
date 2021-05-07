package com.distanceCalculator.controller;

import com.distanceCalculator.model.City;
import com.distanceCalculator.model.Distance;
import com.distanceCalculator.service.CityService;
import com.distanceCalculator.service.DistanceService;
import com.distanceCalculator.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CalculateController {
    private final CityService cityService;
    private final DistanceService distanceService;

    @Autowired
    public CalculateController(CityService cityService, DistanceService distanceService) {
        this.cityService = cityService;
        this.distanceService = distanceService;
    }

    @GetMapping("/calculate")
    public String cities(Model model) {
        List<City> cityList = cityService.findAll();
        model.addAttribute("cityList", cityList);
        Calculate calculate = new Calculate();
        model.addAttribute("calculate", calculate);
        return "calculate";
    }

    @PostMapping("/calculate")
    public String Calculate(Calculate calculate, Model model) {
        double lat1 = calculate.getCityFrom().getLatitude();
        double long1 = calculate.getCityFrom().getLongitude();
        double lat2 = calculate.getCityTo().getLatitude();
        double long2 = calculate.getCityTo().getLongitude();
        String s = calculate.getTypeCalc().get(0);
        double way = 0;
        double wayMatrix = 0;
        Dijkstra dijkstra = new Dijkstra();
        model.addAttribute(calculate);

        List<City> cities = cityService.findAll();
        List<Distance> distanceList = distanceService.findAll();
        List<Vertex> vertices = new ArrayList<>();

        for (City city : cities) {
            Vertex vertex = new Vertex(city.getName());
            vertices.add(vertex);
        }

        Vertex vertexStart=null;
        Vertex vertexEnd=null;
        for (Vertex vertex: vertices){
            if(vertex.getName().equals(calculate.getCityFrom().getName())){
                vertexStart = vertex;
            }
        }
        for (Vertex vertex2: vertices) {
            if (vertex2.getName().equals(calculate.getCityTo().getName())) {
                vertexEnd = vertex2;
            }
        }


        for (Distance distance : distanceList) {
            Vertex vertex1 = new Vertex(distance.getFromCity());
            Vertex vertex2 = new Vertex(distance.getToCity());

            for (Vertex vertex : vertices) {
                if (vertex.getName().equals(vertex1.getName())) {
                    vertex.addNeighbour(new Edge(distance.getDistance(), vertex, vertex2));
                }
            }
        }

        if ("CROWFLIGHT".equals(s)) {
            way = Haversin.haversine(lat1, long1, lat2, long2);
        } else if ("DISTANCE_MATRIX".equals(s)) {
            dijkstra.computeShortestPaths(vertexStart);
            way = vertexEnd.getDistance();

        } else {
            way = Haversin.haversine(lat1, long1, lat2, long2);
            wayMatrix = vertexEnd.getDistance();
        }

        model.addAttribute("way", way);
        model.addAttribute("wayMatrix", wayMatrix);

        return "calculate-result";

    }


}
