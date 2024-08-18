package com.example.demo.Service;

import com.cloudinary.api.ApiResponse;
import com.example.demo.Entities.*;
import com.example.demo.Repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Qualifier
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    CreditCardRespository creditCardRespository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderListRepository orderListRepository;

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public ResponseEntity<List<Orders>> findAllOrder() {
        List<Orders> ordersByUserId = orderRepository.findAll();
        return ResponseEntity.ok(ordersByUserId) ;

    }

    @Override
    public ResponseEntity<Orders> findOrderByIdAndUserId(int orderId, int userId) {
        Orders orderByOrderIdAndUserId = orderRepository.findOrderByOrderIdAndUserId(orderId, userId);
        return ResponseEntity.ok().body(orderByOrderIdAndUserId);
    }

    @Override
    public int addOrder(Orders order) {
        return 0;
    }

    @Override
    public int updateOrder(Orders order) {
        return 0;
    }

    @Override
    public int deleteOrder(int id) {
        return 0;
    }

    @Override
    public ResponseEntity<List<Orders>>  findOrdersByUser(int id) {
        List<Orders> ordersByUserId = orderRepository.findOrdersByUserId(id);
        return ResponseEntity.ok(ordersByUserId) ;
    }





    @Override
    public ResponseEntity<Orders> saveOrder(Orders order, ArrayList<Product> listOfProducts, String creditCardId, User customer, ArrayList<OrderedList> orderList) {
        System.out.println("Starting to process order...");

        // Check if user exists
        System.out.println("Checking if user exists...");
        User user = userRepository.findByEmail(customer.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        // Ensure the user is correctly set in the order
        order.setUser(user);

        // Check if credit card exists
        System.out.println("Checking if credit card exists...");
        CreditCards creditCardsNumber = creditCardRespository.findByCardNumber(creditCardId);
        if (creditCardsNumber == null) {
            System.out.println("Credit card not found, creating new one...");
            CreditCards creditCards = new CreditCards();
            creditCards.setCardNumber(creditCardId);
            creditCardRespository.save(creditCards);
            creditCardsNumber = creditCardRespository.findByCardNumber(creditCardId);
        }

        // Set the credit card and product list in the order
        order.setCreditCardNumber(creditCardsNumber);
        order.setProduct(listOfProducts);

        // Clear any previous order references from the orderedList
        ArrayList<OrderedList> newOrderList = new ArrayList<>();
        for (OrderedList orderedList : orderList) {
            OrderedList newOrderedList = new OrderedList();
            newOrderedList.setProductName(orderedList.getProductName());
            newOrderedList.setImageUrl(orderedList.getImageUrl());
            newOrderedList.setType(orderedList.getType());
            newOrderedList.setPrice(orderedList.getPrice());
            newOrderedList.setQuantity(orderedList.getQuantity());
            newOrderedList.setDescription(orderedList.getDescription());
            newOrderedList.setCategoryId(orderedList.getCategoryId());
            newOrderedList.setOrder(order); // Set the new order reference
            newOrderList.add(newOrderedList);
        }

        order.setOrderedList(newOrderList);

        // Save order
        System.out.println("Saving order...");
        Orders savedOrder = orderRepository.save(order);

        for (OrderedList orderedList : savedOrder.getOrderedList()) {
            orderedList.setOrder(savedOrder);
            orderListRepository.save(orderedList);
        }

        // Update user's orders list
        List<Orders> orders = user.getOrdersList();
        if (orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(order);
        user.setOrdersList(orders);
        userRepository.save(user);

        // Update credit card's orders list
        List<Orders> creditCardOrders = creditCardsNumber.getOrders();
        if (creditCardOrders == null) {
            creditCardOrders = new ArrayList<>();
        }
        creditCardOrders.add(order);
        creditCardsNumber.setOrders(creditCardOrders);
        creditCardRespository.save(creditCardsNumber);

        // Update products' orders list
        for (Product product : order.getProduct()) {
            Product getProduct = productRepository.findById((int) product.getId()).orElseThrow(() -> new RuntimeException("Product not found"));
            List<Orders> productOrders = getProduct.getOrdersList();
            if (productOrders == null) {
                productOrders = new ArrayList<>();
            }
            productOrders.add(order);
            getProduct.setOrdersList(productOrders);
            productRepository.save(getProduct);
        }

        System.out.println("Order processed successfully.");
        return ResponseEntity.ok().body(savedOrder);
    }


}
