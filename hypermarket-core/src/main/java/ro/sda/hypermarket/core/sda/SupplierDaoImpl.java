package ro.sda.hypermarket.core.sda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.sda.hypermarket.core.entity.Supplier;

import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class SupplierDaoImpl implements SupplierDao {

    private List<Supplier> suppliers = new ArrayList<>();

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Supplier getById(Long id) {
        return getCurrentSession().byId(Supplier.class).getReference(id);
    }

    @Override
    public List<Supplier> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaQuery<Supplier> criteriaQueryq = session.getCriteriaBuilder().createQuery(Supplier.class);
        criteriaQueryq.from(Supplier.class);
        List<Supplier> allSuppliers = session.createQuery(criteriaQueryq).getResultList();
        return allSuppliers;
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        getCurrentSession().save(supplier);
        return supplier;
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    @Override
    public void deleteSupplier(Supplier supplier) {
        getCurrentSession().delete(supplier);
        getCurrentSession().flush();
    }
}
