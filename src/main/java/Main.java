import com.codecool.shop.Application;
import com.codecool.shop.dao.SqliteJDBCConnector;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Product;

import java.sql.SQLException;


public class Main {

    public static void main(String[] args) {
        if(args.length > 0 && args[0].equals("--init-db")){
            System.out.println("Created new tables");
            try{
                SqliteJDBCConnector.createTables();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(args.length > 0 && args[0].equals("--migrate-db")){
            System.out.println("Drop And create");
            try{
                SqliteJDBCConnector.dropTables();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        Application.run();

    }


}
