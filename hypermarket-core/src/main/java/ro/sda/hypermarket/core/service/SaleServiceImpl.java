package ro.sda.hypermarket.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.entity.Sale;
import ro.sda.hypermarket.core.repository.SaleRepository;
import ro.sda.hypermarket.core.sda.SaleDao;

import java.util.List;

@Service("saleService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SaleServiceImpl implements SaleService{

    @Autowired
    private SaleDao saleDao;

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public Sale getById(Long id, boolean useHibernate) {
        if (useHibernate){
            return saleDao.getById(id);
        }
        return saleRepository.findById(id);
    }

    @Override
    public List<Sale> getAll(boolean useHibernate) {
        if (useHibernate){
            return saleDao.getAll();
        }
        return saleRepository.findAll();
    }

    @Override
    @Transactional
    public Sale createSale(Sale sale, boolean useHibernate) {
        if (useHibernate){
            return saleDao.createSale(sale);
        }
        return saleRepository.save(sale);
    }

    @Override
    @Transactional
    public void updateSale(Sale sale, boolean useHibernate) {
        if(useHibernate){
            saleDao.updateSale(sale);
        }
        saleRepository.save(sale);
    }

    @Override
    @Transactional
    public void deleteSale(Sale sale, boolean useHibernate) {
        if (useHibernate){
            saleDao.deleteSale(sale);
        }
        saleRepository.delete(sale);
    }
}
