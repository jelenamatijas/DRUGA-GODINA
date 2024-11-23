#pragma once

#include "Vector.h"

class Set
{
private:
    Vector *vectors;
    int numberOfVectors;
    int index;
public:
    Set(int n);
    ~Set();
    void add(const Vector&);
    bool equal(const Set&);
};

