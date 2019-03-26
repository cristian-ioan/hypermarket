import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.sda.hypermarket.core.entity.Supplier;
import ro.sda.hypermarket.core.service.SupplierService;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class SupplierDaoImplTest {

    @Autowired
    private SupplierService supplierService;

    @Test
    @Rollback(false)
    public void testCreateSupplier(){
        // given
        Supplier supplier = new Supplier();
        // when
        supplier.setName("Albinuta");
        supplier.setContactNo("074698560");
        supplier.setCity("Iasi");
        supplierService.createSupplier(supplier, false);
        //then
        Assert.assertNotNull(supplier);

    }

    @Test
    public void testGetByIdSupplier(){

        Supplier supplier = supplierService.getById(14L, false);

        Long supplierId = supplier.getId();
        String supplierName = supplier.getName();
        String supplierCity = supplier.getCity();

        Assert.assertEquals("Bizz", supplierName);
        Assert.assertEquals("Iasi", supplierCity);
        Assert.assertEquals(new Long(14), supplierId);

    }

    @Test
    @Transactional
    @Rollback(false)
    public void getAllSuppliers(){

        Supplier supplier1 = new Supplier();
        supplier1.setName("Cuiul SRL");
        supplier1.setContactNo("0740655587");
        supplier1.setCity("Tg. Neamt");

        Supplier supplier2 = new Supplier();
        supplier2.setName("Cuiul Indoit SRL");
        supplier2.setContactNo("0740655587");
        supplier2.setCity("Tg. Neamt");

        supplierService.createSupplier(supplier1, false);
        supplierService.createSupplier(supplier2, false);

        List<Supplier> allSuppliers = supplierService.getAll(false);

        Assert.assertEquals(18, allSuppliers.size());

    }

//    @Test
//    public void testDeleteSupplier1(){
//
//        Supplier supplier = supplierService.getById(27L, false);
//        supplierService.deleteSupplier(supplier, false);
//        List<Supplier> allSuppliers = supplierService.getAll(false);
//        Assert.assertTrue(allSuppliers.isEmpty());
//    }

    @Test
    @Transactional
    @Rollback(false)
    public void testDeleteSupplier(){

        List<Supplier> allSuppliers1 = supplierService.getAll(false);

        int size1 = allSuppliers1.size();
        Supplier supplier = supplierService.getById(27L, false);
        supplierService.deleteSupplier(supplier, false);
        List<Supplier> allSuppliers2 = supplierService.getAll(false);
        int size2 = allSuppliers2.size();

        Assert.assertEquals(size1 -1 , size2);

    }

    @Test
    @Transactional
    @Rollback(false)
    public void testUpdateSupplierTest(){

        Supplier supplier = supplierService.getById(14L, false);

        supplier.setCity("Pascani");
        String supplierCity = supplier.getCity();

        supplierService.updateSupplier(supplier, false);

        Assert.assertEquals("Pascani", supplierCity);

    }
}
