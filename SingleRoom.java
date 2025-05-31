import java.io.Serializable;
import java.util.ArrayList;

class Singleroom implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    String contact;
    String gender;
    ArrayList<Food> food; // Made package-private or use methods

    Singleroom() {
        this.name = "";
        this.contact = "";
        this.gender = "";
        this.food = new ArrayList<>();
    }

    Singleroom(String name, String contact, String gender) {
        this.name = name;
        this.contact = contact;
        this.gender = gender;
        this.food = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Food> getFoodOrders() {
        return food;
    }

    public void addFoodOrder(Food newFood) {
        if (this.food == null) {
            this.food = new ArrayList<>();
        }
        this.food.add(newFood);
    }
}