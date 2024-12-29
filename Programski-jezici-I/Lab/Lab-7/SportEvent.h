#pragma once
#include"Tickets.h"

class SportEvent:public Tickets
{
private:
    string sportName, date;
    bool isVIP;
public:
    SportEvent(string, string,bool, int64_t);
    double computePrice()const override;
};

SportEvent::SportEvent(string name = "", string date = "", bool isVIP = false, int64_t price = 0):Tickets(price),
                                                                                                sportName(name),
                                                                                                date(date),
                                                                                                isVIP(isVIP)
                                                                                                {}

double SportEvent::computePrice()const{
    return isVIP ? base_price + 50 : base_price;
}