package Classes;

import Enums.ProductTypes;
import Exceptions.ChooseException;
import Exceptions.DeficiencyAmountOfMoneyException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Shop {

    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Customer> clientsBase = new ArrayList<>();

    public Shop() {
        products.add(new Product(ProductTypes.IPHONE));
        products.add(new Product(ProductTypes.HUAWEI));
        products.add(new Product(ProductTypes.NOKIA));
        products.add(new Product(ProductTypes.SAMSUNG));
        products.add(new Product(ProductTypes.SONY));
        products.add(new Product(ProductTypes.XIAOMI));
    }

    public void console() throws ChooseException, DeficiencyAmountOfMoneyException {
        System.out.println("Hello dear client, write your id");
        Scanner sc = new Scanner(System.in);
        String curId;
        int indexOfCustomer = 0;
        boolean check = false;
        while (true) {
            curId = sc.nextLine();
            for (Customer c : clientsBase) {
                if (c.getId().equals(curId)) {
                    check = true;
                    indexOfCustomer = clientsBase.indexOf(c);
                    break;
                }
            }
            if (check) {
                System.out.println("You log in successfully\n");
                break;
            } else {
                System.out.println("User with that id did not registered, try again");
            }
        }
        check = true;
        boolean saveIntoFile = false;
        while (check) {
            System.out.println("Mobile store");
            System.out.println("Menu:");
            System.out.println("1.Products catalog");
            System.out.println("2.Buy product");
            System.out.println("3.Bought products");
            System.out.println("4.Save your bought products into file");
            System.out.println("5.Serialize your products");
            System.out.println("6.Get your serialized products");
            System.out.println("7.Quit");
            short res = sc.nextShort();
            switch (res) {
                case 1 -> {
                    int i = 1;
                    for (Product p : products) {
                        System.out.println("id " + p.getId() + ": " + p.getName());
                    }
                }
                case 2 -> {
                    System.out.println("Write id of product to buy");
                    boolean checkId = false;
                    int productInd = 0;
                    while (!checkId) {
                        long chooseId = sc.nextLong();
                        for (Product p : products) {
                            if (p.getId() == chooseId) {
                                checkId = true;
                                productInd = products.indexOf(p);
                            }
                        }
                        if (!checkId) {
                            System.out.println("There is no this id, try again");
                        }
                    }
                    if (products.get(productInd).getPrice() <= clientsBase.get(indexOfCustomer).getCash()) {
                        clientsBase.get(indexOfCustomer).buy(products.get(productInd).getPrice());
                        clientsBase.get(indexOfCustomer).boughtProductsAdd(products.get(productInd));
                        System.out.println("You bought a new product");
                    } else {
                        throw new DeficiencyAmountOfMoneyException("You dont have enough of money("
                                + products.get(productInd).getPrice() + ") to buy this product");
                    }
                    System.out.println("Thank you for your order!");
                }
                case 3 -> {
                    System.out.println("Your products:");
                    clientsBase.get(indexOfCustomer).boughtProductsNames();
                    System.out.println();
                }
                case 4 -> {
                    try (OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream("src\\Files\\orders.txt"))) {
                        for (Product p : clientsBase.get(indexOfCustomer).getBoughtProducts()) {
                            fout.write(p.getName() + " ");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("All your products will be saved into file");
                }
                case 5 -> {
                    try (ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream("src\\Files\\objects.txt"))) {
                        oOut.writeObject(clientsBase.get(indexOfCustomer).getBoughtProducts());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("You has serialized your bought products");
                }
                case 6 -> {
                    ArrayList<Product> serializedProducts = new ArrayList<>();
                    try (ObjectInputStream oIn = new ObjectInputStream(new FileInputStream("src\\Files\\objects.txt"))) {
                        serializedProducts = ((ArrayList<Product>) oIn.readObject());
                        System.out.println("Your serialized products:");
                        for (Product p : serializedProducts) {
                            System.out.println(p.toString());
                        }

                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                case 7 -> {
                    System.out.println("Good bye");
                    check = false;
                }
                default -> {
                    System.out.println("Your choice not from the list, try again");
                    throw new ChooseException("Your choose (" + res + ") is more than 4 or lower than 1");
                }
            }
        }

    }

    public void addCustomer() {
        System.out.println("Registration:");
        Scanner sc = new Scanner(System.in);
        System.out.println("Write your id");
        String id = sc.nextLine();
        System.out.println(id);
        boolean check = true;
        for (Customer c : clientsBase) {
            if (c.getId().equals(id)) {
                check = false;
                break;
            }
        }
        if (!check) {
            System.out.println("Client with that id already registered, write new id");
            id = sc.nextLine();
            check = true;
            for (Customer c : clientsBase) {
                if (c.getId().equals(id)) {
                    check = false;
                }
            }
        }
        if (check) {
            System.out.println("Write your cash");
            int cash = sc.nextInt();
            clientsBase.add(new Customer(id, cash));
            System.out.println("You has registered");
        } else {
            System.out.println("Your id is bad again");
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Customer> getClientsBase() {
        return clientsBase;
    }

    public void setClientsBase(ArrayList<Customer> clientsBase) {
        this.clientsBase = clientsBase;
    }
}
