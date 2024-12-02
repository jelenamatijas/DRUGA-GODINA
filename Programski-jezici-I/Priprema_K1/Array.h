#pragma once
#include "Operation.h"

class Array
{
private:
    int capacity;
    int size;
    Vector2D *array;
public:
    Array(int initialCapacity=1):capacity(initialCapacity), size(0), array(new Vector2D[capacity]){}
    ~Array(){
        delete[] array;
    }

    void insert(const Vector2D& v, int index){
        if(size==capacity){
            resize(capacity*2);
        }

        for(int i=size;i>index;i--)
        
    }
private:
    void resize(int newCapacity){
        Vector2D* newArray = new Vector2D[newCapacity];
        capacity*=2;
        for(int i=0;i<size;i++)
            newArray[i]=array[i];
        delete[] array;
        array = newArray;
        newArray = nullptr;
    }
};

