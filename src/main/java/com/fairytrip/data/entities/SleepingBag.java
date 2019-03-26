package com.fairytrip.data.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "sleeping_bag")
public class SleepingBag extends Commodity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "sleeping_bag_id")
    private Long sleepingBagId;

    @Column(name = "material")
    private String material;

    @Column(name = "size")
    private String size;

    public Long getSleepingBagId() {
        return sleepingBagId;
    }

    public void setSleepingBagId(Long sleepingBagId) {
        this.sleepingBagId = sleepingBagId;
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
