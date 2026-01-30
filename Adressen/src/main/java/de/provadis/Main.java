package de.provadis;

import de.provadis.model.Mitarbeiter;
import de.provadis.model.Person;
import de.provadis.utils.DBAccess;
import de.provadis.utils.FakeData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException {

//        List<Person> personen = FakeData.createPersonen(5);
//        personen.forEach(System.out::println);
//        System.out.println();
//
        List<Mitarbeiter> mitarbeiterListe = FakeData.createMitarbeiter(5);
//        mitarbeiterListe.forEach(System.out::println);
        DBAccess db = new DBAccess("adressen");
//        db.createTable("mitarbeiter");
//        db.insertMitarbeiter("mitarbeiter", (ArrayList<Mitarbeiter>) mitarbeiterListe);

        db.updateMitarbeiter("mitarbeiter",1, Map.of("gehalt",115000));

            db.printTable("mitarbeiter");
//            db.deleteData("mitarbeiter", "all");
            db.CloseConnection();
    }
}