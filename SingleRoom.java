import java.io.Serializable;
import java.util.ArrayList;

class Singleroom implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    String contact;
    String gender;
    ArrayList<Food> food; // Made package-private or use methods
    private boolean needsCleaning;

    Singleroom() {
        this.name = "";
        this.contact = "";
        this.gender = "";
        this.food = new ArrayList<>();
        this.needsCleaning = false;
    }

    Singleroom(String name, String contact, String gender, boolean needsCleaning) {
        this.name = name;
        this.contact = contact;
        this.gender = gender;
        this.food = new ArrayList<>();
        this.needsCleaning = needsCleaning;
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

    public boolean isNeedsCleaning() {
        return needsCleaning;
    }

    public void setNeedsCleaning(boolean needsCleaning) {
        this.needsCleaning = needsCleaning;
    }
}