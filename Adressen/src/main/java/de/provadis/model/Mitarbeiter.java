package de.provadis.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Mitarbeiter extends Person{

    private SimpleStringProperty maID;
    private SimpleIntegerProperty gehalt;
    private SimpleStringProperty abteilung;
    private SimpleStringProperty position;
    private SimpleObjectProperty<LocalDate> einstellungsdatum;
    private SimpleStringProperty firma;

    public Mitarbeiter() {
        super();
        maID = new SimpleStringProperty();
        gehalt = new SimpleIntegerProperty();
        abteilung = new SimpleStringProperty();
        position = new SimpleStringProperty();
        firma = new SimpleStringProperty();
        einstellungsdatum = new SimpleObjectProperty<LocalDate>();
    }

    public Mitarbeiter(String nachname, String vorname, LocalDate geburtsdatum,
                       String plz, String ort, String strasse, String hausnummer, String land,
                       String bundesland,
                       String maID,
                       int  gehalt,
                       String abteilung,
                       String position,
                       LocalDate einstellungsdatum,
                       String  firma) {

        super(nachname, vorname, geburtsdatum, plz, ort, strasse, hausnummer, land, bundesland);
        this.maID = new SimpleStringProperty(maID);
        this.gehalt = new SimpleIntegerProperty(gehalt);
        this.abteilung = new SimpleStringProperty(abteilung);
        this.position = new SimpleStringProperty(position);
        this.einstellungsdatum = new SimpleObjectProperty<LocalDate>(einstellungsdatum);
        this.firma = new SimpleStringProperty(firma);
    }

    public Mitarbeiter(Person person,
                       String maID,
                       int  gehalt,
                       String abteilung,
                       String position,
                       LocalDate einstellungsdatum,
                       String  firma) {
        super(person.getNachname(),
                person.getVorname(),
                person.getGeburtsdatum(),
                person.getPlz(),
                person.getOrt(),
                person.getStrasse(),
                person.getHausnummer(),
                person.getLand(),
                person.getBundesland());
        this.maID = new SimpleStringProperty(maID);
        this.gehalt = new SimpleIntegerProperty(gehalt);
        this.abteilung = new SimpleStringProperty(abteilung);
        this.position = new SimpleStringProperty(position);
        this.einstellungsdatum = new SimpleObjectProperty<LocalDate>(einstellungsdatum);
        this.firma = new SimpleStringProperty(firma);
    }

    public String getMaID() {
        return maID.get();
    }

    public SimpleStringProperty maIDProperty() {
        return maID;
    }

    public void setMaID(String maID) {
        this.maID.set(maID);
    }

    public int getGehalt() {
        return gehalt.get();
    }

    public SimpleIntegerProperty gehaltProperty() {
        return gehalt;
    }

    public void setGehalt(int gehalt) {
        this.gehalt.set(gehalt);
    }

    public String getAbteilung() {
        return abteilung.get();
    }

    public SimpleStringProperty abteilungProperty() {
        return abteilung;
    }

    public void setAbteilung(String abteilung) {
        this.abteilung.set(abteilung);
    }

    public String getPosition() {
        return position.get();
    }

    public SimpleStringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public LocalDate getEinstellungsdatum() {
        return einstellungsdatum.get();
    }

    public SimpleObjectProperty<LocalDate> einstellungsdatumProperty() {
        return einstellungsdatum;
    }

    public void setEinstellungsdatum(LocalDate einstellungsdatum) {
        this.einstellungsdatum.set(einstellungsdatum);
    }

    public String getFirma() {
        return firma.get();
    }

    public SimpleStringProperty firmaProperty() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma.set(firma);
    }

    @Override
    public String toString() {
        return "Mitarbeiter{" +
                "maID=" + maID +
                ", Person=" + super.toString() +
                ", gehalt=" + gehalt +
                ", abteilung=" + abteilung +
                ", position=" + position +
                ", einstellungsdatum=" + einstellungsdatum +
                ", firma=" + firma +
                '}';
    }
}
