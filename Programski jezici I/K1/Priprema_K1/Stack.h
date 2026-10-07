#pragma once
#include "Array.h"

class Stack
{
private:
    Array a;
public:
    Stack() {}
    void push(const Vector2D& v){
        a.insert(v, a.getSize());
    }

    bool pop(Vector2D& v){
        return a.remove(v,a.getSize()-1);
    }
};