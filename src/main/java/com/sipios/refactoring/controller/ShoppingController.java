package com.sipios.refactoring.controller;

import com.sipios.refactoring.service.RoutingService;
import com.sipios.refactoring.service.dto.Body;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    private Logger logger = LoggerFactory.getLogger(ShoppingController.class);
    private final RoutingService routingService ;

    public ShoppingController(RoutingService routingService) {
        this.routingService = routingService;
    }

    @PostMapping
    public String getPrice(@RequestBody Body b) {
        double totalAmount = 0;
        double d;

        // Compute discount for customer
        if (b.getType().equals("STANDARD_CUSTOMER")) {
            d = 1;
        } else if (b.getType().equals("PREMIUM_CUSTOMER")) {
            d = 0.9;
        } else if (b.getType().equals("PLATINUM_CUSTOMER")) {
            d = 0.5;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        totalAmount = routingService.totalAmountByTypeQuantity(b,d);

        try {
            if (b.getType().equals("STANDARD_CUSTOMER")) {
                if (totalAmount > 200) {
                    throw new Exception("Price (" + totalAmount + ") is too high for standard customer");
                }
            } else if (b.getType().equals("PREMIUM_CUSTOMER")) {
                if (totalAmount > 800) {
                    throw new Exception("Price (" + totalAmount + ") is too high for premium customer");
                }
            } else if (b.getType().equals("PLATINUM_CUSTOMER")) {
                if (totalAmount > 2000) {
                    throw new Exception("Price (" + totalAmount + ") is too high for platinum customer");
                }
            } else {
                if (totalAmount > 200) {
                    throw new Exception("Price (" + totalAmount + ") is too high for standard customer");
                }
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return String.valueOf(totalAmount);
    }
}

