package bo;

import db.ItemDB;

import java.util.Collection;


public class Item {
    private int id;
    private String name;
    private int price;

    static public Collection searchItems(String group) {
        return ItemDB.searchItems(group);
    }

    protected Item(int id , String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

