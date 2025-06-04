class Doubleroom extends Singleroom{
    private static final long serialVersionUID = 1L;
    String name2;
    String contact2;
    String gender2;

    Doubleroom() {
        super();
        this.name2 = "";
        this.contact2 = "";
        this.gender2 = "";
    }

    Doubleroom(String name, String contact, String gender, boolean needsCleaning, String name2, String contact2, String gender2) {
        super(name, contact, gender, needsCleaning);
        this.name2 = name2;
        this.contact2 = contact2;
        this.gender2 = gender2;
    }

    public String getName2() {
        return name2;
    }
}