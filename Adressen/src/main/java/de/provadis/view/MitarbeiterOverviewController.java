package de.provadis.view;

import de.provadis.MainApp;
import de.provadis.model.Mitarbeiter;
import de.provadis.utils.*;
import de.provadis.utils.DateCell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.LocalDate;

public class MitarbeiterOverviewController {

    @FXML
    private Button bNew;

    @FXML
    private Button bEdit;

    @FXML
    private Button bDelete;

    @FXML
    private Label lNachname;

    @FXML
    private Label lVorname;

    @FXML
    private Label lGeburtsdatum;
    @FXML
    private Label lStrasse;

    @FXML
    private Label lPLZ;

    @FXML
    private Label lOrt;

    @FXML
    private Label lHausnummer;
    @FXML
    private Label lLand;

    @FXML
    private Label lBundesland;

    @FXML
    private Label lGehalt;

    @FXML
    private Label lAbteilung;

    @FXML
    private Label lPosition;

    @FXML
    private Label lfirma;

    @FXML
    private Label lEinstellungsdatum;

    @FXML
    private TableView<Mitarbeiter> tvMitarbeiter;

    @FXML
    private TableColumn<Mitarbeiter, Number> tcId;

    @FXML
    private TableColumn<Mitarbeiter, String> tcNachname;

    @FXML
    private TableColumn<Mitarbeiter, String> tcVorname;

    @FXML
    private TableColumn<Mitarbeiter, LocalDate> tcGeburtsdatum;

    @FXML
    private TableColumn<Mitarbeiter, Number> tcGehalt;

    private Mitarbeiter editMitarbeiter = null;

    @FXML
    public void initialize() {
        tcId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        tcVorname.setCellValueFactory(cellData -> cellData.getValue().vornameProperty());
        tcNachname.setCellValueFactory(cellData -> cellData.getValue().nachnameProperty());
        tcGeburtsdatum.setCellValueFactory(cellData -> cellData.getValue().geburtsdatumProperty());
        tcGehalt.setCellValueFactory(cellData -> cellData.getValue().gehaltProperty());

        tcGeburtsdatum.setCellFactory(e-> {
            return new DateCell();
        });
        tcGehalt.setCellFactory(e-> {
            return new GehaltCell();
        });

        showMitarbeiterDetail(null);
        tvMitarbeiter.getSelectionModel().selectedItemProperty()
                .addListener((observableValue, oldvalue, newValue) -> {showMitarbeiterDetail(newValue);}
        );
    }

    MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        tvMitarbeiter.setItems(mainApp.getMitarbeiterData());
    }
    public void showMitarbeiterDetail(Mitarbeiter _m) {
        if (_m != null) {
            lNachname.setText(_m.getNachname());
            lVorname.setText(_m.getVorname());
            lGeburtsdatum.setText(DateUtil.format(_m.getGeburtsdatum()));
            lStrasse.setText(_m.getStrasse());
            lHausnummer.setText(_m.getHausnummer());
            lOrt.setText(_m.getOrt());
            lPLZ.setText(_m.getPlz());
            lLand.setText(_m.getLand());
            lBundesland.setText(_m.getBundesland());
            lGehalt.setText(formatNumber.format(_m.getGehalt()) + " €");
            lAbteilung.setText(_m.getAbteilung());
            lPosition.setText(_m.getPosition());
            lfirma.setText(_m.getFirma());
            lEinstellungsdatum.setText(DateUtil.format(_m.getEinstellungsdatum()));
        } else {
            lNachname.setText("");
            lVorname.setText("");
            lGeburtsdatum.setText("");
            lStrasse.setText("");
            lHausnummer.setText("");
            lOrt.setText("");
            lPLZ.setText("");
            lLand.setText("");
            lBundesland.setText("");
            lGehalt.setText("");
            lAbteilung.setText("");
            lPosition.setText("");
            lfirma.setText("");
            lEinstellungsdatum.setText("");
        }
    }

    @FXML
    private void handleEditMitarbeiter(ActionEvent event) {
        Mitarbeiter selectedPerson = tvMitarbeiter.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showEditMitarbeiterDialog(selectedPerson, "Editieren");
            if (okClicked) {
                showMitarbeiterDetail(selectedPerson);
                //ToDo
                //Update des Mitarbeiters in der Datenbank
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Keine Auswahl getroffen");
            alert.setHeaderText("Bitte wählen Sie eine Person aus");
            alert.setContentText("Bitte wählen Sie eine Person aus");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewMitarbeiter(ActionEvent event) {
        Mitarbeiter tempMitarbeiter = new Mitarbeiter();
        boolean ok = mainApp.showEditMitarbeiterDialog(tempMitarbeiter,"Neu anlegen.");
        if (ok) {
            mainApp.getMitarbeiterData().add(tempMitarbeiter);
        }
    }

    @FXML
    private void handleDeleteMitarbeiter(ActionEvent event) throws SQLException {
        int selectedIndex = tvMitarbeiter.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            System.out.println(this.editMitarbeiter.getNachname()+ " " +this.editMitarbeiter.getId());
            DBAccess db = new DBAccess("testdaten");
            db.deleteData("adressen", ""+ this.editMitarbeiter.getId());
            tvMitarbeiter.getItems().remove(selectedIndex);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Keine Auswahl getroffen");
            alert.setHeaderText("Bitte wählen Sie eine Person aus");
            alert.setContentText("Bitte wählen Sie eine Person aus");

            alert.showAndWait();
        }
    }
}