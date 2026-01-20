public class Chemikant extends Azubi {

    public Chemikant(int id, String name) {
        super(id, name);
    }

    @Override
    public void doAbschlussProjekt() {
        IO.println("Chemikant: " + getName());
    }
}