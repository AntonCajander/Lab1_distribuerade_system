package db;

import java.sql.*;

import java.util.Vector;
import java.util.Collection;

public class ItemDB extends bo.Item {
    //TODO Ändra i databasen så att en användare endast har userId, username och password
    //TODO Hitta user med username och password, returnera id om den finns annars -1.

    private ItemDB(int id, String name) {
        super(id, name);
    }

    public static void addItemToCart(int itemId, int userId, int nrOfItems){ //TODO Ta bort nrOfItems
        PreparedStatement insertStatement = null;

        try {
            Connection con = DbManager.getConnection();
            insertStatement = con.prepareStatement("insert into ShoppingCart values(?,?,?)");

            insertStatement.setInt(1, userId);
            insertStatement.setInt(2, itemId);
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

    /**
     * Takes a userId and returns a collection with the items in that users shoppingCart
     * @param userId
     * @return Collection with items
     */

    public static Collection<ItemDB> lookUpShoppingChartWithUserId(int userId) {
        Vector<ItemDB> v = new Vector<>();
        try {
            Connection con = DbManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from ShoppingCart where userId = " + userId);
            while (rs.next()) {
                int id = rs.getInt("itemId");
                String name = rs.getString("name");
                v.addElement(new ItemDB(id, name));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return v;
    }

    public static void createNewUser(String username, String password){ //TODO bytt till username och password flytta till ny klass userDB
        PreparedStatement insertStatement = null;

        try {
            Connection con = DbManager.getConnection();
            insertStatement = con.prepareStatement("INSERT INTO `distribuerade_system`.`user` (`username`, `password`) VALUES (?, ?);");

            insertStatement.setString(1, username);
            insertStatement.setString(2, password);

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

    public static int findUserByName(String username, String password) throws SQLException { //TODO FUNKAR INTE
        int nbrOfUsers = 0;
        try {
            Connection con = DbManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select userId from User where username = " + username + " && password = " + password);

            while(rs.next()){
                nbrOfUsers++;
            }
            if (nbrOfUsers == 1){
                return rs.getInt(0);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public static void closePreparedStatement(PreparedStatement statement){
        try{
            statement.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

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
}

