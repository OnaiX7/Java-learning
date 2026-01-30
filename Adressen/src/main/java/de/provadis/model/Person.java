package de.provadis.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Person {

    private SimpleIntegerProperty id;
    private SimpleStringProperty nachname;
    private SimpleStringProperty vorname;
    private SimpleObjectProperty<LocalDate> geburtsdatum;
    private SimpleStringProperty plz;
    private SimpleStringProperty ort;
    private SimpleStringProperty strasse;
    private SimpleStringProperty hausnummer;
    private SimpleStringProperty land;
    private SimpleStringProperty bundesland;

    public Person() {
        id = new SimpleIntegerProperty();
        nachname = new SimpleStringProperty();
        vorname = new SimpleStringProperty();
        geburtsdatum = new SimpleObjectProperty<>();
        plz = new SimpleStringProperty();
        ort = new SimpleStringProperty();
        strasse = new SimpleStringProperty();
        hausnummer = new SimpleStringProperty();
        land = new SimpleStringProperty();
        bundesland = new SimpleStringProperty();
    }

    public Person(String nachname,
                  String vorname,
                  LocalDate geburtsdatum,
                  String plz,
                  String ort,
                  String strasse,
                  String hausnummer,
                  String land,
                  String bundesland) {
        this.id = new SimpleIntegerProperty();
        this.nachname =     new SimpleStringProperty(nachname);
        this.vorname =      new SimpleStringProperty(vorname);
        this.geburtsdatum = new SimpleObjectProperty<LocalDate>(geburtsdatum);
        this.plz =          new SimpleStringProperty(plz);
        this.ort =          new SimpleStringProperty(ort);
        this.strasse =      new SimpleStringProperty(strasse);
        this.hausnummer =   new SimpleStringProperty(hausnummer);
        this.land =         new SimpleStringProperty(land);
        this.bundesland =   new SimpleStringProperty(bundesland);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNachname() {
        return nachname.get();
    }

    public SimpleStringProperty nachnameProperty() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname.set(nachname);
    }

    public String getVorname() {
        return vorname.get();
    }

    public SimpleStringProperty vornameProperty() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname.set(vorname);
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum.get();
    }

    public SimpleObjectProperty<LocalDate> geburtsdatumProperty() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum.set(geburtsdatum);
    }

    public String getPlz() {
        return plz.get();
    }

    public SimpleStringProperty plzProperty() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz.set(plz);
    }

    public String getOrt() {
        return ort.get();
    }

    public SimpleStringProperty ortProperty() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort.set(ort);
    }

    public String getStrasse() {
        return strasse.get();
    }

    public SimpleStringProperty strasseProperty() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse.set(strasse);
    }

    public String getHausnummer() {
        return hausnummer.get();
    }

    public SimpleStringProperty hausnummerProperty() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer.set(hausnummer);
    }

    public String getLand() {
        return land.get();
    }

    public SimpleStringProperty landProperty() {
        return land;
    }

    public void setLand(String land) {
        this.land.set(land);
    }

    public String getBundesland() {
        return bundesland.get();
    }

    public SimpleStringProperty bundeslandProperty() {
        return bundesland;
    }

    public void setBundesland(String bundesland) {
        this.bundesland.set(bundesland);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", nachname=" + nachname +
                ", vorname=" + vorname +
                ", geburtsdatum=" + geburtsdatum +
                ", plz=" + plz +
                ", ort=" + ort +
                ", strasse=" + strasse +
                ", hausnummer=" + hausnummer +
                ", land=" + land +
                ", bundesland=" + bundesland +
                '}';
    }
}
