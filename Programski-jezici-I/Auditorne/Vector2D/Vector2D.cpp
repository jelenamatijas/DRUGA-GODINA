#include "Vector2D.h"
#include "iostream"

Vector2D::Vector2D(double x, double y):x(x), y(y){}

double Vector2D::getX() const
{
    return x;
}

double Vector2D::getY() const
{
    return y;
}

void Vector2D::setX(double x)
{
    this->x = x;
}

void Vector2D::setY(double y)
{
    this->y = y;
}

bool Vector2D::equals(const Vector2D& other) const
{
    return (x==other.getX() && y==other.getY());
}

void Vector2D::add(const Vector2D &other)
{
    x+=other.getX();
    y+=other.getY();
}

Vector2D Vector2D::add_(const Vector2D &other) const
{
    return Vector2D(x+other.getX(), y+other.getY());
}

void Vector2D::subtract(const Vector2D &other)
{
    x-=other.getX();
    y-=other.getY();
}

Vector2D Vector2D::subtract_(const Vector2D &other) const
{
    return Vector2D(x-other.getX(), y-other.getY());
}

void Vector2D::print() const
{
    std::cout<<"("<<this->getX()<<","<<this->getY()<<")\n";
}
