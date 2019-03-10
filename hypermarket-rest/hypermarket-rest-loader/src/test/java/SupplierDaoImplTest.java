import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.sda.hypermarket.core.entity.Supplier;
import ro.sda.hypermarket.core.sda.SupplierDao;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class SupplierDaoImplTest {

    @Autowired
    private SupplierDao supplierDao;

    @Test
    public void testCreateSupplier(){
        // given
        Supplier supplier = new Supplier();
        // when
        supplier.setName("Albinuta");
        supplier.setContactNo("074698564");
        supplier.setCity("Iasi");
        supplierDao.createSupplier(supplier);
        //then
        Assert.assertNotNull(supplier);

    }

    @Test
    public void testGetByIdSupplier(){
        Supplier supplier = supplierDao.getById(11L);

        Long supplierId = supplier.getId();
        String supplierName = supplier.getName();
        String supplierCity = supplier.getCity();

        Assert.assertEquals("Albinuta SRL", supplierName);
        Assert.assertEquals("Iasi", supplierCity);
        Assert.assertEquals(new Long(5), supplierId);

    }

    @Test
    public void testDeleteSupplier(){

        Supplier supplier = supplierDao.getById(13L);

        supplierDao.deleteSupplier(supplier);

        List<Supplier> allSuppliers = supplierDao.getAll();

        Assert.assertTrue(allSuppliers.isEmpty());

    }

    @Test
    public void testDeleteSupplier1(){

        List<Supplier> allSuppliers1 = supplierDao.getAll();
        int size1 = allSuppliers1.size();

        Supplier supplier = supplierDao.getById(15L);

        supplierDao.deleteSupplier(supplier);

        List<Supplier> allSuppliers2 = supplierDao.getAll();
        int size2 = allSuppliers2.size();

        Assert.assertEquals(size1 -1 , size2);

    }

    @Test
    public void getAllSuppliers(){

        Supplier supplier1 = new Supplier();
        supplier1.setName("Cuiul SRL");
        supplier1.setContactNo("0740655587");
        supplier1.setCity("Tg. Neamt");

        Supplier supplier2 = new Supplier();
        supplier2.setName("Cuiul Indoit SRL");
        supplier2.setContactNo("0740655587");
        supplier2.setCity("Tg. Neamt");

        supplierDao.createSupplier(supplier1);
        supplierDao.createSupplier(supplier2);

        List<Supplier> allSuppliers = supplierDao.getAll();

        Assert.assertEquals(2, allSuppliers.size());

    }

    @Test
    public void testUpdateSupplierTest(){

        Supplier supplier = supplierDao.getById(14L);

        supplier.setCity("Iasi");
        String supplierCity = supplier.getCity();

        supplierDao.updateSupplier(supplier);

        Assert.assertEquals("Iasi", supplierCity);

    }


}
