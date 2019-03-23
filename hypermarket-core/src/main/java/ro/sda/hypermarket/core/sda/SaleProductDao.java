package ro.sda.hypermarket.core.sda;

import org.hibernate.Session;
import ro.sda.hypermarket.core.entity.SaleProduct;

import java.util.List;

public interface SaleProductDao {

    Session getCurrentSession();
    SaleProduct getById(Long id);
    List<SaleProduct> getAll();
    SaleProduct createSaleProduct(SaleProduct saleProduct);
    SaleProduct updateSaleProduct(SaleProduct saleProduct);
    void deleteSaleProduct(SaleProduct saleProduct);
}
