import com.mysql.cj.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.sda.hypermarket.core.entity.Product;
import ro.sda.hypermarket.core.entity.Supplier;
import ro.sda.hypermarket.core.sda.ProductDao;
import ro.sda.hypermarket.core.sda.SupplierDao;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class ProductDaoImplTest {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private SupplierDao supplierDao;

    @Autowired
    private SessionFactory sessionFactory;


    @Test
    public void testCreateProduct(){
        // given
        Product product = new Product();
        // when
        Supplier supplier = supplierDao.getById(1L);
        product.setName("Lapte");
        product.setSupplierPrice(new BigDecimal("16.25"));
        product.setVendingPrice(new BigDecimal("18.25"));
        product.setStock(80);
        product.setSupplier(supplier);
        productDao.createProduct(product);
        //then
        Assert.assertNotNull(product);

    }

    @Test
    public void testGetByIdProduct(){
        Product product = productDao.getById(1L);

        String productName = product.getName();
        BigDecimal supplierPrice = product.getSupplierPrice();
        BigDecimal vendingPrice = product.getVendingPrice();
        Integer stock = product.getStock();
        Supplier supplier = product.getSupplier();

        Assert.assertEquals("Lapte", productName);
        Assert.assertEquals(java.util.Optional.of(80), java.util.Optional.ofNullable(stock));
        Assert.assertEquals(new BigDecimal(16.25), supplierPrice);
        Assert.assertEquals(new BigDecimal(18.25), vendingPrice);
        Assert.assertEquals("Albinuta", supplier.getName());
    }

    @Test
    public void testUpdateProductTest(){

        Product product = productDao.getById(1L);

        product.setVendingPrice(new BigDecimal(19.35));
        BigDecimal vendingPrice = product.getVendingPrice();
        productDao.updateProduct(product);

        Assert.assertEquals(new BigDecimal(19.35), vendingPrice);

    }

    @Test
    public void testDeleteProduct(){

        List<Product> allProducts1 = productDao.getAll();

        int size1 = allProducts1.size();
        Product product = productDao.getById(3L);
        productDao.deleteProduct(product);
        List<Product> allProducts2 = productDao.getAll();
        int size2 = allProducts2.size();

        Assert.assertEquals(size1 -1 , size2);

    }
    @Test
    public void getAllProducts(){

        Product product1 = new Product();
        product1.setName("Paine");
        product1.setSupplierPrice(new BigDecimal(2.5));
        product1.setVendingPrice(new BigDecimal(3.5));
        product1.setSupplier(supplierDao.getById(1L));
        product1.setStock(64);

        Product product2 = new Product();
        product2.setName("Covrig");
        product2.setSupplierPrice(new BigDecimal(1.5));
        product2.setVendingPrice(new BigDecimal(2.5));
        product2.setSupplier(supplierDao.getById(1L));
        product2.setStock(83);

        productDao.createProduct(product1);
        productDao.createProduct(product2);

        List<Product> allProducts = productDao.getAll();

        Assert.assertEquals(2, allProducts.size());

    }


}
