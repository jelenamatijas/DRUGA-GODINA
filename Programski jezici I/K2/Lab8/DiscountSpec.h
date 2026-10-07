#pragma once

#include "Discount.h"

class DiscountSpec: public Discount
{
private:
    int limit;
public:
    DiscountSpec(Sale &sale, double discount,int limit):Discount(sale, discount), limit(limit) {}

    double totalPrice()const override{
        double sum=0;
        if(discountSale.size()>=limit){
            sum = discountSale.totalPrice()*(1-discount);
        }
        return sum;
    }
    ~DiscountSpec() {}
};