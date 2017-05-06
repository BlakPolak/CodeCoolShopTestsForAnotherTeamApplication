import com.codecool.shop.controller.MainMenuController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductDaoSqlite;
import com.codecool.shop.dao.SqliteJDBCConnector;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        if (args.length > 0 && args[0].equals("--create-table")) {
            try {
                SqliteJDBCConnector.createTables();
            } catch (SQLException e) {
                System.out.println("Cannot create tables in DB");
                System.out.println(e.getMessage());
            }
        }

        MainMenuController mainMenu = new MainMenuController();
        mainMenu.mainMenuAction();


    }


}
