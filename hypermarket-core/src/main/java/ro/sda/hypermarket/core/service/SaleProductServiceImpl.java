package ro.sda.hypermarket.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.entity.SaleProduct;
import ro.sda.hypermarket.core.repository.SaleProductRepository;
import ro.sda.hypermarket.core.sda.SaleProductDao;

import java.util.List;

@Service("saleProductService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SaleProductServiceImpl implements SaleProductService{

    @Autowired
    private SaleProductDao saleProductDao;

    @Autowired
    private SaleProductRepository saleProductRepository;

    @Override
    public SaleProduct getById(Long id, boolean useHibernate) {
        if (useHibernate){
            return saleProductDao.getById(id);
        }
        return saleProductRepository.findById(id);
    }

    @Override
    public List<SaleProduct> getAll(boolean useHibernate) {
        if (useHibernate){
            return saleProductDao.getAll();
        }
        return saleProductRepository.findAll();
    }

    @Override
    @Transactional
    public SaleProduct createSaleProduct(SaleProduct saleProduct, boolean useHibernate) {
        if (useHibernate){
            return saleProductDao.createSaleProduct(saleProduct);
        }
        return saleProductRepository.save(saleProduct);
    }

    @Override
    @Transactional
    public SaleProduct updateSaleProduct(SaleProduct saleProduct, boolean useHibernate) {
        if(useHibernate){
            return saleProductDao.updateSaleProduct(saleProduct);
        }
        return saleProductRepository.save(saleProduct);
    }

    @Override
    @Transactional
    public void deleteSaleProduct(SaleProduct saleProduct, boolean useHibernate) {
        if (useHibernate){
            saleProductDao.deleteSaleProduct(saleProduct);
        }
        saleProductRepository.delete(saleProduct);
    }
}
