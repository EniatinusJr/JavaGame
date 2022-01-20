package ch.bbw.zork;

public class Item {

    private String name;
    private String itemDescription;
    private int weight;
    private Tag tag;

    public Item(String name, String itemDescription, int weight, Tag tag) {
        this.name = name;
        this.itemDescription = itemDescription;
        this.weight = weight;
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
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public Tag getTag() {
        return tag;
    }
    public void setTag(Tag tag) {
        this.tag = tag;
    }

}
