#pragma once

#include "Complex.h"

class Vector{
    Complex *array;
    int capacity;
    int index;
public:

    Vector(int N=0);
    Vector(const Vector&)=delete;
    Vector(Vector&&)=delete;
    Vector& operator=(const Vector&);
    Vector& operator=(Vector &&)=delete;
    ~Vector();

    bool equalVectors(const Vector&)const;

    int get_capacity()const;
    int get_index()const;
    bool push(const Complex&);
};