package bo;

public class ItemHandler {

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
