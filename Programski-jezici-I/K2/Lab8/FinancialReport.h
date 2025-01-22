#pragma once
#include "Sale.h"
#include "Item.h"

class FinancialReport final: public Object
{
private:
    Array array;
public:
    void addSale(Sale* sale){
        array.append(sale);
    }

    double totalIncome()const{
        double sum=0;
        for(int i=0; i< array.getSize(); i++){
            Sale *sale = (Sale*)array[i];
            sum += sale->totalPrice();
        }
        return sum;
    }

    std::string convertToString() const
    {
        std::string str = "Financial report: \n";
        for (int i = 0; i < array.getSize(); i++)
        {
            Sale *sale = (Sale *)array[i];
            str += std::to_string(i+1) + ". " + sale->convertToString() + "\n";
        }
        str += "Total income: " + std::to_string(totalIncome());
        return str;
    }

    void print(std::ostream& out) const override {
        out << convertToString();
    }

    virtual Object* clone()const override{
        return new FinancialReport(*this);
    }
};
