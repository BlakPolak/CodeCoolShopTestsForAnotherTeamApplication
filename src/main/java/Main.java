import com.codecool.shop.controller.ProductController;

import java.sql.SQLException;


public class Main {

    public static void main(String[] args) {
        if(args.length > 0 && args[0].equals("--create-tables")){
            try{

            }catch (SQLException e){}
        }
        ProductController productController = new ProductController();
        productController.displayList();
    }


}
