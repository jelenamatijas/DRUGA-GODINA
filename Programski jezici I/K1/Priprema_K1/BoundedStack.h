#pragma once
#include "Stack.h"
#include <iostream>

class BoundedStack
{
private:
    int capacity;
    int size;
    Stack s;
public:
    BoundedStack(int initialCapacit=1):capacity(initialCapacit), size(0) {}
    void push(const Vector2D& v){
        if(size==capacity){
            std::cout<<"Stack over flow."<<std::endl;
            return;
        }
        s.push(v);
        size++;
    }

    bool pop(Vector2D& v){
        int result = s.pop(v);
        if(result)size--;
        return result;
    }
    ~BoundedStack() {}
};