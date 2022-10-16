package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbManager {
    private static DbManager instance = null;
    private Connection con = null;

    private DbManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Distribuerade_System", "root", "password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to establish a connection with the database
     *
     * @return
     */

    public static Connection getConnection() {
        return getInstance().con;
    }

    private static DbManager getInstance() {
        if (instance == null)
            instance = new DbManager();
        return instance;
    }
}
