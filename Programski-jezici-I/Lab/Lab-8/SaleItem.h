#pragma once
#include "Item.h"

class SaleItem:public Item
{
private:
    const Item& refernced;
    const int count;
public:
    SaleItem(Item& item, int count):refernced(item), count(count), Item(item.getName(), item.getPrice()*count) {}
    int getCount()const{
        return count;
    }

    virtual Object* clone()override{
        return new SaleItem(*this);
    }
};