package ro.sda.hypermarket.core.service;

import ro.sda.hypermarket.core.entity.Product;

import java.util.List;

public interface ProductService {

    Product getById(Long id, boolean useHibernate);
    List<Product> getAll(boolean useHibernate);
    Product createProduct(Product product, boolean useHibernate);
    void updateProduct(Product product, boolean useHibernate);
    void deleteProduct(Product product, boolean useHibernate);
}
