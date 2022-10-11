package bo;

import ui.ItemInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ItemHandler {


    public static Collection<ItemInfo> getShoppingCartItems(int userId) {
        Collection c = Item.getShoppingCartItems(userId);
        ArrayList<ItemInfo> items = new ArrayList<ItemInfo>();
        System.out.println("Inne i get Shopping ");

        for (Iterator it = c.iterator(); it.hasNext(); ) {
            Item item = (Item) it.next();
            items.add(new ItemInfo(item.getName(), item.getId()));
        }
        return items;
    }

    public static void addItemToCart(int itemId, int userId){

        Item.addItemToCart(itemId, userId);
    }

    public static void createNewUser(String username, String password){
        Item.createNewUser(username, password);
    }

    public static int findUserByName(String username, String password){
        return Item.findUserByName(username, password);
    }
}
