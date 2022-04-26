package com.sipios.refactoring.service;


import com.sipios.refactoring.service.dto.Body;
import com.sipios.refactoring.service.dto.Item;

import com.sipios.refactoring.service.dto.TypeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
@Service
public class RoutingService {

    private double getDiscount(Body b) {
        if (b.getType().equals(TypeEnum.STANDARD_CUSTOMER)) {
            return 1;
        } else if (b.getType().equals(TypeEnum.PREMIUM_CUSTOMER)) {
            return 0.9;
        } else  {
            return 0.5;
        }
    }
    public double totalAmountByTypeQuantity(Body b){
    // Compute total amount depending on the types and quantity of product and
    // if we are in winter or summer discounts periods
        double d = getDiscount(b);
        double p = 0;
        Date date = new Date();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);

        if (
            !(
            cal.get(Calendar.DAY_OF_MONTH) < 15 &&
        cal.get(Calendar.DAY_OF_MONTH) > 5 &&
        cal.get(Calendar.MONTH) == 5
        ) &&
        !(
        cal.get(Calendar.DAY_OF_MONTH) < 15 &&
        cal.get(Calendar.DAY_OF_MONTH) > 5 &&
        cal.get(Calendar.MONTH) == 0
        )
        ) {
        if (b.getItems() == null) {
            return 0;
        }

        for (int i = 0; i < b.getItems().length; i++) {
            Item it = b.getItems()[i];

            if (it.getType().equals("TSHIRT")) {
                p += 30 * it.getNb() * d;
            } else if (it.getType().equals("DRESS")) {
                p += 50 * it.getNb() * d;
            } else if (it.getType().equals("JACKET")) {
                p += 100 * it.getNb() * d;
            }
            // else if (it.getType().equals("SWEATSHIRT")) {
            //     price += 80 * it.getNb();
            // }
        }
    } else {
        if (b.getItems() == null) {
            return 0;
        }

        for (int i = 0; i < b.getItems().length; i++) {
            Item it = b.getItems()[i];

            if (it.getType().equals("TSHIRT")) {
                p += 30 * it.getNb() * d;
            } else if (it.getType().equals("DRESS")) {
                p += 50 * it.getNb() * 0.8 * d;
            } else if (it.getType().equals("JACKET")) {
                p += 100 * it.getNb() * 0.9 * d;
            }
            // else if (it.getType().equals("SWEATSHIRT")) {
            //     price += 80 * it.getNb();
            // }
        }
    }

    return p;
}
}
