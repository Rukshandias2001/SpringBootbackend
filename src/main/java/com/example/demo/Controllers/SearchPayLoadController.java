package com.example.demo.Controllers;

import com.example.demo.Entities.Product;
import com.example.demo.Payload.SearchPayload;
import com.example.demo.Service.SearchPayloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/searchPayLoad")
public class SearchPayLoadController {
    @Autowired
    SearchPayloadService searchPayloadService;

    @GetMapping("/searchResult")
    public ResponseEntity<List<Product>> searchProduct(
            @RequestParam(required = false) String nameOfProduct,
            @RequestParam(required = false) Integer searchFrom,
            @RequestParam(required = false) Integer searchTo,
            @RequestParam(required = false) String selectedType,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = false) Integer productId,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer  size) {

        SearchPayload searchPayload = new SearchPayload();
        if(searchFrom != null) {
            searchPayload.setSearchFrom(searchFrom);
        }
        if(searchTo != null) {
            searchPayload.setSearchTo(searchTo);
        }
        if(selectedType != null) {
            searchPayload.setSelectedType(selectedType);
        }if(nameOfProduct != null) {
            searchPayload.setNameOfProduct(nameOfProduct);
        }
        if(quantity != null) {
            searchPayload.setQuantity(quantity);
        }
        if(productId != null) {
            searchPayload.setProductId(productId);
        }
        return searchPayloadService.findProduct(searchPayload);
    }

}
