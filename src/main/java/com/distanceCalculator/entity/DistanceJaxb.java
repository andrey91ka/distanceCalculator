package com.distanceCalculator.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "distance")
public class DistanceJaxb {

    private String fromCity;
    private String toCity;
    private int distance;

    public String getFromCity() {
        return fromCity;
    }

    @XmlElement(name = "fromCity")
    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }
    @XmlElement(name = "toCity")
    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public int getDistance() {
        return distance;
    }
    @XmlElement(name = "distance")
    public void setDistance(int distance) {
        this.distance = distance;
    }

    public DistanceJaxb() {
    }

    public DistanceJaxb(String fromCity, String toCity, int distance) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = distance;
    }
}
