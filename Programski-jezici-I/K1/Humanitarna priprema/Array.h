#pragma once
#include "Operation.h"

class Array
{
private:
    int capacity;
    int size;
    Vector2D* arr;

public:
    Array(int capacity = 1) : capacity(capacity < 1 ? 1 : capacity),
                              size(0),
                              arr(new Vector2D[capacity]) {}
    ~Array()
    {
        this->dispose();
    }
    Array(const Array& other) : Array(other.capacity)
    {
        this->size = other.size;
        for(int i = 0; i < this->size; i++)
        {
            this->arr[i] = other.arr[i];
        }
    }
    Array(Array&& other)
    {
        this->capacity = other.capacity;
        this->size = other.size;
        this->arr = other.arr;
        other.arr = nullptr;
        other.size = other.capacity = 0;
    }
    Array& operator=(const Array& other)
    {
        if(this != &other)
        {
            this->capacity = other.capacity;
            this->size = other.size;
            delete[] this->arr;
            this->arr = new Vector2D[this->capacity];
            for(int i = 0; i < this->size; i++)
            {
                this->arr[i] = other.arr[i];
            }
        }
        return *this;
    }
    Array& operator=(Array&& other)
    {
        if(this != &other)
        {
            this->capacity = other.capacity;
            this->size = other.size;
            delete[] this->arr;
            this->arr = other.arr;
            other.arr = nullptr;
            other.size = other.capacity = 0;
        }
        return *this;
    }

    int getSize() const {return this->size;}
    int getCapacity() const {return this->capacity; }

    //Pretpostaviti da je indeks validan
    void insert(const Vector2D& vec, int position)
    {
        if(this->size >= this->capacity)
            this->realloc(2 * this->capacity);
        
        for(int i = this->size; i > position; i--)
        {
            this->arr[i] = this->arr[i-1];
        }
        this->arr[position] = vec;
        this->size++;
    }

    bool remove(Vector2D& result, int position)
    {
        if(position < 0 || position >= this->size || this->size == 0)
        {
            return false;
        }

        result = this->arr[position];
        for(int i = position; i < this->size; i++)
        {
            this->arr[i] = this->arr[i + 1];
        }

        this->size--;
        return true;
    }

private:
    void realloc(int newCapacity)
    {
        Vector2D* newArr = new Vector2D[newCapacity];
        for(int i = 0; i < this->size; i++)
        {
            newArr[i] = arr[i];
        }
        delete[] this->arr;
        this->arr = newArr;
        this->capacity = newCapacity;
    }

    void dispose()
    {
        delete[] this->arr;
    }
};