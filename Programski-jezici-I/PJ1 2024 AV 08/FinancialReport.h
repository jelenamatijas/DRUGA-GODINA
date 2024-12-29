#pragma once
#include "Sale.h"

class FinancialReport final : public Object
{
private:
    Array sales;
public:
    // NOTE: FinancialReport takes ownership of the sale, so it will be deleted when FinancialReport is deleted.
    void addSale(Sale* sale)
    {
        sales.append(sale);
    }
    int totalIncome() const
    {
        int sum = 0;
        for (int i = 0; i < sales.size(); i++)
        {
            Sale *sale = (Sale *)sales[i];
            sum += sale->totalPrice();
        }
        return sum;
    }
    std::string toString() const
    {
        std::string str = "Financial report: \n";
        for (int i = 0; i < sales.size(); i++)
        {
            Sale *sale = (Sale *)sales[i];
            str += std::to_string(i+1) + ". " + sale->toString() + "\n";
        }
        str += "Total income: " + std::to_string(totalIncome());
        return str;
    }

};
