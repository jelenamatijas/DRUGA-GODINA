#pragma once
#include <string>

class IColor
{
public:
    virtual std::string getColorName()const = 0;   
};

class Red: public IColor{
public:
    std::string getColorName()const{ return "Red";};
};

class Blue: public IColor{
public:
    std::string getColorName()const{ return "Blue";};
};

class Green: public IColor{
public:
    std::string getColorName()const{ return "Green";};
};