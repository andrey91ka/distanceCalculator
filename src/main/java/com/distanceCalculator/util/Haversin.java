package com.distanceCalculator.util;

public class Haversin {
    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        // расстояние между широтой и долготой

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // преобразовать в радианы

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);


        // применить формулы
        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) *
                Math.cos(lat1) * Math.cos(lat2);

        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        double distance = rad * c;
        return Math.ceil(distance);

    }
}
