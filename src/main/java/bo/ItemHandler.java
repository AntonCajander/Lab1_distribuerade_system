package bo;

import db.ItemDB;
import ui.ItemInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ItemHandler {

    public static void addItemToCart(int itemId, int userId){
        Item.addItemToCart(itemId, userId);
    }

    public static Collection<ItemInfo> getShoppingCartItems(int userId) {
        Collection<ItemDB> itemDBCollection = Item.getShoppingCartItems(userId);
        ArrayList<ItemInfo> itemInfoList = new ArrayList<>();

        for (Iterator it = itemDBCollection.iterator(); it.hasNext(); ) {
            Item item = (Item) it.next();
            itemInfoList.add(new ItemInfo(item.getName(), item.getId(), item.getNrOfItems()));
        }
        return itemInfoList;
    }

    public static void createNewUser(String username, String password){
        Item.createNewUser(username, password);
    }

    public static Collection<ItemInfo> getAllItems() {
        Collection<ItemDB> itemDBCollection = Item.getAllItems();
        ArrayList<ItemInfo> items = new ArrayList<>();

        for (Iterator it = itemDBCollection.iterator(); it.hasNext(); ) {
            Item item = (Item) it.next();
            items.add(new ItemInfo(item.getName(), item.getId()));
        }
        return items;
    }
}
