package ro.sda.hypermarket.core.sda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.sda.hypermarket.core.entity.SaleProduct;

import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class SaleProductDaoImpl implements SaleProductDao {

    private List<SaleProduct> saleProducts = new ArrayList<>();

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public SaleProduct getById(Long id) {
        return getCurrentSession().byId(SaleProduct.class).getReference(id);
    }

    @Override
    public List<SaleProduct> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaQuery<SaleProduct> criteriaQuery = session.getCriteriaBuilder().createQuery(SaleProduct.class);
        criteriaQuery.from(SaleProduct.class);
        List<SaleProduct> allSaleProducts = session.createQuery(criteriaQuery).getResultList();
        return allSaleProducts;
    }

    @Override
    public SaleProduct createSaleProduct(SaleProduct saleProduct) {
        getCurrentSession().save(saleProduct);
        return saleProduct;
    }

    @Override
    public SaleProduct updateSaleProduct(SaleProduct saleProduct) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        SaleProduct supplier1 = getById(saleProduct.getId());
        sessionFactory.getCurrentSession().merge(supplier1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();
        return saleProduct;
    }

    @Override
    public void deleteSaleProduct(SaleProduct saleProduct) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        SaleProduct saleProduct1 = getById(saleProduct.getId());
        sessionFactory.getCurrentSession().delete(saleProduct1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();
    }
}
