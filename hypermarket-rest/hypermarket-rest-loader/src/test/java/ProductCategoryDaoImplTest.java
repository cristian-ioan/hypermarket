import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.sda.hypermarket.core.entity.Employee;
import ro.sda.hypermarket.core.entity.ProductCategory;
import ro.sda.hypermarket.core.sda.EmployeeDao;
import ro.sda.hypermarket.core.sda.ProductCategoryDao;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class ProductCategoryDaoImplTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void testGetAll(){

        Employee employee = new Employee();
        employee.setFirstName("Bogdan");
        employee.setLastName("Bogdanel");
        employee.setSalary(new BigDecimal(10000.0));
        employee.setJobTitle("Manager");
        employee.setCity("Podul Iloaiei");
        employee = employeeDao.createEmployee(employee);

        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("Chicks");
        productCategory.setManager(employee);

        productCategoryDao.createProductCategory(productCategory);

        List<ProductCategory> allProductCategories = productCategoryDao.getAll();

        Assert.assertEquals(1, allProductCategories.size());

    }

    @Test
    @Transactional
    @Rollback(true)
    public void testCreateProductCategory(){
        Employee employee = new Employee();
        employee.setFirstName("Bogdan");
        employee.setLastName("Bogdanel");
        employee.setSalary(new BigDecimal(10000.0));
        employee.setJobTitle("Manager");
        employee.setCity("Podul Iloaiei");
        employeeDao.createEmployee(employee);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("chicks");
        productCategory.setManager(employee);
        productCategoryDao.createProductCategory(productCategory);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void testGetByIdProductCategory(){

        ProductCategory productCategory = productCategoryDao.getById(4L);

        String productCategoryName = productCategory.getName();
        Long manager_id = productCategory.getManager().getId();

        Assert.assertEquals("Chicks", productCategoryName);
        Assert.assertEquals(new Long(16), manager_id);

    }

    @Test
    public void testDeleteProductCategory(){

        List<ProductCategory> allProductCategories = productCategoryDao.getAll();

        int size1 = allProductCategories.size();

        ProductCategory productCategory = productCategoryDao.getById(2L);

        productCategoryDao.deleteProductCategory(productCategory);

        List<ProductCategory> allProductCategories2 = productCategoryDao.getAll();
        int size2 = allProductCategories2.size();

        Assert.assertEquals(size1 -1 , size2);
    }

    @Test
    public void testUpdateProductCategory(){

        ProductCategory productCategory = productCategoryDao.getById(4L);

        productCategory.setName("drinks");
        String updateName = productCategory.getName();
        Long managerId = productCategory.getManager().getId();

        productCategoryDao.updateProductCategory(productCategory);

        Assert.assertEquals(new Long(16), managerId);
        Assert.assertEquals("drinks", updateName);

    }

}
