#pragma once

#include "Sale.h"

class Discount: public Sale
{
protected:
    Sale& discountSale;
    double discount;
public:
    Discount(Sale& sale, double discount): discountSale(sale), discount(discount) {}

    virtual double totalPrice()const override{
        return discountSale.totalPrice()*(1-discount);
    }

    virtual std::string convertToString()const{
        std::string str = discountSale.convertToString() + "\n Discount: "+std::to_string(discount*100)+"\n";
        str += "Total discount prie: "+std::to_string(totalPrice()) + "\n";
        return str;
    }

    virtual Object* clone()const override{
        return new Discount(*this);
    }

    ~Discount() {}
};