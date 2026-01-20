public class AE extends Azubi {

    public AE(int id, String name) {
        super(id, name);
    }

    @Override
    public void doAbschlussProjekt() {
    IO.println("AE: "+ getName());
    }
}
