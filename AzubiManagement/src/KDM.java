public class KDM extends Azubi {

    public KDM(int id, String name) {
        super(id, name);
    }

    @Override
    public void doAbschlussProjekt() {
        IO.println("KDM: " + getName());
    }
}