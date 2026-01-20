public abstract class Azubi implements iAzubi {
    private int id;
    private String name;

    public Azubi(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
