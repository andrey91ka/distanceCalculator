package com.distanceCalculator.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Distance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long ID;

    @Column(name = "from_city")
    private String fromCity;

    @Column(name = "to_city")
	private String toCity;

	private int distance;

}
