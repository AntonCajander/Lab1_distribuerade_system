package db;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ItemDBTest {

    @Test
    void addItemToCart() {
    }

    @Test
    void lookUpShoppingChartWithUserId() {
    }

    @Test
    void createNewUser() {
        ItemDB.createNewUser("Kalle", "Anka");
    }

    @Test
    void findUserByName() throws SQLException {
        int res = ItemDB.findUserByName("Kalle", "Anka");
        System.out.println(res);
    }

    @Test
    void closePreparedStatement() {
    }

    @Test
    void searchItems() {
    }
}