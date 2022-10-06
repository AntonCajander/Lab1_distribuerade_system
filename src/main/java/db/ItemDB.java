package db;

import java.sql.*;

import java.util.Vector;
import java.util.Collection;

public class ItemDB extends bo.Item {

    public static Collection searchItems(String group) { //TODO FEL QUERY
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

    public static void addItemToCart(int itemId, int userId, int nrOfItems){ //TODO Lösning för nrOfItems, den måste ju veta hur många items man redan har, men just nu kör jag att man får skriva in det
        PreparedStatement insertStatement = null;

        try {
            Connection con = DbManager.getConnection();
            insertStatement = con.prepareStatement("insert into ShoppingCart values(?,?,?)");

            insertStatement.setInt(1, itemId);
            insertStatement.setInt(2, userId);
            insertStatement.setInt(3, nrOfItems);

            insertStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if(insertStatement != null){
                closePreparedStatement(insertStatement);
            }
        }
    }

    public static void closePreparedStatement(PreparedStatement statement){
        try{
            statement.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}

