package app.portfoliojofregf.vercel.salesservice.service;

import app.portfoliojofregf.vercel.salesservice.dto.SalesDto;
import app.portfoliojofregf.vercel.salesservice.dto.ShoppingcartDto;
import app.portfoliojofregf.vercel.salesservice.exception.ResourceNotFoundException;
import app.portfoliojofregf.vercel.salesservice.model.Sales;
import app.portfoliojofregf.vercel.salesservice.repository.ISalesRepository;
import app.portfoliojofregf.vercel.salesservice.repository.IShoppingcartAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SalesService implements ISalesService{

    @Autowired
    private IShoppingcartAPI shoppingcartAPI;
    @Autowired
    private ISalesRepository salesRepository;

    @Override
    @CircuitBreaker(name = "shoppingcarts-service", fallbackMethod = "fallbackGetShoppingcart")
    @Retry(name = "shoppingcarts-service")
    @Transactional
    public SalesDto addSales(Long id_shc, Sales sales) throws ResourceNotFoundException {
        ShoppingcartDto shopc = shoppingcartAPI.getShoppingcartById(id_shc);

        if(shopc == null || shopc.getProductsList() == null || shopc.getProductsList().isEmpty()){
            throw new ResourceNotFoundException("The shoppingcart is empty");
        }

        sales.setDate_sale(sales.getDate_sale());
        sales.setSent(sales.getSent());
        sales.setId_shoppingcart(id_shc);

        Sales savedSale = salesRepository.save(sales);

        return mapToSalesDto(savedSale, shopc);
    }

    @Override
    @CircuitBreaker(name = "shoppingcarts-service", fallbackMethod = "fallbackGetShoppingcartDto")
    @Retry(name = "shoppingcarts-service")
    @Transactional
    public List<SalesDto> getAllSales() {
        List<Sales> salesList = salesRepository.findAll();
        List<SalesDto> salesDtoList = new ArrayList<>();

        for(Sales sales:salesList){
            ShoppingcartDto shoppingcartDto = shoppingcartAPI.getShoppingcartById(sales.getId_shoppingcart());
            SalesDto salesDto = mapToSalesDto(sales, shoppingcartDto);
            salesDtoList.add(salesDto);
        }
        return salesDtoList;
    }

    @Override
    @CircuitBreaker(name = "shoppingcarts-service", fallbackMethod = "fallbackGetShoppingcart")
    @Retry(name = "shoppingcarts-service")
    @Transactional
    public SalesDto getSalesById(Long id_sales) throws ResourceNotFoundException {
        Sales sale = salesRepository.findById(id_sales)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found"));

        SalesDto saleDto = new SalesDto();
        saleDto.setId_sales(sale.getId_sales());
        saleDto.setDate_sale(sale.getDate_sale());
        saleDto.setSent(sale.getSent());
        saleDto.setId_shoppingcart(sale.getId_shoppingcart());

        ShoppingcartDto shopc = shoppingcartAPI.getShoppingcartById(sale.getId_shoppingcart());
        saleDto.setShoppingcart(shopc);

        return saleDto;
    }

    @Override
    public String deleteSalesById(Long id_sales) throws ResourceNotFoundException {
        salesRepository.deleteById(id_sales);
        return "Sale deleted successfully";
    }

    @Override
    @CircuitBreaker(name = "shoppingcarts-service", fallbackMethod = "fallbackGetShoppingcart")
    @Retry(name = "shoppingcarts-service")
    @Transactional
    public SalesDto editSales(Sales sales, Long id_sales) throws ResourceNotFoundException {
        Sales sale = salesRepository.findById(id_sales)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found"));



        sale.setDate_sale(sales.getDate_sale());
        sale.setSent(sales.getSent());
        sale.setId_shoppingcart(sales.getId_shoppingcart());

        ShoppingcartDto shopc = shoppingcartAPI.getShoppingcartById(sale.getId_shoppingcart());

        salesRepository.save(sale);

        return mapToSalesDto(sale, shopc);
    }

    private SalesDto fallbackGetShoppingcart(Throwable throwable){
        SalesDto salesDto = new SalesDto();
        salesDto.setId_sales(0L);
        salesDto.setDate_sale(null);
        salesDto.setSent(null);
        salesDto.setId_sales(0L);
        salesDto.setShoppingcart(new ShoppingcartDto(null, Collections.emptyList()));
        return salesDto;
    }


    private List<SalesDto> fallbackGetShoppingcartDto(Throwable throwable){
        SalesDto fallbackSalesDto = new SalesDto();

        fallbackSalesDto.setId_sales(0L);
        fallbackSalesDto.setDate_sale(null);
        fallbackSalesDto.setSent(null);
        fallbackSalesDto.setId_shoppingcart(0L);
        fallbackSalesDto.setShoppingcart(new ShoppingcartDto(null, Collections.emptyList()));

        List<SalesDto> fallbackSalesDtoList = new ArrayList<>();
        List<Sales> salesList = salesRepository.findAll();

        for(Sales sales:salesList){
            fallbackSalesDtoList.add(fallbackSalesDto);
        }

        return fallbackSalesDtoList;
    }

    private SalesDto mapToSalesDto(Sales sales, ShoppingcartDto shoppingcartDto){
        SalesDto salesDto = new SalesDto();

        salesDto.setId_sales(sales.getId_sales());
        salesDto.setDate_sale(sales.getDate_sale());
        salesDto.setSent(sales.getSent());
        salesDto.setId_shoppingcart(sales.getId_shoppingcart());
        salesDto.setShoppingcart(shoppingcartDto);

        return salesDto;
    }
}
