import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        ProductCategory category = new ProductCategory("Category", "Department", "Description");
        Supplier supplier = new Supplier("Supplier", "Description");
        Product product1 = new Product("Product", 12.50f, "PLN", "Description", category, supplier);
        Product product2 = new Product("Product 2", 12.50f, "PLN", "Description", category, supplier);
        Product product3 = new Product("Product 3", 12.50f, "PLN", "Description", category, supplier);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        for(Product product : products){
            System.out.println(product.toString());
        }
    }


}
