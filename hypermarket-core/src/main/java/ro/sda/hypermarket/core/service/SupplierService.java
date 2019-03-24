package ro.sda.hypermarket.core.service;

import org.hibernate.Session;
import ro.sda.hypermarket.core.entity.Supplier;

import java.util.List;

public interface SupplierService {

    Supplier getById(Long id);
    List<Supplier> getAll();
    Supplier createSupplier(Supplier supplier, boolean useHibernate);
    Supplier updateSupplier(Supplier supplier);
    void deleteSupplier(Supplier supplier);

}
