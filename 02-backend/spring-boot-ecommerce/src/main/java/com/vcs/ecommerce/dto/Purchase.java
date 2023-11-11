package com.vcs.ecommerce.dto;

import com.vcs.ecommerce.entity.Address;
import com.vcs.ecommerce.entity.Customer;
import com.vcs.ecommerce.entity.Order;
import com.vcs.ecommerce.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
public class Purchase {

    private Customer customer;

    private Address shippingAddress;

    private Address billingAddress;

    private Order order;

    private Set<OrderItem> orderItems;
}
