#pragma once
#include <string>
#include "Object.h"

class Item : public Object
{
protected:
    const std::string name;
    const int price;

public:
    Item(std::string name, int price) : name(name), price(price) {}
    int getPrice() const { return price; }
    std::string getName() const { return name; }
    std::string toString() const override
    {
        return name + " " + std::to_string(price);
    }

    virtual Object* clone()override{
        return new Item(*this);
    }
};