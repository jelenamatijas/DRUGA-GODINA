#pragma once
#include "Vector2D.h"

class Operation
{
private:
    const Vector2D &v1;
    const Vector2D &v2;
    Vector2D (*f)(const Vector2D& v1,const Vector2D& v2);
public:
    Operation(const Vector2D& l,const Vector2D& r, Vector2D (*f)(const Vector2D& v1,const Vector2D& v2)):v1(l), v2(r),f(f){}
    const Vector2D get_v1()const{return v1;}
    const Vector2D get_v2()const{return v2;}

    Vector2D execute(){
       return f(v1,v2);
    }
};
