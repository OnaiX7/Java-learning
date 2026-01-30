package de.provadis.view;
import de.provadis.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;

public class RootLayoutController {

    MainApp mainApp;

    @FXML
    private MenuItem miClose;

    @FXML
    void handleClose(ActionEvent event) {
        System.exit(0);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }

    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("AddressApp");
        alert.setHeaderText("About");
        alert.setContentText("Author: Provadis GmbH");

        alert.showAndWait();
    }

}