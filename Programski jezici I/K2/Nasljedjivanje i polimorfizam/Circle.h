#pragma once
#include "Shape2D.h"

class Circle : public Shape2D
{
private:
    double r;
public:
    Circle(double r=0);
    
    virtual std::string type() const;

    virtual double calculatePerimeter() const;
    virtual double calculateArea() const;

    virtual Shape2D* clone() const{
        return new Circle(r);
    }

    virtual void rescaleInPlace(double scalar);

    ~Circle(){}
};

Circle::Circle(double r): r(r){}

std::string Circle::type() const{
    return "Circle";
}

double Circle::calculatePerimeter() const{
    return 2*3.14*r;
}

double Circle::calculateArea() const{
    return 3.14*r*r;
}

void Circle::rescaleInPlace(double scalar){
    r*=scalar;
}