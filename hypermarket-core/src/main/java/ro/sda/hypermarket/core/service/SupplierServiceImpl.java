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
    public Supplier getById(Long id, boolean useHibernate) {
        if (useHibernate){
            return supplierDao.getById(id);
        }
        return supplierRepository.findById(id);
    }

    @Override
    public List<Supplier> getAll(boolean useHibernate) {
        if (useHibernate){
            return supplierDao.getAll();
        }
        return supplierRepository.findAll();
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
    @Transactional
    public void updateSupplier(Supplier supplier, boolean useHibernate) {
        if(useHibernate){
            supplierDao.updateSupplier(supplier);
        }
        supplierRepository.save(supplier);
    }

    @Override
    @Transactional
    public void deleteSupplier(Supplier supplier, boolean useHibernate) {
        if (useHibernate){
            supplierDao.deleteSupplier(supplier);
        }
        supplierRepository.delete(supplier);
    }
}
