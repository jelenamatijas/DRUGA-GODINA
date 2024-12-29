#pragma once
#include"Tickets.h"
#include <cstdint>

class Concert:public Tickets
{
private:
    string concertName,date,artistName;
public:
    Concert(string, string, string, int64_t);
    double computePrice()const override;
};

Concert::Concert(string concertName="", string date="", string artistName="", int64_t price =0 ):Tickets(price),
                                                                                                concertName(concertName),
                                                                                                date(date),
                                                                                                artistName(artistName)
                                                                                                {}

double Concert::computePrice()const {
    return base_price + base_price*20/100;
}