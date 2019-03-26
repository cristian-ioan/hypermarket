import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.sda.hypermarket.core.entity.Product;
import ro.sda.hypermarket.core.entity.Sale;
import ro.sda.hypermarket.core.entity.SaleProduct;
import ro.sda.hypermarket.core.service.ProductService;
import ro.sda.hypermarket.core.service.SaleProductService;
import ro.sda.hypermarket.core.service.SaleService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class SaleProductDaoImplTest {

    @Autowired
    private SaleProductService saleProductService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SaleService saleService;

    @Test
    @Rollback(false)
    public void testCreateSaleProduct(){
        SaleProduct saleProduct = new SaleProduct();
        Product product = new Product();
        product.setName("morcov");
        product.setSupplierPrice(new BigDecimal(2.5));
        product.setVendingPrice(new BigDecimal(3));
        product.setStock(12);
        productService.createProduct(product,false);
        saleProduct.setProduct(product);
        saleProduct.setQuantity(1L);

        Sale sale = new Sale();
        sale = saleService.getById(2L,false);
        saleProduct.setSale(sale);
        saleProductService.createSaleProduct(saleProduct,false);

        Assert.assertNotNull(saleProduct);
    }

    @Test
    public void testGetByIdSaleProduct(){

        SaleProduct saleProduct = saleProductService.getById(2L,false);

        Long quantity = saleProduct.getQuantity();
        Product product = saleProduct.getProduct();
        Sale sale = saleProduct.getSale();

        Assert.assertEquals(java.util.Optional.of(1L), java.util.Optional.of(quantity));
        Assert.assertEquals(java.util.Optional.of(5L), java.util.Optional.of(product.getId()));
        Assert.assertEquals(java.util.Optional.of(2L), java.util.Optional.of(sale.getId()));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testUpdateSaleProduct(){

        SaleProduct saleProduct = saleProductService.getById(2L,false);

        saleProduct.setQuantity(2L);

        saleProductService.updateSaleProduct(saleProduct,false);

        Assert.assertEquals(new Long(2), saleProduct.getQuantity());

    }

    @Test
    @Transactional
    @Rollback(false)
    public void testDeleteProductCategory(){

        List<SaleProduct> allSaleProducts = saleProductService.getAll(false);
        int size1 = allSaleProducts.size();
        SaleProduct saleProduct = saleProductService.getById(3L,false);

        saleProductService.deleteSaleProduct(saleProduct,false);

        List<SaleProduct> allSalesProducts2 = saleProductService.getAll(false);
        int size2 = allSalesProducts2.size();

        Assert.assertEquals(size1 -1 , size2);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getAllSaleProductsTest(){
        SaleProduct saleProduct = new SaleProduct();
        saleProduct.setQuantity(15L);

        Product product = productService.getById(2L,false);
        saleProduct.setProduct(product);

        Sale sale = new Sale();
        sale = saleService.getById(2L,false);
        saleProduct.setSale(sale);

        saleProductService.createSaleProduct(saleProduct,false);

        List<SaleProduct> saleProducts = saleProductService.getAll(false);
        Assert.assertEquals(1, saleProducts.size());

    }
}
