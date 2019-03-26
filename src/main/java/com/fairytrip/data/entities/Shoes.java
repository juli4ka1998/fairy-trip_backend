package com.fairytrip.data.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@Entity
@Table(name = "shoes")
public class Shoes extends Commodity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "shoes_id")
    private Long shoesId;

    @Column(name = "sex")
    private String sex;

    @Column(name = "material")
    private String material;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "shoes_size", joinColumns = @JoinColumn(name = "shoes_id"))
    @Column(name = "size")
    private List<Integer> sizes;

    public Long getShoesId() {
        return shoesId;
    }

    public void setShoesId(Long shoesId) {
        this.shoesId = shoesId;
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

    public List<Integer> getSizes() {
        return sizes;
    }

    public void setSizes(List<Integer> sizes) {
        this.sizes = sizes;
    }
}
