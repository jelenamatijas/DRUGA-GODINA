#pragma once
#include "Sale.h"

class Discount:public Sale
{
protected:
    const Sale& sale;
    double discount;
public:
    Discount(const Sale& sale, double discount):sale(sale), discount(discount) {}

    int totalPrice()const override{
        return (int)(sale.totalPrice()*(1-discount));
    }
    
    std::string toString()const{
        std::string str = sale.toString() + "Discount: " + std::to_string(discount*100) + "%";
        str+="Total price with discount: " + std::to_string(totalPrice());
        return str;
    }

    virtual Object* clone()override{
        return new Discount(*this);
    }
};