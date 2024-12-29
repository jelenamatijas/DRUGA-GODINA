#pragma once
#include "Sale.h"

class FinancialReport:public Object
{
private:
    Array sales;
public:
    void addSale(Sale* s){
        sales.apend(s);
    }

    int totalIncome()const{
        int sum=0;
        for(int i=0;i<sales.getLenght();i++){
            Sale* sale = (Sale*)sales[i];
            sum+=sale->totalPrice();
        }
        return sum;
    }

    std::string toString()const{
        std::string str = "Financial report:\n";
        for(int i=0;i<sales.getLenght();i++){
            Sale* sale = (Sale*)sales[i];
            str+=std::to_string(i+1) + " " + sale->toString() + "\n";
        }
        str+="Total income: "+std::to_string(totalIncome()) + "\n";
        return str;
    }
};