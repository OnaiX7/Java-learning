package de.provadis.utils;

import de.provadis.model.Mitarbeiter;
import javafx.scene.control.TableCell;

import java.time.LocalDate;

public class DateCell extends TableCell<Mitarbeiter, LocalDate> {

    @Override
    protected void updateItem(LocalDate item, boolean empty) {

        if (item != null) {
            setText(DateUtil.format(item));
        }
        else {
        setText("");
        }
    }
}