#pragma once
#include <iostream>
#include <string>

class Shape2D
{
public:
    virtual std::string type() const = 0;

    virtual double calculatePerimeter() const = 0;
    virtual double calculateArea() const = 0;

    virtual Shape2D* clone() const = 0;

    virtual void rescaleInPlace(double scalar) = 0;
    virtual Shape2D* rescale(double scalar)const;

    virtual Shape2D& operator*=(double scalar);
    virtual Shape2D* operator*(double scalar)const;

    virtual ~Shape2D(){};
};

Shape2D* Shape2D::rescale(double scalar)const{
    Shape2D* newShape = clone();
    newShape->rescaleInPlace(scalar);
    return newShape;
}

Shape2D& Shape2D::operator*=(double scalar){
    this->rescaleInPlace(scalar);
    return *this;
}

Shape2D* Shape2D::operator*(double scalar)const{
    return this->rescale(scalar);
}