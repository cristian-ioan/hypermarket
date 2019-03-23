package ro.sda.hypermarket.core.sda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.sda.hypermarket.core.entity.Sale;

import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class SaleDaoImpl implements SaleDao {

    private List<Sale> sales = new ArrayList<>();

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Sale getById(Long id) {
        return getCurrentSession().byId(Sale.class).getReference(id);
    }

    @Override
    public List<Sale> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaQuery<Sale> criteriaQuery = session.getCriteriaBuilder().createQuery(Sale.class);
        criteriaQuery.from(Sale.class);
        List<Sale> allSales = session.createQuery(criteriaQuery).getResultList();
        return allSales;
    }

    @Override
    public Sale createSale(Sale sale) {
        getCurrentSession().save(sale);
        return sale;
    }

    @Override
    public Sale updateSale(Sale sale) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        Sale sale1 = getById(sale.getId());
        sessionFactory.getCurrentSession().merge(sale1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();
        return sale;
    }

    @Override
    public void deleteSale(Sale sale) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        Sale supplier1 = getById(sale.getId());
        sessionFactory.getCurrentSession().delete(supplier1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();
    }
}
