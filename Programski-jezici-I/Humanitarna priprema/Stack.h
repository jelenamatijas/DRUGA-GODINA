#pragma once
#include "Array.h"

class Stack
{
private:
    Array stack;
public:
    Stack()
    {
        this->stack = Array();
    }
    
    void push(const Vector2D& vec)
    {
        stack.insert(vec, stack.getSize());
    }

    bool pop(Vector2D& vec)
    {
        bool result = stack.remove(vec, stack.getSize() - 1);
        return result;
    }
};
