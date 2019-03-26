import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.sda.hypermarket.core.entity.Employee;
import ro.sda.hypermarket.core.sda.EmployeeDao;
import ro.sda.hypermarket.core.service.EmployeeService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class EmployeeDaoImplTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    @Rollback(false)
    public void createEmployeeTest(){
        Employee employee = new Employee();
        employee.setFirstName("Bogdan");
        employee.setLastName("Bogdan");
        employee.setCity("Podul Iloaie");
        employee.setSalary(new BigDecimal(5200));
        employee.setJobTitle("dog chef");
        employeeService.createEmployee(employee,false);
        List<Employee> employees = employeeService.getAll(false);
//        Assert.assertEquals(1, suppliers.size());
        Assert.assertEquals("Bogdan", employees.get(1).getFirstName());
        Assert.assertEquals("Bogdan", employees.get(1).getLastName());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updateEmployeeTest() {
        Employee employee = employeeService.getById(4L,false);
        employee.setFirstName("Sorina");
        employee.setLastName("Boz");
        employee.setCity("Botosani");
        employee.setSalary(new BigDecimal(2100));
        employee.setJobTitle("Manager");
        employeeService.updateEmployee(employee,false);
        List<Employee> employees = employeeService.getAll(false);
        Assert.assertEquals("Sorina", employees.get(2).getFirstName());
        Assert.assertEquals("Boz", employees.get(2).getLastName());
        Assert.assertEquals("Botosani", employees.get(2).getCity());
//        Assert.assertEquals(2100, employees.get(0).getSalary());
        Assert.assertEquals("Manager", employees.get(2).getJobTitle());
    }

    @Test
    public void getEmployeeByIdTest(){
        Employee employee = new Employee();
        employeeService.getById(4L,false);

        List<Employee> employees = employeeService.getAll(false);
        Assert.assertEquals("Sorina", employees.get(2).getFirstName());
        Assert.assertEquals("Boz", employees.get(2).getLastName());
        Assert.assertEquals("Botosani", employees.get(2).getCity());
//        Assert.assertEquals(5200, employees.get(0).getSalary());
        Assert.assertEquals("Manager", employees.get(2).getJobTitle());
    }

//    @Test
//    public void deleteEmployeeTest1(){
//        Employee employee = employeeService.getById(3L,false);
//        employeeService.deleteEmployee(employee,false);
//        List<Employee> employees = employeeService.getAll(false);
//        Assert.assertTrue (employees.isEmpty());
//    }

    @Test
    @Transactional
    @Rollback(false)
    public void deleteEmployeeTest(){
        List<Employee> employees = employeeService.getAll(false);
        int size = employees.size();
        Employee employee = employeeService.getById(3L,false);
        employeeService.deleteEmployee(employee,false);
        employees = employeeService.getAll(false);
        Assert.assertEquals(size - 1, employees.size());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getAllEmployeesTest(){
        Employee employee = new Employee();
        employee.setFirstName("Cristiana");
        employee.setLastName("Turcanu");
        employee.setCity("Iasi");
        employee.setSalary(new BigDecimal(3200));
        employee.setJobTitle("Cashier");
        employeeService.createEmployee(employee,false);

        Employee employee1 = new Employee();
        employee1.setFirstName("Lili");
        employee1.setLastName("Popescu");
        employee1.setCity("Targu Neamt");
        employee1.setSalary(new BigDecimal(2100));
        employee1.setJobTitle("Cashier");
        employeeService.createEmployee(employee1,false);
        List<Employee> employees = employeeService.getAll(false);
        Assert.assertEquals(4, employees.size());
    }
}
