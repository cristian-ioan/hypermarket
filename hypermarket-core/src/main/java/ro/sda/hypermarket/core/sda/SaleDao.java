package ro.sda.hypermarket.core.sda;

import org.hibernate.Session;
import ro.sda.hypermarket.core.entity.Sale;

import java.util.List;


public interface SaleDao {

    Session getCurrentSession();
    Sale getById(Long id);
    List<Sale> getAll();
    Sale createSale(Sale sale);
    Sale updateSale(Sale sale);
    void deleteSale(Sale sale);
}
