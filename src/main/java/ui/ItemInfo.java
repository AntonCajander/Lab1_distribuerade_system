package ui;

public class ItemInfo {
    private String name;
    private int itemId;
    private int nrOfItems;

    public ItemInfo(String name, int itemId) {
        this.name = name;
        this.itemId = itemId;
    }

    public ItemInfo(String name, int itemId, int nrOfItems) {
        this.name = name;
        this.itemId = itemId;
        this.nrOfItems = nrOfItems;
    }

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public int getNrOfItems() {
        return nrOfItems;
    }

}
