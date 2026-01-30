module Adressen {
    requires java.sql;
    requires javafaker;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires commons.lang3;

    opens de.provadis to javafx.fxml;
    exports de.provadis;
    exports de.provadis.utils;

    opens de.provadis.utils to javafx.fxml;
    opens de.provadis.view to javafx.fxml;
}