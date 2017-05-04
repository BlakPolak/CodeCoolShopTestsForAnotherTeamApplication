import com.codecool.shop.controller.MainMenuController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SqliteJDBCConnector;
import com.codecool.shop.view.UserInput;

import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    private static MainMenuController mainMenuController = new MainMenuController();

    public static void main(String[] args) {
        if(args.length > 0 && args[0].equals("--create-tables")){
            System.out.println("Test");
            try{
                SqliteJDBCConnector.createTables();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        mainMenuController.mainMenuAction();

    }


}
