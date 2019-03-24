import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.sda.hypermarket.core.entity.Product;
import ro.sda.hypermarket.core.entity.Sale;
import ro.sda.hypermarket.core.entity.SaleProduct;
import ro.sda.hypermarket.core.sda.ProductDao;
import ro.sda.hypermarket.core.sda.SaleDao;
import ro.sda.hypermarket.core.sda.SaleProductDao;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class SaleProductDaoImplTest {

    @Autowired
    private SaleProductDao saleProductDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private SaleDao saleDao;

    @Test
    public void testCreateSaleProduct(){
        SaleProduct saleProduct = new SaleProduct();
        Product product = new Product();
        product.setName("morcov");
        product.setSupplierPrice(new BigDecimal(2.5));
        product.setVendingPrice(new BigDecimal(3));
        product.setStock(12);
        productDao.createProduct(product);
        saleProduct.setProduct(product);
        saleProduct.setQuantity(1L);

        Sale sale = new Sale();
        sale = saleDao.getById(2L);
        saleProduct.setSale(sale);
        saleProductDao.createSaleProduct(saleProduct);

        Assert.assertNotNull(saleProduct);
    }

    @Test
    public void testGetByIdSaleProduct(){

        SaleProduct saleProduct = saleProductDao.getById(1L);

        Long quantity = saleProduct.getQuantity();
        Product product = saleProduct.getProduct();
        Sale sale = saleProduct.getSale();

        Assert.assertEquals(java.util.Optional.of(1L), java.util.Optional.of(quantity));
        Assert.assertEquals(java.util.Optional.of(1L), java.util.Optional.of(product.getId()));
        Assert.assertEquals(java.util.Optional.of(2L), java.util.Optional.of(sale.getId()));
    }

    @Test
    public void testUpdateSaleProduct(){

        SaleProduct saleProduct = saleProductDao.getById(1L);

        saleProduct.setQuantity(2L);

        saleProductDao.updateSaleProduct(saleProduct);

        Assert.assertEquals(new Long(2), saleProduct.getQuantity());

    }

    @Test
    public void testDeleteProductCategory(){

        List<SaleProduct> allSaleProducts = saleProductDao.getAll();
        int size1 = allSaleProducts.size();
        SaleProduct saleProduct = saleProductDao.getById(1L);

        saleProductDao.deleteSaleProduct(saleProduct);

        List<SaleProduct> allSalesProducts2 = saleProductDao.getAll();
        int size2 = allSalesProducts2.size();

        Assert.assertEquals(size1 -1 , size2);
    }
}
