package bo;

public class UserHandler {

    public static int findUserByName(String username, String password) {
        if (username != "" && password != "") {
            int id;
            id = Item.findUserByName(username, password);
            return id;
        } else {
            System.out.println("Username or password was empty");
            return -1;
        }
    }

    public static void createNewUser(String username, String password) {
        Item.createNewUser(username, password);
    }
}
