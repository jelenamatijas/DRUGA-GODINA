#pragma once
#include <string>

class IStringConvertable
{
public:
    virtual std::string convertToString()const = 0;
    virtual operator std::string()const final{
        return this->convertToString();
    }
};