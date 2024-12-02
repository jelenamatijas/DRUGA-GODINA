#pragma once
#include <iostream>
#include <cmath>

class Vector2D
{
private:
    int x, y;
public:
    Vector2D(int x=0, int y=0):x(x), y(y){}
    int getX()const{return x;}
    int getY()const{return y;}
    void setX(int x){this->x = x;}
    void setY(int y){this->y = y;}

    Vector2D operator+(const Vector2D& other)const{
        return Vector2D(x+other.x, y+other.y);
    }

    Vector2D& operator+=(const Vector2D& other){
        x+=other.x;
        y+=other.y;
        return *this;
    }

    Vector2D operator-(const Vector2D& other)const{
        return Vector2D(x-other.x, y-other.y);
    }

    Vector2D& operator-=(const Vector2D& other){
        x-=other.x;
        y-=other.y;
        return *this;
    }

    bool operator==(const Vector2D& other)const{
        return (x==other.x && y==other.y);
    }

    bool operator!=(const Vector2D& other)const{
        return !(*this==other);
    }

    Vector2D operator*(int scalar)const{
        return Vector2D(x*scalar, y*scalar);
    }
    Vector2D& operator*=(int scalar){
        x*=scalar;
        y*=scalar;
        return *this;
    }

    Vector2D max(const Vector2D& other)const{
        return Vector2D(std::max(x, other.x),std::max(y, other.y));
    }

};

Vector2D& operator*=(int scalar, const Vector2D& other){
    return other*scalar;
}

std::ostream& operator<<(std::ostream& out, const Vector2D& v){
    out<<"("<<v.getX()<<","<<v.getY()<<")"<<std::endl;
    return out;
}

std::istream& operator>>(std::istream& in, Vector2D& v){
    int x, y;
    in>>x>>y;
    v.setX(x);
    v.setY(y);
    return in;
}

Vector2D max(const Vector2D& v1,const Vector2D& v2){
    return Vector2D(std::max(v1.getX(), v2.getX()),std::max(v1.getY(), v2.getY()));
}