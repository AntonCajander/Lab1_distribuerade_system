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

    public static void addItemToCart(int itemId, int userId){
        PreparedStatement insertStatement = null;

        try {
            Connection con = DbManager.getConnection();
            insertStatement = con.prepareStatement("insert into ShoppingCart values(?,?)");

            insertStatement.setInt(1, userId);
            insertStatement.setInt(2, itemId);

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
            ResultSet rs = st.executeQuery("select itemId from ShoppingCart where userId = " + "'" +  userId + "'");

            while(rs.next()){
                Statement st2 = con.createStatement();
                ResultSet rs2 = st2.executeQuery("select * from Item where itemId = " + "'" +  rs.getInt("itemId") + "'");

                while(rs2.next()){
                    System.out.println("Resultat ");
                    int id = rs2.getInt("itemId");
                    String name = rs2.getString("name");
                    v.addElement(new ItemDB(id, name));
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return v;
    }

    /**
     * Gives you the name of an item given the id
     * @param itemId
     * @return
     */

    public static ItemDB findItemById(int itemId){
        ItemDB result = null;
        try {
            Connection con = DbManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Item where itemId = " + "'" + itemId + "'");
            if (rs.next()){
                result = new ItemDB(rs.getInt("itemId"), rs.getString("name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public static void createNewUser(String username, String password){ //TODO flytta till ny klass userDB
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

    public static int findUserByName(String username, String password){
        try {
            Connection con = DbManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT userId FROM distribuerade_system.user WHERE username = " + "'" + username + "'" + " AND password = " + "'" + password + "'");

            if (rs.next()){
                return rs.getInt("userId");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    private static void closePreparedStatement(PreparedStatement statement){
        try{
            statement.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}

