package com.distanceCalculator.controller;

import com.distanceCalculator.entity.CitiesAndDist;
import com.distanceCalculator.entity.CityJaxb;
import com.distanceCalculator.entity.DistanceJaxb;
import com.distanceCalculator.model.City;
import com.distanceCalculator.model.Distance;
import com.distanceCalculator.service.CityService;
import com.distanceCalculator.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class FileLoadController {
    private final CityService cityService;
    private final DistanceService distanceService;

    @Autowired
    public FileLoadController(CityService cityService, DistanceService distanceService) {
        this.cityService = cityService;
        this.distanceService = distanceService;
    }


    @GetMapping("/")
    public String fileLoad(Model model) {
        return "/file-load";
    }

    @PostMapping("/file-load")
    public String fileLoader(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) throws JAXBException, IOException {

        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Пожалуйста, выберите файл для загрузки.");
            return "redirect:/";
        }


        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();


        {
            try {
                JAXBContext jaxbCities = JAXBContext.newInstance(CitiesAndDist.class);
                Unmarshaller jaxbUnmarshaller = jaxbCities.createUnmarshaller();

                CitiesAndDist citiesAndDist = (CitiesAndDist) jaxbUnmarshaller.unmarshal(convFile);
                List<CityJaxb> cities = citiesAndDist.getCities();
                List<DistanceJaxb> distances = citiesAndDist.getDistances();


                for (CityJaxb cityJaxb : cities) {
                    City city = new City();
                    city.setName(cityJaxb.getName());
                    city.setLongitude(cityJaxb.getLongitude());
                    city.setLatitude(cityJaxb.getLatitude());
                    cityService.saveCity(city);
                }

                for (DistanceJaxb distanceJaxb : distances) {
                    Distance distance = new Distance();
                    distance.setFromCity(distanceJaxb.getFromCity());
                    distance.setToCity(distanceJaxb.getToCity());
                    distance.setDistance(distanceJaxb.getDistance());
                    distanceService.saveDistance(distance);

                }

            } catch (
                    JAXBException e) {
                e.printStackTrace();
            }
        }

        return "redirect:/cities";
    }
}
