#pragma once
#include"Tickets.h"

class MovieEvent:public Tickets
{
private:
    string movieName, date;
    bool is3D;
public:
    MovieEvent(string, string, bool, int64_t);
    double computePrice()const override;
};

MovieEvent::MovieEvent(string name = "", string date = "", bool is3D = false, int64_t price = 0):Tickets(price),
                                                                                                movieName(name),
                                                                                                date(date),
                                                                                                is3D(is3D)
                                                                                                {}

double MovieEvent::computePrice()const{
    return is3D ? base_price + 5 : base_price;
}