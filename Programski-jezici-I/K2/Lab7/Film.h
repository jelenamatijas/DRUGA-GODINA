#pragma once
#include "Event.h"

class Film: public Event
{
private:
    double basePrice;
    bool is3D;
public:
    Film():Event(), basePrice(0), is3D(false) {}
    Film(std::string name, std::string date, double basePrice, bool is3D):Event(name, date),
                                                                          basePrice(basePrice),
                                                                          is3D(is3D) {}

    double getBasePrice()const override {
        return basePrice;
    }

    double calculatePrice()const override {
        return is3D ? basePrice+5 : basePrice;
    }
    ~Film() {}
};