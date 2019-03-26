import com.mysql.cj.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.sda.hypermarket.core.entity.Product;
import ro.sda.hypermarket.core.entity.Supplier;
import ro.sda.hypermarket.core.sda.ProductDao;
import ro.sda.hypermarket.core.sda.SupplierDao;
import ro.sda.hypermarket.core.service.ProductService;
import ro.sda.hypermarket.core.service.SupplierService;

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
    private ProductService productService;

    @Autowired
    private SupplierService supplierService;


    @Test
    @Rollback(false)
    public void testCreateProduct(){
        // given
        Product product = new Product();
        // when
        Supplier supplier = supplierService.getById(1L, false);
        product.setName("Lapte");
        product.setSupplierPrice(new BigDecimal("16.25"));
        product.setVendingPrice(new BigDecimal("18.25"));
        product.setStock(80);
        product.setSupplier(supplier);
        productService.createProduct(product, false);
        //then
        Assert.assertNotNull(product);

    }

    @Test
    public void testGetByIdProduct(){
        Product product = productService.getById(1L, false);

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
    @Transactional
    @Rollback(false)
    public void testUpdateProductTest(){

        Product product = productService.getById(1L, false);

        product.setVendingPrice(new BigDecimal(19.35));
        BigDecimal vendingPrice = product.getVendingPrice();
        productService.updateProduct(product,false);

        Assert.assertEquals(new BigDecimal(19.35), vendingPrice);

    }

    @Test
    @Transactional
    @Rollback(false)
    public void testDeleteProduct(){

        List<Product> allProducts1 = productService.getAll(false);

        int size1 = allProducts1.size();
        Product product = productService.getById(1L, false);
        productService.deleteProduct(product, false);
        List<Product> allProducts2 = productService.getAll(false);
        int size2 = allProducts2.size();

        Assert.assertEquals(size1 -1 , size2);

    }
    @Test
    @Transactional
    @Rollback(false)
    public void getAllProducts(){

        Product product1 = new Product();
        product1.setName("Paine");
        product1.setSupplierPrice(new BigDecimal(2.5));
        product1.setVendingPrice(new BigDecimal(3.5));
        product1.setSupplier(supplierService.getById(1L, false));
        product1.setStock(64);

        Product product2 = new Product();
        product2.setName("Covrig");
        product2.setSupplierPrice(new BigDecimal(1.5));
        product2.setVendingPrice(new BigDecimal(2.5));
        product2.setSupplier(supplierService.getById(1L,false));
        product2.setStock(83);

        productService.createProduct(product1, false);
        productService.createProduct(product2, false);

        List<Product> allProducts = productService.getAll(false);

        Assert.assertEquals(2, allProducts.size());

    }


}
