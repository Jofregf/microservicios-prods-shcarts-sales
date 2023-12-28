package app.portfoliojofregf.vercel.salesservice.controller;

import app.portfoliojofregf.vercel.salesservice.dto.SalesDto;
import app.portfoliojofregf.vercel.salesservice.exception.ResourceNotFoundException;
import app.portfoliojofregf.vercel.salesservice.model.Sales;
import app.portfoliojofregf.vercel.salesservice.service.ISalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    @Autowired
    private ISalesService salesService;

    @PostMapping("/create")
    public ResponseEntity<SalesDto> createSales(@RequestBody Sales sales,
                                                @RequestParam("id_shc") Long id_shc) throws ResourceNotFoundException {
        SalesDto newSales = salesService.addSales(id_shc, sales);
        return new ResponseEntity<>(newSales, HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<SalesDto>> getAllSales(){
        List<SalesDto> list = salesService.getAllSales();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id_sales}")
    public ResponseEntity<SalesDto> getSaleById(@PathVariable Long id_sales) throws ResourceNotFoundException {
        SalesDto sale = salesService.getSalesById(id_sales);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id_sales}")
    public ResponseEntity<String> deleteSaleById(@PathVariable Long id_sales) throws ResourceNotFoundException {
        salesService.deleteSalesById(id_sales);
        return new ResponseEntity<>("sale successfully deleted", HttpStatus.OK);
    }

    @PutMapping("/edit/{id_sales}")
    public ResponseEntity<SalesDto> editSale(@RequestBody Sales sales,
                                             @PathVariable Long id_sales) throws ResourceNotFoundException {
        SalesDto salesDto = salesService.editSales(sales, id_sales);
        return new ResponseEntity<>(salesDto, HttpStatus.OK);
    }
}
