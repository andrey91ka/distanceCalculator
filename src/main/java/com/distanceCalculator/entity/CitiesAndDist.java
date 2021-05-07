package com.distanceCalculator.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "root")
@XmlType(propOrder = { "cities", "distances" })
public class CitiesAndDist {

    List<CityJaxb> cities;

    List<DistanceJaxb> distances;

    @XmlElementWrapper(name = "Cities")
    @XmlElement(name = "City")
    public List<CityJaxb> getCities() {
        return cities;
    }

    public void setCities(List<CityJaxb> cities) {
        this.cities = cities;
    }
    @XmlElementWrapper(name = "Distances")
    @XmlElement(name = "Distance")
    public List<DistanceJaxb> getDistances() {
        return distances;
    }

    public void setDistances(List<DistanceJaxb> distances) {
        this.distances = distances;
    }
}
