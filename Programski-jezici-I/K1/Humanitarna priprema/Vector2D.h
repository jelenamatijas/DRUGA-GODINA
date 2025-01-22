#pragma once
#include <iostream>

class Vector2D
{
private:
    int x;
    int y;

public:
    Vector2D(int x = 0, int y = 0) : x(x), y(y) {}
    
    int getX () const {return x;}
    int getY () const {return y;}

    void setX(int x) {this->x = x;}
    void setY(int y) {this->y = y;}

    Vector2D operator+(const Vector2D& other) const
    {
        return Vector2D(this->x + other.x, this->y + other.y);
    }

    Vector2D& operator+=(const Vector2D& other)
    {
        this->x += other.x;
        this->y += other.y;
        return *this;
    }

    Vector2D& operator-=(const Vector2D& other)
    {
        this->x -= other.x;
        this->y -= other.y;
        return *this;
    }

    Vector2D operator-(const Vector2D& other) const
    {
        return Vector2D(this->x - other.x, this->y - other.y);
    }

    int operator*(const Vector2D& other) const
    {
        return this->x * other.x + this->y * other.y;
    }

    bool operator==(const Vector2D& other) const
    {
        return this->x == other.x && this->y == other.y;
    }

    Vector2D operator*(int scalar) const
    {
        return Vector2D(this->x * scalar, this->y * scalar);
    }

    Vector2D max(const Vector2D& other) const
    {
        return Vector2D(this->x > other.x ? this->x : other.x,
                        this->y > other.y ? this->y : other.y);
    }
};

Vector2D operator*(int scalar, const Vector2D& vector)
{
    return vector * scalar;
}

std::ostream& operator<<(std::ostream& out, const Vector2D& vec)
{
    out << "(" << vec.getX() << ", " << vec.getY() << ")";
    return out;
}

std::istream& operator>>(std::istream& in,Vector2D& vec)
{
    int x,y;
    in >> x >> y;
    vec.setX(x);
    vec.setY(y);
    return in;
}

