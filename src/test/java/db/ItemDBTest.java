package db;

import bo.Item;
import org.junit.jupiter.api.Test;
import ui.ItemInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

class ItemDBTest {

    @Test
    void addItemToCart() {
        ItemDB.addItemToCart(3, 4, 6);
    }

    @Test
    void getAllItems() {
        Collection<ItemDB> c = ItemDB.getAllItems();

        for (ItemDB itemDB : c) {
            Item item = (Item) itemDB;
            System.out.println(item.getId() + " " + item.getName());
        }
    }

    @Test
    void lookUpShoppingChartWithUserId() {
        Collection<ItemDB> c = ItemDB.lookUpShoppingChartWithUserId(4);
        ArrayList<ItemInfo> items = new ArrayList<ItemInfo>();

        for (ItemDB itemDB : c) {
            Item item = (Item) itemDB;
            System.out.println(item.getId() + " " + item.getName() + " " + item.getNrOfItems());
        }
    }

    @Test
    void findItemById(){
        System.out.println(ItemDB.findItemById(1).getName());
    }

    @Test
    void nrOfItemsInCart(){

        System.out.println(ItemDB.nrOfItemsInCart(2,5));
    }

    @Test
    void createNewUser() {
        ItemDB.createNewUser("Tim", "Svensson");
    }

    @Test
    void findUserByName() throws SQLException {
        int res = ItemDB.findUserByName("Kalle", "Anka");
        System.out.println(res);
    }

    @Test
    void closePreparedStatement() {
    }

    @Test
    void searchItems() {
    }
}