package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {
    private static DbManager instance = null;
    private Connection con = null;

    private DbManager(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Distribuerade_System", "root", "password");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return getInstance().con;
    }

    private static DbManager getInstance(){
        if (instance == null)
            instance = new DbManager();
        return instance;
    }

    public void disconnect(){
        try {
            if (this.con != null) {
                con.close();
                this.con = null;
            }else throw new SQLException("Could not disconnect from database");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
