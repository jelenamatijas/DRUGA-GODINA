#pragma once
#include "Stack.h"

class BoundedStack
{
private:
    int capacity;
    int size;
    Stack stack;
public:
    BoundedStack(int capacity = 1) : capacity(capacity), size(0)
    {
        this->stack = Stack();
    }

    void push(const Vector2D& vec)
    {
        if(size >= capacity)
        {
            std::cout << "Stack is full" << std::endl;
            return;
        }
        
        stack.push(vec);
        size++;
    }

    bool pop(Vector2D& vec)
    {
        bool result = stack.pop(vec);
        if(result)
            size--;
        return result;
    }
};
