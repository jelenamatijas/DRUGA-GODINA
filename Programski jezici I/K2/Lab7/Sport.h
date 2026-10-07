#pragma once
#include "Event.h"

class Sport: public Event
{
private:
    double basePrice;
    bool isVIP;
public:
    Sport():Event(), basePrice(0), isVIP(false) {}
    Sport(std::string name, std::string date, double basePrice, bool isVIP):Event(name, date),
                                                                          basePrice(basePrice),
                                                                          isVIP(isVIP) {}

    double getBasePrice()const override {
        return basePrice;
    }

    double calculatePrice()const override {
        return isVIP ? basePrice+50 : basePrice;
    }
    ~Sport() {}
};