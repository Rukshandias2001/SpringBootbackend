package com.example.demo.Service;

import com.example.demo.Entities.Product;
import com.example.demo.Payload.SearchPayload;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SearchPayloadService {

    ResponseEntity<List<Product>> findProduct(SearchPayload searchPayload);

    public ResponseEntity<Page<Product>> findProduct(SearchPayload searchPayload, int page, int size);
}
