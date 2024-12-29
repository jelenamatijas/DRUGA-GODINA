#pragma once
#include "Object.h"

class Item:public Object
{
private:
    const std::string name;
    const int price;
public:
    Item(const std::string name="", const int price=0):name(name), price(price) {}

    std::string getName()const{
        return name;
    }
    int getPrice()const{
        return price;
    }

    std::string toString()const override{
        return name + " " + std::to_string(price);
    }
};