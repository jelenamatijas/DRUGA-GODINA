#pragma once
#include <cstdint>

#include <iostream>
#include <string>

using namespace std;

class Tickets
{
protected:
    int64_t base_price;
public:
    Tickets(int64_t);
    virtual double computePrice()const;
};

Tickets::Tickets(int64_t base_price=0):base_price(base_price){}

double Tickets::computePrice()const{
    return this->computePrice();
}
