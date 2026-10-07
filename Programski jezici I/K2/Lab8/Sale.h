#pragma once
#include "Array.h"
#include "Item.h"

class Sale: public Object
{
protected:
    Array array;
public:
    Sale() {}
    void addItem(Item* item){
        array.append(item);
    }
    virtual std::string convertToString()const{
        std::string out = "Sale:\n";
        for(int i=0; i < array.getSize(); i++){
            out += std::to_string(i+1) + " " + array[i]->convertToString() + "\n";
        }
        return out;
    }

    int size()const{return array.getSize();}
    
	virtual double totalPrice()const{
        double sum = 0;
        for(int i=0; i < array.getSize(); i++){
            Item* item = (Item*)array[i];
            sum += item->getItemPrice();
        }
        return sum;
	}

    virtual Object* clone()const override{
        return new Sale(*this);
    }
    ~Sale() {}
};

