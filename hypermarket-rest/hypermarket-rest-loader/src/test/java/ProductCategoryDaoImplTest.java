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
import ro.sda.hypermarket.core.service.EmployeeService;
import ro.sda.hypermarket.core.service.ProductCategoryService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class ProductCategoryDaoImplTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private EmployeeService employeeService;

    @Test
    @Transactional
    @Rollback(false)
    public void testGetAll(){

        Employee employee = new Employee();
        employee.setFirstName("Bogdan");
        employee.setLastName("Bogdanel");
        employee.setSalary(new BigDecimal(10000.0));
        employee.setJobTitle("Manager");
        employee.setCity("Podul Iloaiei");
        employee = employeeService.createEmployee(employee,false);

        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("Chicks");
        productCategory.setManager(employee);

        productCategoryService.createProductCategory(productCategory,false);

        List<ProductCategory> allProductCategories = productCategoryService.getAll(false);

        Assert.assertEquals(1, allProductCategories.size());

    }


    @Test
    @Rollback(false)
    public void testCreateProductCategory(){
        Employee employee = new Employee();
        employee.setFirstName("Bogdan");
        employee.setLastName("Bogdanel");
        employee.setSalary(new BigDecimal(10000.0));
        employee.setJobTitle("Manager");
        employee.setCity("Podul Iloaiei");
        employeeService.createEmployee(employee,false);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("chicks");
        productCategory.setManager(employee);
        productCategoryService.createProductCategory(productCategory,false);
        Assert.assertNotNull(productCategory);
    }

    @Test
    public void testGetByIdProductCategory(){

        ProductCategory productCategory = productCategoryService.getById(1L,false);

        String productCategoryName = productCategory.getName();
        Long manager_id = productCategory.getManager().getId();

        Assert.assertEquals("Chicks", productCategoryName);
        Assert.assertEquals(new Long(7), manager_id);

    }


    @Test
    @Transactional
    @Rollback(false)
    public void testDeleteProductCategory(){

        List<ProductCategory> allProductCategories = productCategoryService.getAll(false);

        int size1 = allProductCategories.size();

        ProductCategory productCategory = productCategoryService.getById(2L,false);

        productCategoryService.deleteProductCategory(productCategory,false);

        List<ProductCategory> allProductCategories2 = productCategoryService.getAll(false);
        int size2 = allProductCategories2.size();

        Assert.assertEquals(size1 -1 , size2);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testUpdateProductCategory(){

        ProductCategory productCategory = productCategoryService.getById(2L,false);

        productCategory.setName("drinks");
        String updateName = productCategory.getName();

        productCategoryService.updateProductCategory(productCategory,false);
        Assert.assertEquals("drinks", updateName);

    }

}
