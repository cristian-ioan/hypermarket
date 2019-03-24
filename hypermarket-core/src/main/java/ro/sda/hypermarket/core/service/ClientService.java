package ro.sda.hypermarket.core.service;

import ro.sda.hypermarket.core.entity.Client;

import java.util.List;

public interface ClientService {

    Client getById(Long id, boolean useHibernate);
    List<Client> getAll(boolean useHibernate);
    Client createClient(Client client, boolean useHibernate);
    Client updateClient(Client client, boolean useHibernate);
    void deleteClient(Client client, boolean useHibernate);
}
