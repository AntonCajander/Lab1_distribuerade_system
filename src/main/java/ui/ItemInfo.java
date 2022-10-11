package ui;

public class ItemInfo {
    private String name;
    private int itemId;

    public ItemInfo(String name, int itemId) {
        this.name = name;
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId){
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
