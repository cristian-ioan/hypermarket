package ro.sda.hypermarket.core.sda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.sda.hypermarket.core.entity.Product;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

    private List<Product> products = new ArrayList<>();

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Product getById(Long id) {
        return getCurrentSession().byId(Product.class).getReference(id);
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        getCurrentSession().save(product);
        return product;
    }

    @Override
    public void updateProduc(Product product) {

    }

    @Override
    public void deleteProduc(Product product) {

    }
}
