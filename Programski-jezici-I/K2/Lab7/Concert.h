#pragma once
#include "Event.h"

class Concert: public Event
{
private:
    double basePrice;
    std::string nameOfSinger;
public:
    Concert():Event(), basePrice(0), nameOfSinger("") {}
    Concert(std::string name, std::string date, double basePrice, std::string nameOfSinger):Event(name, date),
                                                                          basePrice(basePrice),
                                                                          nameOfSinger(nameOfSinger) {}

    double getBasePrice()const override {
        return basePrice;
    }

    double calculatePrice()const override {
        return basePrice + basePrice*0.2;
    }
    ~Concert() {}
};