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

    static public Collection searchItems(String group) {
        return ItemDB.searchItems(group);
    }

    public static void addItemToCart(int itemId, int userId, int nrOfItems){
       ItemDB.addItemToCart(itemId, userId, nrOfItems);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

}

