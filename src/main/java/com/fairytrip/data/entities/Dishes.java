package com.fairytrip.data.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "dishes")
public class Dishes extends Commodity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "dishes_id")
    private Long dishesId;

    @Column(name = "material")
    private String material;

    @Column(name = "size")
    private String size;

    public Long getDishesId() {
        return dishesId;
    }

    public void setDishesId(Long dishesId) {
        this.dishesId = dishesId;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
