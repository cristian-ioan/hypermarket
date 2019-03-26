
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.sda.hypermarket.core.entity.Client;
import ro.sda.hypermarket.core.entity.Employee;
import ro.sda.hypermarket.core.entity.Sale;
import ro.sda.hypermarket.core.service.ClientService;
import ro.sda.hypermarket.core.service.EmployeeService;
import ro.sda.hypermarket.core.service.SaleService;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class SaleDaoImplTest {

    @Autowired
    private SaleService saleService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ClientService clientService;

    @Test
    @Rollback(false)
    public void testCreateSale(){
        Sale sale = new Sale();
        sale.setNumber(1L);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, 03, 12);
        Date date = cal.getTime();
        sale.setSaleDate(date);

        Employee employee = new Employee();
        employee.setFirstName("Bogdan");
        employee.setLastName("Bogdanel");
        employee.setSalary(new BigDecimal(10000.0));
        employee.setJobTitle("salesman");
        employee.setCity("Podul Iloaiei");
        employeeService.createEmployee(employee,false);
        Long id = employee.getId();
        sale.setEmployeeId(id);

        Client client = new Client();
        client.setName("Puiul fericit");
        sale.setClient(client);
        clientService.createClient(client,false);

        saleService.createSale(sale,false);
        Assert.assertNotNull(sale);
    }

    @Test
    public void testGetByIdSale(){

        Sale sale = saleService.getById(2L,false);

        Long saleNumber = sale.getNumber();
        Date saleDate = sale.getSaleDate();
        Long employeeId = sale.getEmployeeId();
        Client client = sale.getClient();

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(2019, 03, 12);
        Date date = cal.getTime();
        new SimpleDateFormat("yyyy-MM-dd").format(date);


        Assert.assertEquals(java.util.Optional.of(1L), java.util.Optional.of(saleNumber));
        Assert.assertEquals(new SimpleDateFormat("yyyy-MM-dd").format(date), String.valueOf(saleDate));
        Assert.assertEquals(java.util.Optional.of(2L), java.util.Optional.of(employeeId));
        Assert.assertEquals(clientService.getById(2L,false),client);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testUpdateProductCategory(){

        Sale sale = saleService.getById(2L,false);

        sale.setNumber(2L);

        Client client = new Client();
        client.setName("Puiul nefericit");
        sale.setClient(client);
        clientService.createClient(client,false);
        sale.setClient(client);
        Long updateNumber = sale.getNumber();
        Client updateClient = sale.getClient();

        saleService.updateSale(sale,false);

        Assert.assertEquals(new Long(2), updateNumber);
        Assert.assertEquals(clientService.getById(5L,false), updateClient);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testDeleteProductCategory(){

        List<Sale> allSales = saleService.getAll(false);
        int size1 = allSales.size();
        Sale sale = saleService.getById(5L,false);

        saleService.deleteSale(sale,false);

        List<Sale> allSales2 = saleService.getAll(false);
        int size2 = allSales2.size();

        Assert.assertEquals(size1 -1 , size2);
    }

}
