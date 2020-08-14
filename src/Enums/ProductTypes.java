package Enums;

public enum ProductTypes {
    IPHONE("Iphone", 1, 1000),
    HUAWEI("Huawei", 2, 400),
    NOKIA("Nokia", 3, 100),
    SAMSUNG("Samsung", 4, 600),
    SONY("Sony", 5, 500),
    XIAOMI("Xiaomi", 6, 200);

    private String name;
    private long id;
    private int price;

    ProductTypes() {

    }

    ProductTypes(String name, long id, int price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
