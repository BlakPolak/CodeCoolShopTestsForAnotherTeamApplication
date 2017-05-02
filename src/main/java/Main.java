import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.SqliteJDBCConnector;

import java.sql.SQLException;


public class Main {

    public static void main(String[] args) {
        if(args.length > 0 && args[0].equals("--create-tables")){
            System.out.println("Test");
            try{
                SqliteJDBCConnector.createTables();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
        ProductController productController = new ProductController();
        productController.displayList();
    }


}
