package com.example.demo.Service;

import com.example.demo.Entities.Product;
import com.example.demo.Payload.SearchPayload;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Transactional
@Slf4j
@Qualifier
public class SearchPayloadImpl implements SearchPayloadService{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ResponseEntity<List<Product>> findProduct(SearchPayload searchPayload) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query;
        query = cb.createQuery(Product.class);
        Root<Product> findProduct = query.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        // Add predicates based on search conditions
        if (searchPayload.getSearchTo() != 0 && searchPayload.getSearchFrom() != 0) {
            predicates.add(cb.between(findProduct.get("price"), searchPayload.getSearchFrom(), searchPayload.getSearchTo()));
        }
        if (searchPayload.getNameOfProduct() != null && !searchPayload.getNameOfProduct().isEmpty()) {
            predicates.add(cb.equal(findProduct.get("productName"), searchPayload.getNameOfProduct()));
        }
        if (searchPayload.getProductId() > 0) {
            predicates.add(cb.equal(findProduct.get("id"), searchPayload.getProductId()));
        }
        if (searchPayload.getQuantity() > -1) {
            predicates.add(cb.equal(findProduct.get("quantity"), searchPayload.getQuantity()));
        }
        if (searchPayload.getSelectedType() != null && !searchPayload.getSelectedType().isEmpty()) {
            predicates.add(cb.equal(findProduct.get("type"), searchPayload.getSelectedType()));
        }

        // Apply predicates only if there are any
        if (!predicates.isEmpty()) {
            query.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        // Execute the query and get results
        List<Product> listOfProduct = entityManager.createQuery(query).getResultList();
        return ResponseEntity.ok().body(listOfProduct);
    }

    @Override
    public Page<Product> findProduct(SearchPayload searchPayload, int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("price").descending());
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> findProduct = query.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        // Add predicates based on search conditions
        if (searchPayload.getSearchTo() != 0 && searchPayload.getSearchFrom() != 0) {
            predicates.add(cb.between(findProduct.get("price"), searchPayload.getSearchFrom(), searchPayload.getSearchTo()));
        }
        if (searchPayload.getNameOfProduct() != null && !searchPayload.getNameOfProduct().isEmpty()) {
            predicates.add(cb.equal(findProduct.get("productName"), searchPayload.getNameOfProduct()));
        }
        if (searchPayload.getProductId() > 0) {
            predicates.add(cb.equal(findProduct.get("id"), searchPayload.getProductId()));
        }
        if (searchPayload.getQuantity() > -1) {
            predicates.add(cb.equal(findProduct.get("quantity"), searchPayload.getQuantity()));
        }
        if (searchPayload.getSelectedType() != null && !searchPayload.getSelectedType().isEmpty()) {
            predicates.add(cb.equal(findProduct.get("type"), searchPayload.getSelectedType()));
        }

        // Apply predicates
        Predicate [] finalQuery = predicates.toArray(new Predicate[0]);
        if(finalQuery.length>0){
            cb.and(finalQuery);
            query.where(finalQuery);
        }


        // Fetch paginated result list
        List<Product> productResultList = entityManager.createQuery(query)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        List<Product> sizeOfList = entityManager.createQuery(query).getResultList();

        // Build count query
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Product> countRoot = countQuery.from(Product.class);
        Predicate[] finalPredicates = predicates.toArray(new Predicate[0]); // Add this line here
        countQuery.select(cb.count(countRoot)).where(finalPredicates); // Use finalPredicates here
        // Fetch total count for pagination




        return new PageImpl<>(productResultList, pageable,sizeOfList.size());
    }







}
