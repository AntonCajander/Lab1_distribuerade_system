package bo;

import ui.ItemInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ItemHandler {

    public static void addItemToCart(int itemId, int userId){
        Item.addItemToCart(itemId, userId);
    }

    public static Collection<ItemInfo> getShoppingCartItems(int userId) {
        Collection c = Item.getShoppingCartItems(userId);
        ArrayList<ItemInfo> items = new ArrayList<ItemInfo>();

        for (Iterator it = c.iterator(); it.hasNext(); ) {
            Item item = (Item) it.next();
            items.add(new ItemInfo(item.getName(), item.getId(), item.getNrOfItems()));
        }
        return items;
    }

    public static void createNewUser(String username, String password){
        Item.createNewUser(username, password);
    }



    public static Collection<ItemInfo> getAllItems() {
        Collection c = Item.getAllItems();
        ArrayList<ItemInfo> items = new ArrayList<ItemInfo>();

        for (Iterator it = c.iterator(); it.hasNext(); ) {
            Item item = (Item) it.next();
            items.add(new ItemInfo(item.getName(), item.getId()));
        }
        return items;
    }
}
