package ro.sda.hypermarket.core.sda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.sda.hypermarket.core.entity.Client;

import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ClientDaoImpl implements ClientDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Client createClient(Client client) {
        sessionFactory.getCurrentSession().beginTransaction();
        getCurrentSession().save(client);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().getTransaction().commit();
        return client;
    }

    @Override
    public Client getById(Long id) {
        return getCurrentSession().load(Client.class, id);
    }

    @Override
    public List<Client> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaQuery<Client> criteriaQuery = session.getCriteriaBuilder().createQuery(Client.class);
        criteriaQuery.from(Client.class);
        List<Client> clients = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return clients;
    }

    @Override
    public Client updateClient(Client client) {

        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        Client client1 = getById(client.getId());
        sessionFactory.getCurrentSession().merge(client1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();
        return client;
    }

    @Override
    public void deleteClient(Client client) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        Client client1 = getById(client.getId());
        sessionFactory.getCurrentSession().delete(client1);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

}
