import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.sda.hypermarket.core.entity.Client;
import ro.sda.hypermarket.core.sda.ClientDao;
import ro.sda.hypermarket.core.service.ClientService;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class ClientDaoImplTest {

//    @Autowired
//    private ClientDao clientDAO;

    @Autowired
    private ClientService clientService;

    @Test
    @Rollback(false)
    public void createClientTest() {

//        Client client = new Client();
//        client.setName("Irina Mihai");
//        clientDAO.createClient(client);
//        List<Client> clients = clientDAO.getAll();
//        Assert.assertEquals("Irina Mihai", client.getName());

        Client client = new Client();
        client.setName("Irina Mihai");
        clientService.createClient(client, false);
        List<Client> clients = clientService.getAll(false);
        Assert.assertEquals("Irina Mihai", client.getName());
    }

    @Test
    public void getClientByIdTest() {

//        Client client = clientService.getById(1L, false);
//        Assert.assertEquals("Irina Mihai", client.getName());

        Client client = clientService.getById(1L, false);
        Assert.assertEquals("Irina Mihai", client.getName());
    }

    @Test
    public void getAllClientsTest() {

//        Client client = new Client();
//        client.setName("Madalina Georgiana");
//        clientService.createClient(client, false);
//
//        Client client1 = new Client();
//        client1.setName("Mardare Cristina");
//        clientService.createClient(client1, false);
//
//        List<Client> clients = clientService.getAll(false);
//        Assert.assertEquals(2, clients.size());

        Client client = new Client();
        client.setName("Madalina Georgiana");
        clientService.createClient(client, false);

        Client client1 = new Client();
        client1.setName("Mardare Cristina");
        clientService.createClient(client1, false);

        List<Client> clients = clientService.getAll(false);
        Assert.assertEquals(2, clients.size());
    }

    @Test
    @Rollback(false)
    public void updateClientTest(){

//        Client client = clientService.getById(1L, false);
//        client.setName("Maria Lupu");
//        clientService.updateClient(client, false);
//        List<Client> clients = clientService.getAll(false);
//        Assert.assertEquals("Maria Lupu", clients.get(0).getName());

        Client client = clientService.getById(1L, false);
        client.setName("Maria Lupu");
        clientService.updateClient(client, false);
        List<Client> clients = clientService.getAll(false);
        Assert.assertEquals("Maria Lupu", clients.get(0).getName());
    }


    @Test
    @Rollback(false)
    public void deleteClientTest1(){

//        List<Client> clients = clientService.getAll(false);
//        int size = clients.size();
//        Client client = clientService.getById(1L, false);
//        clientService.deleteClient(client, false);
//        clients = clientService.getAll(false);
//        Assert.assertEquals(size - 1, clients.size());

        List<Client> clients = clientService.getAll(false);
        int size = clients.size();
        Client client = clientService.getById(1L, false);
        clientService.deleteClient(client, false);
        clients = clientService.getAll(false);
        Assert.assertEquals(size - 1, clients.size());

    }

}
