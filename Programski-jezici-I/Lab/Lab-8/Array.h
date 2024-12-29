#pragma once
#include "Object.h"

class Array : virtual public Object
{
private:
    Object **arr;
    size_t arrLen;

public:
    Array() : arr(nullptr), arrLen(0) {}
    Array(const Array & other):arrLen(other.arrLen){
        arr = new Object*[arrLen];
        for(int i=0;i<arrLen;i++){
            arr[i] = other.arr[i]? other.arr[i]->clone():nullptr;
        }
    }
    Array(Array &&other) : arr(other.arr), arrLen(other.arrLen)
    {
        other.arr = nullptr;
        other.arrLen = 0;
    }
    Array &operator=(const Array &other){
        if(this == &other){
            return *this;
        }
        for (int i = 0; i < arrLen; i++)
        {
            delete arr[i];
        }
        delete[] arr;

        arr = new Object*[other.arrLen];
        arrLen = other.arrLen;
        for(int i=0;i<arrLen;i++){
            arr[i] = other.arr[i]? other.arr[i]->clone():nullptr;
        }
        
        return *this;
    }
    Array &operator=(Array &&other)
    {
        if (this != &other)
        {
            for (int i = 0; i < arrLen; i++)
            {
                delete arr[i];
            }
            delete[] arr;
            arr = other.arr;
            arrLen = other.arrLen;
            other.arr = nullptr;
            other.arrLen = 0;
        }
        return *this;
    }
    ~Array()
    {
        for (int i = 0; i < arrLen; i++)
        {
            delete arr[i];
        }
        delete[] arr;
    }
    // NOTE: object will be deleted in destructor
    virtual void append(Object *object)
    {
        if (this->arr == nullptr)
            this->arr = new Object *[1];
        else
            realloc(this->arrLen + 1);
        this->arr[arrLen] = object;
        arrLen++;
    }
    virtual int size() const
    {
        return this->arrLen;
    }
    virtual Object *operator[](size_t index)
    {
        return this->arr[index];
    }
    virtual const Object *operator[](size_t index) const
    {
        return this->arr[index];
    }

    virtual Object* clone()override{
        return new Array(*this);
    }

private:
    void realloc(size_t newCapacity)
    {
        Object **newArr = new Object *[newCapacity];
        size_t newLength = this->arrLen < newCapacity ? this->arrLen : newCapacity;
        for (int i = 0; i < newLength; i++)
        {
            newArr[i] = this->arr[i];
        }
        delete[] this->arr;
        this->arr = newArr;
    }
};