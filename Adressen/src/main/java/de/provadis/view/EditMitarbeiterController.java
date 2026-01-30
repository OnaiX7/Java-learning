package de.provadis.view;

import de.provadis.model.Mitarbeiter;
import de.provadis.utils.DateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditMitarbeiterController {

    @FXML
    TextField tfNachname;

    @FXML
    TextField tfVorname;

    @FXML
    private TextField tfStrasse;

    @FXML
    private TextField tfPlz;

    @FXML
    private TextField tfOrt;

    @FXML
    private TextField tfGeburtstag;

    @FXML
    private TextField tfHausnummer;

    @FXML
    private TextField tfLand;

    @FXML
    private TextField tfBundesland;

    private Stage dialog;
    private Mitarbeiter mitarbeiter;
    private boolean okClicked = false;


    @FXML
    public void handelEdit(ActionEvent event) {

        okClicked = true;
        dialog.close();
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setDialog(Stage dialog) {
        this.dialog = dialog;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;

        tfVorname.setText(mitarbeiter.getVorname());
        tfNachname.setText(mitarbeiter.getNachname());
        tfGeburtstag.setText(DateUtil.format(mitarbeiter.getGeburtsdatum()));
        tfGeburtstag.setPromptText("dd.mm.yyyy");
        tfPlz.setText(mitarbeiter.getPlz());
        tfOrt.setText(mitarbeiter.getOrt());
        tfHausnummer.setText(mitarbeiter.getHausnummer());
        tfLand.setText(mitarbeiter.getLand());
        tfBundesland.setText(mitarbeiter.getBundesland());
        tfStrasse.setText(mitarbeiter.getStrasse());
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        dialog.close();
    }

    @FXML
    void handleOK(ActionEvent event) {
        //if (isInputValid()) {
        mitarbeiter.setGeburtsdatum(DateUtil.parse(tfGeburtstag.getText()));
        mitarbeiter.setNachname(tfNachname.getText());
        mitarbeiter.setVorname(tfVorname.getText());
        mitarbeiter.setStrasse(tfStrasse.getText());
        mitarbeiter.setPlz(tfPlz.getText());
        mitarbeiter.setOrt(tfOrt.getText());
        mitarbeiter.setHausnummer(tfHausnummer.getText());
        mitarbeiter.setLand(tfLand.getText());
        mitarbeiter.setBundesland(tfBundesland.getText());

        okClicked = true;
        dialog.close();
        //}
    }

}
