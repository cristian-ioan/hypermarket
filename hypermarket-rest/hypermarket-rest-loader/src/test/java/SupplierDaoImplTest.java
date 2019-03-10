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
        //then
        supplierDao.createSupplier(supplier);
        Assert.assertNotNull(supplier);

    }

    @Test
    public void testGetByIdSupplier(){
        Supplier supplier = supplierDao.getById(5L);

        Long supplierId = supplier.getId();
        String supplierName = supplier.getName();
        String supplierCity = supplier.getCity();

        Assert.assertEquals("Albinuta SRL", supplierName);
        Assert.assertEquals("Iasi", supplierCity);
        Assert.assertEquals(new Long(5), supplierId);

    }

    @Test
    public void testDeleteSupplier(){

        Supplier supplier = supplierDao.getById(5L);

        supplierDao.deleteSupplier(supplier);
        Assert.assertNull(supplier);

    }

    @Test
    public void getAllSuppliers(){
        List<Supplier> allSuppliers = supplierDao.getAll();
        System.out.println(allSuppliers);
    }


}
