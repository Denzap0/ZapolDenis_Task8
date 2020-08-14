package Main;

import Classes.Shop;
import Exceptions.ChooseException;
import Exceptions.DeficiencyAmountOfMoneyException;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.addCustomer();
        try {
            shop.console();
        } catch (ChooseException | DeficiencyAmountOfMoneyException e) {
            e.printStackTrace();
        }

    }
}
