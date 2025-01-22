#pragma once
#include "Shape2D.h"

class Rectangle : public Shape2D
{
private:
    double a,b;
public:
    Rectangle(double a=0, double b=0);
    
    virtual std::string type() const;

    virtual double calculatePerimeter() const;
    virtual double calculateArea() const;

    virtual Shape2D* clone() const;

    virtual void rescaleInPlace(double scalar);
    ~Rectangle(){}
};

Rectangle::Rectangle(double a, double b): a(a), b(b){}

std::string Rectangle::type() const{
    return "Rectangle";
}

double Rectangle::calculatePerimeter() const{
    return 2*a+2*b;
}

double Rectangle::calculateArea() const{
    return a*b;
}

Shape2D* Rectangle::clone() const{
    return new Rectangle(a,b);
}

void Rectangle::rescaleInPlace(double scalar){
    a*=scalar;
    b*=scalar;
}