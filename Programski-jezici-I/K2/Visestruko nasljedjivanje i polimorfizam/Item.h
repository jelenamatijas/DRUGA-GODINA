#pragma once
#include "Object.h"

class Item: public Object
{
protected:
    std::string itemName;
    double itemPrice;
public:
    Item(std::string name = "", double price = 0):itemName(name), itemPrice(price) {}
    std::string getItemName()const{
        return itemName;
    }
    double getItemPrice()const{
        return itemPrice;
    }

    std::string convertToString()const override{
        return itemName+ " " + std::to_string(itemPrice);
    }
    ~Item() {}
};