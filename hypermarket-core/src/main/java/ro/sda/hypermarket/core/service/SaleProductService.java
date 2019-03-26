package ro.sda.hypermarket.core.service;

import ro.sda.hypermarket.core.entity.SaleProduct;

import java.util.List;

public interface SaleProductService {

    SaleProduct getById(Long id, boolean useHibernate);
    List<SaleProduct> getAll(boolean useHibernate);
    SaleProduct createSaleProduct(SaleProduct saleProduct, boolean useHibernate);
    SaleProduct updateSaleProduct(SaleProduct saleProduct, boolean useHibernate);
    void deleteSaleProduct(SaleProduct saleProduct, boolean useHibernate);
}
