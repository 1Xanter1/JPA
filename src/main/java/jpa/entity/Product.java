package jpa.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Product_Characteristic> productCharacteristics;

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public List<Product_Characteristic> getProductCharacteristics() {
        return productCharacteristics;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProductCharacteristics(List<Product_Characteristic> productCharacteristics) {
        this.productCharacteristics = productCharacteristics;
    }
}
