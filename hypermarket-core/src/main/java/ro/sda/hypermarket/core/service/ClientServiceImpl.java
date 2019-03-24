package ro.sda.hypermarket.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.entity.Client;
import ro.sda.hypermarket.core.repository.ClientRepository;
import ro.sda.hypermarket.core.sda.ClientDao;

import java.util.List;

@Service("clientService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client getById(Long id, boolean useHibernate) {
        if (useHibernate){
            return clientDao.getById(id);
        }
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> getAll(boolean useHibernate) {
        if (useHibernate){
            return clientDao.getAll();
        }
        return clientRepository.findAll();
    }

    @Override
    @Transactional
    public Client createClient(Client client, boolean useHibernate) {
        if (useHibernate){
            return clientDao.createClient(client);
        }
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client updateClient(Client client, boolean useHibernate) {
        if(useHibernate){
            return clientDao.updateClient(client);
        }
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public void deleteClient(Client client, boolean useHibernate) {
        if (useHibernate){
            clientDao.deleteClient(client);
        }
        clientRepository.delete(client);
    }
}
