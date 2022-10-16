package db;

import java.sql.*;

public class UserDB {

    /**
     * Creates a new user in the database
     *
     * @param username
     * @param password
     */

    public static void createNewUser(String username, String password) {
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
     *
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

    private static void closePreparedStatement(PreparedStatement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
