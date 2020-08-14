package Classes;

import java.util.ArrayList;

public class Customer {

    private String id;
    private int cash;
    private ArrayList<Product> boughtProducts = new ArrayList<>();

    public Customer(String id, int cash) {
        this.id = id;
        this.cash = cash;
    }

    public void buy(int price) {
        cash -= price;
    }

    public void boughtProductsAdd(Product product) {
        boughtProducts.add(product);
    }

    public Product serialize() {
        return boughtProducts.get(boughtProducts.size() - 1);
    }

    public void boughtProductsNames() {
        for (Product p : boughtProducts) {
            System.out.print(p.getName() + " ");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public ArrayList<Product> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(ArrayList<Product> ownProducts) {
        this.boughtProducts = ownProducts;
    }
}
