#include "Vector2D.h"

Vector2D::Vector2D(double x, double y):x(x), y(y){}

Vector2D Vector2D::operator+=(const Vector2D &other)
{
    x+=other.getX();
    y+=other.getY();
    return *this;
}

Vector2D Vector2D::operator+(const Vector2D &other) const
{
    return Vector2D(x+other.getX(), y+other.getY());
}

void Vector2D::setX(double x)
{
    this->x = x;
}

void Vector2D::setY(double y)
{
    this->y = y;
}

double Vector2D::getX() const
{
    return x;
}

double Vector2D::getY() const
{
    return y;
}
