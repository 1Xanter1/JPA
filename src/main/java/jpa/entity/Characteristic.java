package jpa.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Characteristics")
public class Characteristic {

    @Id
    @Column(name = "characteristic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long characteristicId;

    @Column(name = "characteristic_name")
    private String characteristicName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "characteristic")
    private List<Product_Characteristic> product_characteristics;

    public Category getCategory() {
        return category;
    }

    public List<Product_Characteristic> getProduct_characteristics() {
        return product_characteristics;
    }

    public long getCharacteristicId() {
        return characteristicId;
    }

    public String getCharacteristicName() {
        return characteristicName;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCharacteristicName(String characteristicName) {
        this.characteristicName = characteristicName;
    }

    public void setCharacteristicId(long characteristicId) {
        this.characteristicId = characteristicId;
    }

    public void setProduct_characteristics(List<Product_Characteristic> product_characteristics) {
        this.product_characteristics = product_characteristics;
    }
}
