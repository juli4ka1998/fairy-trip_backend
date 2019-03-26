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
    private Long foodId;

    @Column(name = "weight")
    private String weight;

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long shoesId) {
        this.foodId = foodId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
