package ro.sda.hypermarket.core.sda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.sda.hypermarket.core.entity.Product;

import javax.persistence.criteria.CriteriaQuery;
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
        Session session = sessionFactory.getCurrentSession();
        CriteriaQuery<Product> criteriaQuery = session.getCriteriaBuilder().createQuery(Product.class);
        criteriaQuery.from(Product.class);
        List<Product> allProducts = session.createQuery(criteriaQuery).getResultList();
        return allProducts;
    }

    @Override
    public Product createProduct(Product product) {
        getCurrentSession().save(product);
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        Product product1 = getById(product.getId());
        sessionFactory.getCurrentSession().merge(product1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();
        return product;
    }

    @Override
    public void deleteProduct(Product product) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        Product product1 = getById(product.getId());
        sessionFactory.getCurrentSession().delete(product1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();

    }
}
