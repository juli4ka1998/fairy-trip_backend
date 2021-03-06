package com.fairytrip.data.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "backpack")
public class Backpack extends Commodity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "backpack_id")
    private Long backpackId;

    @Column(name = "sex")
    private String sex;

    @Column(name = "material")
    private String material;

    @Column(name = "size")
    private String sizes;

    public Long getBackpackId() {
        return backpackId;
    }

    public void setBackpackId(Long backpackId) {
        this.backpackId = backpackId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }
}
