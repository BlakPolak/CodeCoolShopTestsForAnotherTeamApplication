import com.codecool.shop.Application;
import com.codecool.shop.dao.SqliteJDBCConnector;

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
        else if(args.length > 0 && args[0].equals("--migrate-db")){
            System.out.println("Drop And create");
            try{
                SqliteJDBCConnector.dropTables();
            }catch (SQLException e){
                e.printStackTrace();
            }
        } else if(args.length > 0){
            throw new IllegalArgumentException("Invalid program argument");
        }
        Application.run();
    }
}
