package ro.sda.hypermarket.core.service;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import ro.sda.hypermarket.core.entity.Supplier;
import ro.sda.hypermarket.core.repository.SupplierRepository;
import ro.sda.hypermarket.core.sda.SupplierDao;

import java.util.List;

@Service("supplierService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SupplierServiceImpl implements SupplierService{

    @Autowired
    private SupplierDao supplierDao;

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public Supplier getById(Long id) {
        return supplierDao.getById(id);
    }

    @Override
    public List<Supplier> getAll() {
        return supplierDao.getAll();
    }

    @Override
    @Transactional
    public Supplier createSupplier(Supplier supplier, boolean useHibernate) {
        if (useHibernate){
            return supplierDao.createSupplier(supplier);
        }
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Supplier supplier)
    {
        return supplierDao.updateSupplier(supplier);
    }

    @Override
    public void deleteSupplier(Supplier supplier) {
        supplierDao.deleteSupplier(supplier);
    }
}
