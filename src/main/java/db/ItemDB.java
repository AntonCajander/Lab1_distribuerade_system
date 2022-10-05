package db;

import java.sql.*;

import java.util.Vector;
import java.util.Collection;

public class ItemDB extends bo.Item {
    public static Collection searchItems(String group) {
        Vector v = new Vector();
        try {


            Connection con = DbManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select id, name from Item where item_group = " + group);
            while (rs.next()) {
                int i = rs.getInt("item_id");
                String name = rs.getString("name");
                v.addElement(new ItemDB(i, name));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return v;
    }
    private ItemDB(int id, String name) {
        super(id, name);
    }
}

