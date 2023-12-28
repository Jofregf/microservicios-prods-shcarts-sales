package app.portfoliojofregf.vercel.shoppingcartsservice.controller;
import app.portfoliojofregf.vercel.shoppingcartsservice.exception.ResourceNotFoundException;
import app.portfoliojofregf.vercel.shoppingcartsservice.model.Shoppingcart;
import app.portfoliojofregf.vercel.shoppingcartsservice.service.IShoppingcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shoppingcart")
public class ShoppingcartController {
    @Autowired
    private IShoppingcartService shoppingcartService;

    @PostMapping("/create")
    public ResponseEntity<Shoppingcart> createShoppingcart(@RequestBody Shoppingcart shoppingcart,
                                                           @RequestParam("name") List<String> names) throws ResourceNotFoundException {
        Shoppingcart newShopCart = shoppingcartService.addShoppingcart(names, shoppingcart);
        return new ResponseEntity<>(newShopCart, HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Shoppingcart>> getAllShoppingcart() {
        List<Shoppingcart> list = shoppingcartService.getAllShoppingcart();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id_shoppingcart}")
    public ResponseEntity<Shoppingcart> getShoppingcart(@PathVariable Long id_shoppingcart) throws ResourceNotFoundException {
        Shoppingcart shp = shoppingcartService.getShoppingcartById(id_shoppingcart);
        return new ResponseEntity<>(shp, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id_shoppingcart}")
    public ResponseEntity<String> deleteShoppingcart(@PathVariable Long id_shoppingcart) {
        shoppingcartService.deleteShoppingcartById(id_shoppingcart);
        return new ResponseEntity<>("shoppingcart successfully deleted", HttpStatus.OK);
    }

    @PutMapping("/edit/{id_shoppingcart}")
    public ResponseEntity<Shoppingcart> editShoppingcart(@RequestBody Shoppingcart shoppingcart,
                                                         @RequestParam("name") List<String> names,
                                                         @PathVariable Long id_shoppingcart) throws ResourceNotFoundException {
        Shoppingcart shpEdit = shoppingcartService.editShoppingcart(id_shoppingcart, shoppingcart, names);
        return new ResponseEntity<>(shpEdit, HttpStatus.OK);
    }
}
