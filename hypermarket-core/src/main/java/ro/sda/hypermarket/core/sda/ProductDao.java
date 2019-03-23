package ro.sda.hypermarket.core.sda;

import org.hibernate.Session;
import ro.sda.hypermarket.core.entity.Product;

import java.util.List;

public interface ProductDao {

    Session getCurrentSession();
    Product getById(Long id);
    List<Product> getAll();
    Product createProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Product product);

}
