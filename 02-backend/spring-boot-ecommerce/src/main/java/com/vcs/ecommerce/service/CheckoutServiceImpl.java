package com.vcs.ecommerce.service;

import com.vcs.ecommerce.dao.CustomerRepository;
import com.vcs.ecommerce.dto.Purchase;
import com.vcs.ecommerce.dto.PurchaseResponse;
import com.vcs.ecommerce.entity.Customer;
import com.vcs.ecommerce.entity.Order;
import com.vcs.ecommerce.entity.OrderItem;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
//    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

            // retrieve the order from the dto
            Order order = purchase.getOrder();

            // generate tracking number
            String orderTrackingNumber = generateOrderTrackingNumber();
            order.setOrderTrackingNumber(orderTrackingNumber);

            // populate order with billingAddress and shippingAddress
            order.setShippingAddress(purchase.getShippingAddress());
            order.setBillingAddress(purchase.getBillingAddress());

            // populate order with order-items
            Set<OrderItem> orderItems = purchase.getOrderItems();
            orderItems.forEach(order::add);

            // populate customer with order
            Customer customer = purchase.getCustomer();
            customer.addOrder(order);

            // save to the database
            customerRepository.save(customer);

            // return a response
            return new PurchaseResponse(orderTrackingNumber);
    }
    private String generateOrderTrackingNumber() {

        // generate a random UUID
        return UUID.randomUUID().toString();
    }
}
