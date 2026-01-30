package de.provadis.view;

import de.provadis.MainApp;
import de.provadis.model.Mitarbeiter;
import de.provadis.utils.DateCell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;

import java.time.LocalDate;

public class buehneController {

    @FXML
    private TextField tfEingabe1;

    @FXML
    private TextField tfEingabe2;

    @FXML
    private Button bAbschicken;

    @FXML
    void handleAbschicken(ActionEvent event) {

        tfEingabe2.setText(tfEingabe1.getText());
    }

    @FXML
    public void initialize() {
        circle1.radiusProperty().bind(slider1.valueProperty());
        tcId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        tcVorname.setCellValueFactory(cellData -> cellData.getValue().vornameProperty());
        tcNachname.setCellValueFactory(cellData -> cellData.getValue().nachnameProperty());
        tcGeburtstag.setCellValueFactory(cellData -> cellData.getValue().geburtsdatumProperty());

        tcGeburtstag.setCellFactory(e-> {
            return new DateCell();
        });
    }

    @FXML
    private Circle circle1;

    @FXML
    private Slider slider1;

    @FXML
    private TableView<Mitarbeiter> tvDaten;

    @FXML
    private TableColumn<Mitarbeiter, Number> tcId;

    @FXML
    private TableColumn<Mitarbeiter, String> tcNachname;

    @FXML
    private TableColumn<Mitarbeiter, String> tcVorname;

    @FXML
    private TableColumn<Mitarbeiter, LocalDate> tcGeburtstag;

    MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        tvDaten.setItems(mainApp.getMitarbeiterData());
    }
}
