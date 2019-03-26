import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.sda.hypermarket.core.entity.Department;
import ro.sda.hypermarket.core.sda.DepartmentDao;
import ro.sda.hypermarket.core.service.DepartmentService;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class DepartmentDaoImplTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    @Rollback(false)
    public void createDepartmentTest() {

        Department department = new Department();
        department.setName("Sales");
        departmentService.createDepartment(department,false);
        List<Department> departments = departmentService.getAll(false);
        Assert.assertEquals("Sales", department.getName());
    }

    @Test
    public void getDepartmentByIdTest() {
        Department department = new Department();
        Department department1 = departmentService.getById(1L,false);
        Assert.assertEquals("Sales", department1.getName());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updateDepartmentTest(){

        Department department = departmentService.getById(1L,false);
        department.setName("HR");
        departmentService.updateDepartment(department,false);
        List<Department> departments = departmentService.getAll(false);
        Assert.assertEquals("HR", departments.get(0).getName());
    }


    @Test
    @Transactional
    @Rollback(false)
    public void deleteDepartmentTest(){
        List<Department> departments = departmentService.getAll(false);
        int size = departments.size();
        Department department = departmentService.getById(1L,false);
        departmentService.deleteDepartment(department,false);
        departments = departmentService.getAll(false);
        Assert.assertEquals(size - 1, departments.size());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getAllDepartmentsTest() {

        Department department = new Department();
        department.setName("Marketing");
        departmentService.createDepartment(department,false);

        Department department1 = new Department();
        department1.setName("Accounting");
        departmentService.createDepartment(department1,false);

        List<Department> departments = departmentService.getAll(false);
        Assert.assertEquals(3, departments.size());
    }

}
