package ro.sda.hypermarket.core.sda;

import org.hibernate.Session;
import ro.sda.hypermarket.core.entity.Employee;
import ro.sda.hypermarket.core.entity.ProductCategory;

import java.util.List;

public class ProductCategoryDaoImpl implements ProductCategoryDao{


    @Override
    public Session getCurrentSession() {
        return null;
    }

    @Override
    public ProductCategory getById(Long id) {
        return null;
    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }

    @Override
    public ProductCategory createProductCategory(ProductCategory productCategory) {
        return null;
    }

//        public ProductCategory createProductCategory(Long id, ProductCategory productCategory) {
//            Employee employee = getCurrentSession().load(Employee.class, id);
//            productCategory.setManagerId(employee);
//            getCurrentSession().save(productCategory);
//            return productCategory;
//        }



    @Override
    public ProductCategory updateProductCategory(ProductCategory productCategory) {
        return null;
    }

    @Override
    public void deleteProductCategory(ProductCategory productCategory) {

    }
}
