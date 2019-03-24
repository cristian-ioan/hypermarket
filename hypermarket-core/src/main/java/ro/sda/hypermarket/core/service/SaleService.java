package ro.sda.hypermarket.core.service;

import ro.sda.hypermarket.core.entity.Sale;

import java.util.List;

public interface SaleService {

    Sale getById(Long id, boolean useHibernate);
    List<Sale> getAll(boolean useHibernate);
    Sale createSale(Sale sale, boolean useHibernate);
    void updateSale(Sale sale, boolean useHibernate);
    void deleteSale(Sale sale, boolean useHibernate);
}
