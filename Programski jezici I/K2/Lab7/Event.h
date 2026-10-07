#pragma once
#include <iostream>
#include <string>

class Event
{
protected:
    std::string name;
    std::string date;
public:
    Event():name(""), date(""){}
    Event(std::string name, std::string date):name(name), date(date){}

    std::string getEventName()const{
        return name;
    }

    std::string getDate()const{
        return date;
    }

    virtual double getBasePrice()const = 0;
    virtual double calculatePrice()const = 0;
    virtual ~Event() {}
};