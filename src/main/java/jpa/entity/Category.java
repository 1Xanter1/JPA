package jpa.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Categories")
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;

    @Column(name = "category_name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List <Product> products;

    @OneToMany(mappedBy = "category")
    private List <Characteristic> characteristics;


    public long getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public Category(){
        this.characteristics = new ArrayList<>();
    }
    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
