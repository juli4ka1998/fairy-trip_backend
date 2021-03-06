package com.fairytrip.data.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "tent")
public class Tent extends Commodity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "tent_id")
    private Long tentId;

    @Column(name = "material")
    private String material;

    @Column(name = "size")
    private String size;

    public Long getTentId() {
        return tentId;
    }

    public void setTentId(Long tentId) {
        this.tentId = tentId;
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
