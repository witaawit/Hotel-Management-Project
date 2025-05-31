import java.io.Serializable;

class Holder implements Serializable {
    private static final long serialVersionUID = 1L;
    Doubleroom luxury_doublerrom[] = new Doubleroom[10]; // Luxury rooms 1-10
    Doubleroom deluxe_doublerrom[] = new Doubleroom[20]; // Deluxe rooms 11-30
    Singleroom luxury_singleerrom[] = new Singleroom[10]; // Luxury rooms 31-40
    Singleroom deluxe_singleerrom[] = new Singleroom[20]; // Deluxe rooms 41-60

    public Holder() {
        // Arrays are initialized to null by default
    }
}