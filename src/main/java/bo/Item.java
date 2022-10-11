package bo;

import db.ItemDB;

import java.util.Collection;


public class Item {
    private int id;
    private String name;
    private int nrOfItems;

    /**
     * Protected constructor with nrOfItems, used in the shopping cart to display how many of each item the user has
     * @param id
     * @param name
     * @param nrOfItems
     */

    protected Item(int id , String name, int nrOfItems) {
        this.id = id;
        this.name = name;
        this.nrOfItems = nrOfItems;
    }

    /**
     * Protected constructor without nrOfItems, used when nrOfItems is not wanted
     * @param id
     * @param name
     */

    protected Item(int id , String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Method to get the items in a particular users shopping cart
     * @param userId
     * @return
     */

    static public Collection getShoppingCartItems(int userId) {
        return ItemDB.lookUpShoppingChartWithUserId(userId);
    }

    /**
     * Method to add an item to a users shopping cart
     * @param itemId
     * @param userId
     */

    public static void addItemToCart(int itemId, int userId){
       ItemDB.addItemToCart(itemId, userId);
    }

    /**
     * Method to create a new user, used when registering a new user
     * @param username
     * @param password
     */

    public static void createNewUser(String username, String password){
        ItemDB.createNewUser(username, password);
    }

    /**
     * Method that gets a userId if given the username and password of a user
     * @param username
     * @param password
     * @return
     */

    public static int findUserByName(String username, String password){
        return ItemDB.findUserByName(username, password);
    }

    public String getName() {
        return name;
    }

    public int getNrOfItems() {
        return nrOfItems;
    }

    public int getId(){
        return id;
    }
    static public Collection getAllItems() {
        return ItemDB.getAllItems();
    }
}

