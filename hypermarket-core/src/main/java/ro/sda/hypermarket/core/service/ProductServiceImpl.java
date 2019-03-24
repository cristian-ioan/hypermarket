package ro.sda.hypermarket.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.entity.Product;
import ro.sda.hypermarket.core.repository.ProductRepository;
import ro.sda.hypermarket.core.sda.ProductDao;

import java.util.List;

@Service("productService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getById(Long id, boolean useHibernate) {
        if (useHibernate){
            return productDao.getById(id);
        }
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAll(boolean useHibernate) {
        if (useHibernate){
            return productDao.getAll();
        }
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product createProduct(Product product, boolean useHibernate) {
        if (useHibernate){
            return productDao.createProduct(product);
        }
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void updateProduct(Product product, boolean useHibernate) {
        if(useHibernate){
            productDao.updateProduct(product);
        }
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Product product, boolean useHibernate) {
        if (useHibernate){
            productDao.deleteProduct(product);
        }
        productRepository.delete(product);
    }
}
