package drinkki;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public static Connection getConnection() throws Exception {
    String dbUrl = System.getenv("JDBC_DATABASE_URL");
    if (dbUrl != null && dbUrl.length() > 0) {
        return DriverManager.getConnection(dbUrl);
    }

    return DriverManager.getConnection("jdbc:sqlite:drinkkitietokanta.db");
}
    
    


    
}

