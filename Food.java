import java.io.Serializable;

class Food implements Serializable {
    private static final long serialVersionUID = 1L; // Recommended for Serializable classes
    int itemno;
    int quantity;
    float price;

    Food(int itemno, int quantity) {
        this.itemno = itemno;
        this.quantity = quantity;
        switch (itemno) {
            case 1: price = quantity * 50; break;
            case 2: price = quantity * 60; break;
            case 3: price = quantity * 70; break;
            case 4: price = quantity * 30; break;
            default: price = 0; // Handle invalid item number
        }
    }

    public int getItemno() {
        return itemno;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Item No: " + itemno + ", Quantity: " + quantity + ", Price: " + String.format("%.2f", price);
    }
}