#pragma once
#include "Shape2D.h"

class Square : public Shape2D
{
private:
    double a;
public:
    Square(double a=0);
    
    virtual std::string type() const;

    virtual double calculatePerimeter() const;
    virtual double calculateArea() const;

    virtual Shape2D* clone() const{
        return new Square(a);
    }

    virtual void rescaleInPlace(double scalar);

    ~Square(){}
};

Square::Square(double a): a(a){}

std::string Square::type() const{
    return "Square";
}

double Square::calculatePerimeter() const{
    return a*4;
}

double Square::calculateArea() const{
    return a*a;
}

void Square::rescaleInPlace(double scalar){
    a*=scalar;
}