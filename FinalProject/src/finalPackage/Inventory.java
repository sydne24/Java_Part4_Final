package finalPackage;

import java.util.ArrayList;
import java.util.List;

public class Inventory
{
    List<Inventory.Item> items;
    
    public Inventory() {
        this.items = new ArrayList<Inventory.Item>();
    }
    
    public class Item
    {
        private String name;
        private String description;
        private int quantity;
        
        public Item(String name, int quantity, String description) {
            this.name = name;
            this.quantity = quantity;
            this.description = description;
        }
        
        public String getName() {
            return name;
        }
        
        public int getQuantity() {
            return quantity;
        }
        
        public String getDescription() {
            return description;
        }
        
        public void addItem() {
            items.add(this);
        }
        
        public void removeItem() {
            items.remove(this);
        }
    }

    
    public void addItem(String itemName, int quantity, String description) {
        this.items.add(new Inventory.Item(itemName, quantity, description));
    }
    
    public void getItems() {
        System.out.println("Inventory:");
        if (items.isEmpty()) {
            System.out.println("You have nothing in your inventory");
        }
        for (Inventory.Item item : items) {
            System.out.println(item.getQuantity() + " " + item.getName());
        }
    }
    
    public boolean containsItem(Inventory.Item item) {
        boolean conItem = false;
        if (this.items.contains(item)) {
            conItem = true;
        }
        return conItem;
    }
}