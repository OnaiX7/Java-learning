public class Fisi extends Azubi {

    public Fisi(int id, String name) {
        super(id, name);
    }

    @Override
    public void doAbschlussProjekt() {
        IO.println("Fisi: " + getName());
    }
}