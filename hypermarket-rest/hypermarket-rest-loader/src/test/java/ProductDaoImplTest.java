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

//    @Before
//    public void disableForeignKey(){
//        Session session = (Session) sessionFactory.getCurrentSession();
//            public void execute(Connection connection) throws SQLException {
//                Statement s = connection.createStatement();
//                s.execute("SET @@foreign_key_checks = 0;");
//                s.close();
//            }
//        });
//    }
//
//    @After
//    public void enableForeignKey(){
//        Session session = productDao.currentSession();
//        session.doWork(new ClassLoaderService.Work(){
//            @Override
//            public void execute(Connection connection) throws SQLException {
//                Statement s = connection.createStatement();
//                s.execute("SET @@foreign_key_checks = 1;");
//                s.close();
//            }
//        });
//    }

    @Test
    public void testCreateProduct(){
        // given
        Product product = new Product();
        // when
        Supplier supplier = supplierDao.getById(16L);
        product.setName("Lapte");
        product.setSupplierPrice(new BigDecimal("16.25"));
        product.setVendingPrice(new BigDecimal("18.25"));
        product.setStock(80);
        product.setSupplier(supplier);
        productDao.createProduct(product);
        //then
        Assert.assertNotNull(product);

    }
}
