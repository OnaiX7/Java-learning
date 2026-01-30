package de.provadis;

import de.provadis.model.Mitarbeiter;
import de.provadis.utils.DBAccess;
import de.provadis.view.EditMitarbeiterController;
import de.provadis.view.MitarbeiterOverviewController;
import de.provadis.view.RootLayoutController;
import de.provadis.view.buehneController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainApp extends Application {

    private ObservableList<Mitarbeiter> mitarbeiterListe = FXCollections.observableArrayList();
    BorderPane rootLayout;


    public static void main(String[] args) {
        launch(args);
    }
    Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        erstelleKomponenten();
        showViewMitarbeiter();
    }

    public MainApp() {
        readNewDB();
    }

    public void readNewDB() {
        DBAccess db = new DBAccess("adressen");
        ArrayList<Mitarbeiter> liste = db.printTable("mitarbeiter");
        mitarbeiterListe.addAll(liste);
    }

    public ObservableList<Mitarbeiter> getMitarbeiterData(){
        return mitarbeiterListe;
    }

    public void erstelleKomponenten() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/rootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();
        Scene scene = new Scene(rootLayout);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Adressen");
        primaryStage.show();
        RootLayoutController controller = loader.getController();
        controller.setMainApp(this);
    }

    public void showViewMitarbeiter() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/mitarbeiterOverview.fxml"));
        AnchorPane mitarbeitende = (AnchorPane) loader.load();
        Scene scene = new Scene(mitarbeitende);
        rootLayout.setCenter(mitarbeitende);
        MitarbeiterOverviewController controller = loader.getController();
        controller.setMainApp(this);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public boolean showEditMitarbeiterDialog(Mitarbeiter m,String title) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MitarbeiterEditDialog.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            Stage dialog = new Stage();
            dialog.setTitle(title);
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.initOwner(primaryStage);
            Scene scene = new Scene(pane);
            dialog.setScene(scene);

            EditMitarbeiterController controller = loader.getController();
            controller.setDialog(dialog);
            controller.setMitarbeiter(m);

            dialog.showAndWait();
            return controller.isOkClicked();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return false;
        }
    }
}
