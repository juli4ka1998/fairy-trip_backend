package com.fairytrip.data.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "food")
public class Food extends Commodity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long id;

    @Column(name = "weight")
    private String weight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
