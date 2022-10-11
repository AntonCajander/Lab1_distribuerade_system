package bo;

import db.ItemDB;

import java.util.Collection;


public class Item {
    private int id;
    private String name;
    private int price;

    protected Item(int id , String name) {
        this.id = id;
        this.name = name;
    }

    static public Collection getShoppingCartItems(int userId) {
        return ItemDB.lookUpShoppingChartWithUserId(userId);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static void addItemToCart(int itemId, int userId, int nrOfItems){
       ItemDB.addItemToCart(itemId, userId, nrOfItems);
    }

    public int getId(){
        return id;
    }

    public static void addItemToCart(int itemId, int userId){
       ItemDB.addItemToCart(itemId, userId);
    }

    public static void createNewUser(String username, String password){
        ItemDB.createNewUser(username, password);
    }

    public static int findUserByName(String username, String password){
        return ItemDB.findUserByName(username, password);
    }
}

