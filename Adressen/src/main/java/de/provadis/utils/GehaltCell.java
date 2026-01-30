package de.provadis.utils;

import de.provadis.model.Mitarbeiter;
import javafx.scene.control.TableCell;

public class GehaltCell extends TableCell<Mitarbeiter, Number> {

    @Override
    protected void updateItem(Number item, boolean empty) {
        super.updateItem(item, empty); // ⚠️ WICHTIG: super aufrufen!

        if (empty || item == null) {
            setText("");
        } else {
            setText(formatNumber.format(item.intValue()) + " €");
        }
    }
}