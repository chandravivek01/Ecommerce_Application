package com.vcs.ecommerce.service;

import com.vcs.ecommerce.dto.Purchase;
import com.vcs.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
