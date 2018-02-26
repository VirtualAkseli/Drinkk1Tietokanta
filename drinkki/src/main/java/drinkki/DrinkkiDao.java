package drinkki;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DrinkkiDao {
    private Database database;

    public DrinkkiDao(Database database) {
        this.database = database;
    }
    
        public Drinkki findOne(Integer key) throws SQLException, Exception {
        try (Connection conn = database.getConnection()) {
            PreparedStatement s = conn.prepareStatement("SELECT * FROM Drinkki WHERE Drinkki_id = ?");
            s.setInt(1, key);

            ResultSet result = s.executeQuery();
            if (!result.next()) {
                return null;
            }

            return new Drinkki(
                    result.getInt("Drinkki_id"),
                    result.getString("Nimi"),
                    result.getString("Lasityyppi"),
                    result.getString("Resepti"));
        }
    }

    public List<Drinkki> findAll() throws SQLException, Exception {
        List<Drinkki> drinkit = new ArrayList<>();

        try (Connection conn = database.getConnection();
                ResultSet result = conn.prepareStatement("SELECT * FROM Drinkki").executeQuery()) {

            while (result.next()) {
                drinkit.add(new Drinkki(result.getInt("Drinkki_id"), result.getString("Nimi"), result.getString("Lasityyppi"), result.getString("Resepti")));
            }
        }

        return drinkit;
    }

    public Drinkki saveOrUpdate(Drinkki object) throws SQLException, Exception {
        Drinkki byName = findByName(object.getNimi());

        if (byName != null) {
            return byName;
        }

        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO DRINKKI (Nimi, Lasityyppi, Resepti) VALUES (?, ?, ?)");
            stmt.setString(1, object.getNimi());
            stmt.setString(2, object.getLasityyppi());
            stmt.setString(3, object.getResepti());

            stmt.executeUpdate();
        }

        return findByName(object.getNimi());
    }

    private Drinkki findByName(String name) throws SQLException, Exception {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Drinkki WHERE nimi = ?");
            stmt.setString(1, name);

            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return null;
            }

            return new Drinkki(
                    result.getInt("Drinkki_id"),
                    result.getString("Nimi"),
                    result.getString("Lasityyppi"),
                    result.getString("Resepti"));
        }
    }
}
