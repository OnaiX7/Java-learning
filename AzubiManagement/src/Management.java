import java.util.ArrayList;
import java.util.List;

public class Management {

        public void doManangement()  {
            List<iAzubi> azubis = new ArrayList<>();
            azubis.add(new AE(1, "Amin"));
            azubis.add(new AE(2, "Ole"));
            azubis.add(new Fisi(1, "Baran"));
            azubis.add(new KDM(1, "Tomoyuki"));
            azubis.add(new Chemikant(1,"Justus"));

            for(iAzubi element : azubis) {
                element.doAbschlussProjekt();
            }
        }
}