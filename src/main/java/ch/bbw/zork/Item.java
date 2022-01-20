package ch.bbw.zork;

public class Item {

    private String name;
    private String itemDescription;
    private Tag tag;

    public Item(String name, String itemDescription, Tag tag) {
        this.name = name;
        this.itemDescription = itemDescription;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getItemDescription() {
        return itemDescription;
    }
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
    public Tag getTag() {
        return tag;
    }
    public void setTag(Tag tag) {
        this.tag = tag;
    }

}
