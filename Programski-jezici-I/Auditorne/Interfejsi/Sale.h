#pragma once
#include "Array.h"
#include "Item.h"

class Sale final: public Object
{
private:
    Array items;
public:
    void addItem(Item *item){
        items.apend(item);
    }

    int totalPrice()const{
        int sum = 0;
        for(int i=0;i<items.getLenght();i++){
            Item* item = (Item*)items[i];
            sum+=item->getPrice();
        }
        return sum;
    }

    std::string toString()const {
        std::string str = "Sale:\n";
        for(int i=0;i<items.getLenght();i++){
            Item* item = (Item*)items[i];
            str+= " " + std::to_string(i+1) + ".: " + item->toString() + "\n";
        }
        str+= "Total price: " + std::to_string(totalPrice()) + "\n";
        return str;
    }
};