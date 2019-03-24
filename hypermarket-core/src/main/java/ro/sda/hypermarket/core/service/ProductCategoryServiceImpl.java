package ro.sda.hypermarket.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.entity.ProductCategory;
import ro.sda.hypermarket.core.repository.ProductCategoryRepository;
import ro.sda.hypermarket.core.sda.ProductCategoryDao;
import ro.sda.hypermarket.core.service.ProductCategoryService;

import java.util.List;

@Service("productCategoryService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory getById(Long id, boolean useHibernate) {
        if (useHibernate){
            return productCategoryDao.getById(id);
        }
        return productCategoryRepository.findById(id);
    }


    @Override
    public List<ProductCategory> getAll(boolean useHibernate) {
        if (useHibernate){
            return productCategoryDao.getAll();
        }
        return productCategoryRepository.findAll();
    }

    @Override
    @Transactional
    public ProductCategory createProductCategory(ProductCategory productCategory, boolean useHibernate) {
        if (useHibernate){
            return productCategoryDao.createProductCategory(productCategory);
        }
        return productCategoryRepository.save(productCategory);
    }

    @Override
    @Transactional
    public void updateProductCategory(ProductCategory productCategory, boolean useHibernate) {
        if(useHibernate){
            productCategoryDao.updateProductCategory(productCategory);
        }
        productCategoryRepository.save(productCategory);
    }

    @Override
    @Transactional
    public void deleteProductCategory(ProductCategory productCategory, boolean useHibernate) {
        if (useHibernate){
            productCategoryDao.deleteProductCategory(productCategory);
        }
        productCategoryRepository.delete(productCategory);
    }
}
