package db;

import bo.Item;
import org.junit.jupiter.api.Test;
import ui.ItemInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ItemDBTest {

    @Test
    void addItemToCart() {
        ItemDB.addItemToCart(2, 4, 2);
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
    void lookUpShoppingChartWithUserId() {
        Collection c = ItemDB.lookUpShoppingChartWithUserId(4);
        ArrayList<ItemInfo> items = new ArrayList<ItemInfo>();

        for(Iterator it = c.iterator(); it.hasNext();) {
            Item item = (Item) it.next();
            System.out.println(item.getPrice());
        }
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