#pragma once

#include "Item.h"

class SaleItem: public Item
{
private:
    const Item& saleItem;
    const double count;
public:
    SaleItem(const Item& saleItem, const double count=1):saleItem(saleItem), count(count) {}
    const double getCount()const {
        return count;
    }

    virtual Object* clone()const override{
        return new SaleItem(*this);
    }
};