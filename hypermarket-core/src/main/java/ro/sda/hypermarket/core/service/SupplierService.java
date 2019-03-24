package ro.sda.hypermarket.core.service;

import org.hibernate.Session;
import ro.sda.hypermarket.core.entity.Supplier;

import java.util.List;

public interface SupplierService {

    Supplier getById(Long id, boolean useHibernate);
    List<Supplier> getAll(boolean useHibernate);
    Supplier createSupplier(Supplier supplier, boolean useHibernate);
    void updateSupplier(Supplier supplier, boolean useHibernate);
    void deleteSupplier(Supplier supplier, boolean useHibernate);

}
