package com.distanceCalculator.util;

import com.distanceCalculator.model.City;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class Calculate {
    City cityFrom;
    City cityTo;
    List<String> typeCalc = Arrays.asList("CROWFLIGHT" , "DISTANCE_MATRIX", "ALL");
}

