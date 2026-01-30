package de.provadis.utils;
import com.github.javafaker.Faker;
import de.provadis.model.Mitarbeiter;
import de.provadis.model.Person;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class FakeData {
    private static final Faker faker = new Faker(new Locale("de"));

    public static Person createPerson() {
        LocalDate geburtsdatum = faker.date()
                .birthday(18, 65)
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        return new Person(
                faker.name().lastName(),
                faker.name().firstName(),
                geburtsdatum,
                faker.address().zipCode(),
                faker.address().city(),
                faker.address().streetName(),
                String.valueOf(faker.number().numberBetween(1, 200)),
                "Deutschland",
                getBundesland()
        );
    }

    public static List<Person> createPersonen(int anzahl) {
        List<Person> personen = new ArrayList<>();
        for (int i = 0; i < anzahl; i++) {
            personen.add(createPerson());
        }
        return personen;
    }

    public static Mitarbeiter createMitarbeiter() {
        Person person = createPerson();

        LocalDate einstellungsdatum = faker.date()
                .past(3650, TimeUnit.DAYS)
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        if (einstellungsdatum.isBefore(person.getGeburtsdatum().plusYears(18))) {
            einstellungsdatum = person.getGeburtsdatum().plusYears(18).plusDays(1);
        }

        return new Mitarbeiter(
                person,
                "MA" + String.format("%05d", person.getId()),
                faker.number().numberBetween(30000, 90000),
                getAbteilung(),
                faker.job().position(),
                einstellungsdatum,
                faker.company().name()
        );
    }

    public static List<Mitarbeiter> createMitarbeiter(int anzahl) {
        List<Mitarbeiter> mitarbeiter = new ArrayList<>();
        for (int i = 0; i < anzahl; i++) {
            mitarbeiter.add(createMitarbeiter());
        }
        return mitarbeiter;
    }


    private static String getBundesland() {
        String[] bundeslaender = {
                "Baden-Württemberg", "Bayern", "Berlin", "Brandenburg",
                "Bremen", "Hamburg", "Hessen", "Mecklenburg-Vorpommern",
                "Niedersachsen", "Nordrhein-Westfalen", "Rheinland-Pfalz",
                "Saarland", "Sachsen", "Sachsen-Anhalt", "Schleswig-Holstein",
                "Thüringen"
        };
        return bundeslaender[faker.number().numberBetween(0, bundeslaender.length)];
    }


    private static String getAbteilung() {
        String[] abteilungen = {
                "IT", "Personalwesen", "Vertrieb", "Marketing", "Buchhaltung",
                "Einkauf", "Produktion", "Forschung & Entwicklung", "Logistik",
                "Kundenservice", "Qualitätssicherung", "Controlling"
        };
        return abteilungen[faker.number().numberBetween(0, abteilungen.length)];
    }
}
