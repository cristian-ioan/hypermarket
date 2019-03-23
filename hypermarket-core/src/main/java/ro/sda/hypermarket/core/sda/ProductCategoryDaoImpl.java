package ro.sda.hypermarket.core.sda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.sda.hypermarket.core.entity.ProductCategory;

import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ProductCategoryDaoImpl implements ProductCategoryDao{

    private List<ProductCategory> productCategories = new ArrayList<>();

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public ProductCategory getById(Long id) {
        return getCurrentSession().byId(ProductCategory.class).getReference(id);
    }

    @Override
    public List<ProductCategory> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaQuery<ProductCategory> criteriaQuery = session.getCriteriaBuilder().createQuery(ProductCategory.class);
        criteriaQuery.from(ProductCategory.class);
        List<ProductCategory> allProductCategories = session.createQuery(criteriaQuery).getResultList();
        return allProductCategories;
    }

    @Override
    public ProductCategory createProductCategory(ProductCategory productCategory) {
        getCurrentSession().save(productCategory);
        return productCategory;
    }

    @Override
    public ProductCategory updateProductCategory(ProductCategory productCategory) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        ProductCategory productCategory1 = getById(productCategory.getId());
        sessionFactory.getCurrentSession().merge(productCategory1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();
        return productCategory;
    }

    @Override
    public void deleteProductCategory(ProductCategory productCategory) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        ProductCategory productCategory1 = getById(productCategory.getId());
        sessionFactory.getCurrentSession().delete(productCategory1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();
    }
}
