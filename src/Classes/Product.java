package Classes;

import Enums.ProductTypes;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
    private long id;
    private String name;
    private Date date;
    private ProductTypes type;
    private int price;

    public Product() {
        type = ProductTypes.IPHONE;
        name = type.getName();
        date = new Date();
        price = type.getPrice();

    }

    public Product(ProductTypes type) {
        this.type = type;
        id = this.type.getId();
        name = type.getName();
        date = new Date();
        price = type.getPrice();

    }

    public Product(long id, String name, Date date, ProductTypes type, int price) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.type = type;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ProductTypes getType() {
        return type;
    }

    public void setType(ProductTypes type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "id: " + id + " name: " + name + " date: " + date + " price: " + price;
    }
}
