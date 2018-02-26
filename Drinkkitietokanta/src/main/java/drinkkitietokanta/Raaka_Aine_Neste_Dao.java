package drinkkitietokanta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Raaka_Aine_Neste_Dao {

    private Database database;

    public Raaka_Aine_Neste_Dao(Database database) {
        this.database = database;
    }

    public Raaka_aine_neste findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Raaka_aine_neste> findAll() throws SQLException {
        List<Raaka_aine_neste> nesteet = new ArrayList<>();

        try (Connection conn = database.getConnection();
                ResultSet result = conn.prepareStatement("SELECT * FROM Raaka_aine_neste").executeQuery()) {

            while (result.next()) {
                nesteet.add(new Raaka_aine_neste(result.getInt("Raaka_aine_neste_id"), result.getString("Nimi"), result.getInt("Maara"), result.getDouble("Hinta"), result.getDouble("Alkoholipitoisuus")));
            }
        }

        return nesteet;
    }

    public Raaka_aine_neste saveOrUpdate(Raaka_aine_neste object) throws SQLException {
        // simply support saving -- disallow saving if task with
        // same name exists
        Raaka_aine_neste byName = findByName(object.getNimi());

        if (byName != null) {
            return byName;
        }

        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO RAAKA_AINE_NESTE (Nimi, Maara, Hinta, Alkoholipitoisuus) VALUES (?, ?, ?, ?)");
            stmt.setString(1, object.getNimi());
            stmt.setInt(2, object.getMaara());
            stmt.setDouble(3, object.getHinta());
            stmt.setDouble(4, object.getAlkoholipitoisuus());

            stmt.executeUpdate();
        }

        return findByName(object.getNimi());
    }

    private Raaka_aine_neste findByName(String name) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM RAAKA_AINE_NESTE WHERE nimi = ?");
            stmt.setString(1, name);

            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return null;
            }

            return new Raaka_aine_neste(
                    result.getInt("Raaka_aine_neste_id"), 
                    result.getString("Nimi"), 
                    result.getInt("Maara"), 
                    result.getDouble("Hinta"), 
                    result.getDouble("Alkoholipitoisuus"));
        }
    }

    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
