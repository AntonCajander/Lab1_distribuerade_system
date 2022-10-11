package db;

import java.sql.*;

import java.util.Vector;
import java.util.Collection;

public class ItemDB extends bo.Item {

    /**
     * Constructor with the variable nrOfItems which is used to see how many of a particular item a certain user has
     * @param id
     * @param name
     * @param nrOfItems
     */

    private ItemDB(int id, String name, int nrOfItems) {
        super(id, name, nrOfItems);
    }

    /**
     * Constructor without nrOfItems
     * @param id
     * @param name
     */

    private ItemDB(int id, String name) {
        super(id, name);
    }

    /**
     * Checks to see if the user already has that item in the shopping cart, if yes. Execute an update statement
     * that increase the nr of that item. If not, execute insert statement with the item and the amount of that item.
     *
     * @param itemId
     * @param userId
     */
    public static void addItemToCart(int itemId, int userId) {
        PreparedStatement statement = null;
        int nrOfExistingItems = nrOfItemsInCart(itemId, userId);

        try {
            Connection con = DbManager.getConnection();
            if (nrOfExistingItems == -1) {
                statement = con.prepareStatement("insert into ShoppingCart values(?,?,?)");

                statement.setInt(1, userId);
                statement.setInt(2, itemId);
                statement.setInt(3, 1);

            } else {
                statement = con.prepareStatement("UPDATE `Distribuerade_System`.`ShoppingCart` SET `nrOfItems` = (?) WHERE (`userId` = (?)) and (`itemId` = (?));");

                statement.setInt(1, nrOfExistingItems + 1);
                statement.setInt(2, userId);
                statement.setInt(3, itemId);

            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                closePreparedStatement(statement);
            }
        }
    }

    /**
     * Returns a vector containing every item in the store
     * @return vector
     */

    public static Collection<ItemDB> getAllItems() {
        Vector<ItemDB> v = new Vector<>();
        try {
            Connection con = DbManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Item");
            while(rs.next()){
                int id = rs.getInt("itemId");
                String name = rs.getString("name");
                v.addElement(new ItemDB(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    /**
     * Checks if a particular user has a particular item in his shopping cart, if yes. Return the amount
     * of that item. Otherwise, return -1.
     *
     * @param itemId
     * @param userId
     * @return
     */
    private static int nrOfItemsInCart(int itemId, int userId) {
        try {
            Connection con = DbManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select nrOfItems from ShoppingCart where userId = " + "'" + userId + "'" + " AND itemId = " + "'" + itemId + "'");
            if (rs.next()) {
                return rs.getInt("nrOfItems");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Takes a userId and returns a collection with the items in that users shoppingCart
     *
     * @param userId
     * @return Collection with items
     */

    public static Collection<ItemDB> lookUpShoppingChartWithUserId(int userId) {
        Vector<ItemDB> v = new Vector<>();
        try {
            Connection con = DbManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select itemId, nrOfItems from ShoppingCart where userId = " + "'" +  userId + "'");

            while(rs.next()){
                Statement st2 = con.createStatement();
                ResultSet rs2 = st2.executeQuery("select * from Item where itemId = " + "'" +  rs.getInt("itemId") + "'");

                while(rs2.next()){
                    int id = rs2.getInt("itemId");
                    String name = rs2.getString("name");
                    int nrOfItems = rs.getInt("nrOfItems");
                    v.addElement(new ItemDB(id, name, nrOfItems));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    /**
     * Gives you the name of an item given the id
     *
     * @param itemId
     * @return itemDB
     */

    public static ItemDB findItemById(int itemId) {
        ItemDB result = null;
        try {
            Connection con = DbManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Item where itemId = " + "'" + itemId + "'");
            if (rs.next()) {
                result = new ItemDB(rs.getInt("itemId"), rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Creates a new user in the database
     * @param username
     * @param password
     */

    public static void createNewUser(String username, String password) { //TODO flytta till ny klass userDB
        PreparedStatement insertStatement = null;

        try {
            Connection con = DbManager.getConnection();
            insertStatement = con.prepareStatement("INSERT INTO `distribuerade_system`.`user` (`username`, `password`) VALUES (?, ?);");

            insertStatement.setString(1, username);
            insertStatement.setString(2, password);

            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (insertStatement != null) {
                closePreparedStatement(insertStatement);
            }
        }
    }

    /**
     * Get the id of a user given the username and password
     * @param username
     * @param password
     * @return
     */

    public static int findUserByName(String username, String password) {
        try {
            Connection con = DbManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT userId FROM distribuerade_system.user WHERE username = " + "'" + username + "'" + " AND password = " + "'" + password + "'");

            if (rs.next()) {
                return rs.getInt("userId");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Used to close the prepared statement
     * @param statement
     */

    private static void closePreparedStatement(PreparedStatement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

