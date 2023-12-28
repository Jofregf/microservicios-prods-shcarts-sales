package app.portfoliojofregf.vercel.salesservice.service;

import app.portfoliojofregf.vercel.salesservice.dto.SalesDto;
import app.portfoliojofregf.vercel.salesservice.exception.ResourceNotFoundException;
import app.portfoliojofregf.vercel.salesservice.model.Sales;

import java.util.List;

public interface ISalesService {
    public SalesDto addSales(Long id_shc, Sales sales) throws ResourceNotFoundException;
    public List<SalesDto> getAllSales();
    public SalesDto getSalesById(Long id_sales) throws ResourceNotFoundException;
    public String deleteSalesById(Long id_sales) throws ResourceNotFoundException;
    public SalesDto editSales(Sales  sales, Long id_sales) throws ResourceNotFoundException;
}
