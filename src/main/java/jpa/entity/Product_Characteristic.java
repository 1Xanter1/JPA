package jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Product_characteristics")
public class Product_Characteristic {
    @Id
    @Column(name = "product_characteristic_id")
    private long productCharacteristicId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "characteristic_id")
    private Characteristic characteristic;

    @Column(name = "value")
    private String value;

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public long getProductCharacteristicId() {
        return productCharacteristicId;
    }

    public Product getProduct() {
        return product;
    }

    public String getValue() {
        return value;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    public void setProductCharacteristicId(long productCharacteristicId) {
        this.productCharacteristicId = productCharacteristicId;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
