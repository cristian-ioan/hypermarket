package ro.sda.hypermarket.core.service;
import ro.sda.hypermarket.core.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory getById(Long id, boolean useHibernate);
    List<ProductCategory> getAll(boolean useHibernate);
    ProductCategory createProductCategory(ProductCategory productCategory, boolean useHibernate);
    void updateProductCategory(ProductCategory productCategory, boolean useHibernate);
    void deleteProductCategory(ProductCategory productCategory, boolean useHibernate);
}
