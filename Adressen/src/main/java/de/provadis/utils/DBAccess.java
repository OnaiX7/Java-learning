package de.provadis.utils;

import de.provadis.model.Mitarbeiter;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBAccess {

    private Connection conn = null;

    public void CloseConnection() throws SQLException {
        conn.close();
        System.out.println("Verbindung mit der der DB wurde beendet");
    }

    public DBAccess(String dbname) {
        String sTempDb = "src\\main\\resources\\de\\provadis\\" + dbname + ".db";
        String sJdbc = "jdbc:sqlite";
        String sDbUrl = sJdbc + ":" + sTempDb;

        System.out.println(sDbUrl);
        try {
            conn = DriverManager.getConnection(sDbUrl);
            System.out.println("Connection established");

            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void createTable(String tabellenName) {

        String query = "CREATE TABLE IF NOT EXISTS " + tabellenName + " (" +
                "id integer primary key autoincrement," +
                "nachname Text," +
                "vorname Text," +
                "geburtsdatum Date," +
                "plz Text," +
                "ort Text," +
                "strasse text," +
                "hausnummer text," +
                "land text," +
                "bundesland text," +
                "maid text," +
                "gehalt int," +
                "abteilung text," +
                "position text," +
                "einstellungsdatum Date," +
                "firma text)";

        System.out.println(query);

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.execute();
            conn.commit();
            System.out.println();

        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
    }

    public void insertMitarbeiter(String tabellenName, ArrayList<Mitarbeiter> liste) {

        String sql = "INSERT INTO " + tabellenName + "(vorname, nachname, geburtsdatum," +
                "plz, ort, strasse,hausnummer,land,bundesland, maID, position,gehalt," +
                "abteilung, einstellungsdatum, firma) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Mitarbeiter mitarbeiter : liste) {
            try {
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, mitarbeiter.getVorname());
                ps.setString(2, mitarbeiter.getNachname());
                ps.setString(3, mitarbeiter.getGeburtsdatum().format(formatter));
                ps.setString(4, mitarbeiter.getPlz());
                ps.setString(5, mitarbeiter.getOrt());
                ps.setString(6, mitarbeiter.getStrasse());
                ps.setString(7, mitarbeiter.getHausnummer());
                ps.setString(8, mitarbeiter.getLand());
                ps.setString(9, mitarbeiter.getBundesland());
                ps.setString(10, mitarbeiter.getMaID());
                ps.setString(11, mitarbeiter.getPosition());
                ps.setInt(12, mitarbeiter.getGehalt());
                ps.setString(13, mitarbeiter.getAbteilung());
                ps.setString(14, mitarbeiter.getEinstellungsdatum().format(formatter));
                ps.setString(15, mitarbeiter.getFirma());

                ps.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Mitarbeiter> printTable(String tabellenName) {
        ArrayList<Mitarbeiter> mitarbeiterListe = new ArrayList<>();
        String sql = "SELECT * FROM " + tabellenName;

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            ResultSetMetaData md = rs.getMetaData();
            int cols = md.getColumnCount();

            // Spaltenbreiten berechnen
            int[] columnWidths = new int[cols];
            String[] columnNames = new String[cols];

            // Spaltennamen und Mindestbreite
            for (int i = 0; i < cols; i++) {
                columnNames[i] = md.getColumnName(i + 1);
                columnWidths[i] = columnNames[i].length();
            }

            // Daten zwischenspeichern und maximale Breiten ermitteln
            ArrayList<String[]> rows = new ArrayList<>();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (rs.next()) {
                String[] row = new String[cols];
                for (int i = 0; i < cols; i++) {
                    Object value = rs.getObject(i + 1);
                    row[i] = (value != null) ? value.toString() : "NULL";
                    columnWidths[i] = Math.max(columnWidths[i], row[i].length());
                }
                rows.add(row);

                // Mitarbeiter-Objekt erstellen und zur Liste hinzufügen
                Mitarbeiter m = new Mitarbeiter();
                m.setId(rs.getInt("id"));
                m.setVorname(rs.getString("vorname"));
                m.setNachname(rs.getString("nachname"));

                String gebDatumStr = rs.getString("geburtsdatum");
                if (gebDatumStr != null) {
                    m.setGeburtsdatum(LocalDate.parse(gebDatumStr, formatter));
                }

                m.setPlz(rs.getString("plz"));
                m.setOrt(rs.getString("ort"));
                m.setStrasse(rs.getString("strasse"));
                m.setHausnummer(rs.getString("hausnummer"));
                m.setLand(rs.getString("land"));
                m.setBundesland(rs.getString("bundesland"));
                m.setMaID(rs.getString("maid"));
                m.setGehalt(rs.getInt("gehalt"));
                m.setAbteilung(rs.getString("abteilung"));
                m.setPosition(rs.getString("position"));

                String einstellungsDatumStr = rs.getString("einstellungsdatum");
                if (einstellungsDatumStr != null) {
                    m.setEinstellungsdatum(LocalDate.parse(einstellungsDatumStr, formatter));
                }

                m.setFirma(rs.getString("firma"));

                mitarbeiterListe.add(m);
            }

            // Obere Linie
            System.out.print("┌");
            for (int i = 0; i < cols; i++) {
                for (int j = 0; j < columnWidths[i] + 2; j++) {
                    System.out.print("─");
                }
                if (i < cols - 1) System.out.print("┬");
            }
            System.out.println("┐");

            // Kopfzeile
            System.out.print("│");
            for (int i = 0; i < cols; i++) {
                System.out.print(" " + padRight(columnNames[i], columnWidths[i]) + " ");
                if (i < cols - 1) System.out.print("│");
            }
            System.out.println("│");

            // Trennlinie nach Kopfzeile
            System.out.print("├");
            for (int i = 0; i < cols; i++) {
                for (int j = 0; j < columnWidths[i] + 2; j++) {
                    System.out.print("─");
                }
                if (i < cols - 1) System.out.print("┼");
            }
            System.out.println("┤");

            // Datenzeilen
            for (String[] row : rows) {
                System.out.print("│");
                for (int i = 0; i < cols; i++) {
                    System.out.print(" " + padRight(row[i], columnWidths[i]) + " ");
                    if (i < cols - 1) System.out.print("│");
                }
                System.out.println("│");
            }

            // Untere Linie
            System.out.print("└");
            for (int i = 0; i < cols; i++) {
                for (int j = 0; j < columnWidths[i] + 2; j++) {
                    System.out.print("─");
                }
                if (i < cols - 1) System.out.print("┴");
            }
            System.out.println("┘");

            System.out.println("\nGesamt: " + rows.size() + " Datensätze");

        } catch (SQLException e) {
            System.out.println("Select-Fehler: " + e.getMessage());
        }

        return mitarbeiterListe; // WICHTIG: Return hinzugefügt!
    }

    // Hilfsmethode zum Auffüllen von Strings
    private String padRight(String s, int length) {
        return String.format("%-" + length + "s", s);
    }

    public int deleteData(String tableName, String idOrAll) throws SQLException {
        int affected;

        if (idOrAll.equals("all")) {
            Statement st = conn.createStatement();
            affected = st.executeUpdate("DELETE FROM " + tableName);

            // AUTOINCREMENT-Zähler zurücksetzen -> nächste ID wieder bei 1
            st.executeUpdate("DELETE FROM sqlite_sequence WHERE name = '" + tableName + "'");

            st.close();
        } else {
            int id = Integer.parseInt(idOrAll);

            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM " + tableName + " WHERE id = ?"
            );
            ps.setInt(1, id);
            affected = ps.executeUpdate();
            ps.close();
        }

        conn.commit();
        return affected;
    }

    public void updateMitarbeiter(String tabellenName, int id, Map<String, Object> updates) {

        if (updates == null || updates.isEmpty()) {
            System.out.println("Keine Updates angegeben");
            return;
        }

        // SQL dynamisch erstellen
        StringBuilder sql = new StringBuilder("UPDATE " + tabellenName + " SET ");
        List<String> felder = new ArrayList<>();
        List<Object> werte = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            felder.add(entry.getKey() + " = ?");

            // LocalDate zu String konvertieren
            if (entry.getValue() instanceof LocalDate) {
                werte.add(((LocalDate) entry.getValue()).format(formatter));
            } else {
                werte.add(entry.getValue());
            }
        }

        sql.append(String.join(", ", felder));
        sql.append(" WHERE id = ?");

        try {
            PreparedStatement ps = conn.prepareStatement(sql.toString());

            // Werte setzen
            for (int i = 0; i < werte.size(); i++) {
                Object wert = werte.get(i);
                if (wert instanceof Integer) {
                    ps.setInt(i + 1, (Integer) wert);
                } else if (wert instanceof String) {
                    ps.setString(i + 1, (String) wert);
                } else {
                    ps.setString(i + 1, wert.toString());
                }
            }
            ps.setInt(werte.size() + 1, id);

            int affectedRows = ps.executeUpdate();
            conn.commit();
            ps.close();

            if (affectedRows > 0) {
                System.out.println("✓ Mitarbeiter ID " + id + " aktualisiert: " + updates.keySet());
            } else {
                System.out.println("✗ Kein Mitarbeiter mit ID " + id + " gefunden");
            }

        } catch (SQLException e) {
            System.out.println("Fehler beim Update: " + e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback-Fehler: " + ex.getMessage());
            }
        }
    }
}