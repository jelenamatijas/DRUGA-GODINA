#pragma once
#include "Operation.h"
#include <climits>

class Array
{
private:
    int capacity;
    int size;
    Vector2D *array;
public:
    static Vector2D ERROR;
    Array(int initialCapacity=1):capacity(initialCapacity>0?initialCapacity:1), size(0), array(new Vector2D[capacity]){}
    Array(const Array& other):Array(other.capacity){
        size = other.size;
        for(int i=0;i<size;i++){
            array[i] = other.array[i];
        }
    }
    Array(Array&& other){
        capacity = other.capacity;
        size = other.size;
        array = other.array;
        other.array = nullptr;
        other.size = other.capacity=0;
    }
    ~Array(){
        delete[] array;
    }

    Array& operator=(const Array& other){
        if(this!=&other){
            delete[] array;
            capacity = other.capacity;
            array = new Vector2D[capacity];
            size = other.size;
            for(int i=0;i<size;i++){
                array[i] = other.array[i];
            }
        }
        return *this; 
    }

    Array& operator=(Array&& other){
        if(this!=&other){
            delete[] array;
            capacity = other.capacity;
            array = other.array;
            size = other.size;
            other.array = nullptr;
            other.size = other.capacity=0;
        }
        return *this;
    }

    void insert(const Vector2D& v, int index){   
        if(size==capacity){
            resize(capacity*2);
        }

        for(int i=size;i>index;i--)
            array[i] = array[i-1];
        array[index] = v;
        size++;
        
    }

    bool remove(Vector2D& v, int index){
        if(size<=0 || index<0 || index>=size){
            v = ERROR;
            return false;
        }
        v = array[index];
        for(int i=index;i<size-1;i++)
            array[i] = array[i+1];
        size--;
        return true;
    }

    int getSize()const{return size;}

private:
    void resize(int newCapacity){
         Vector2D* newArr = new Vector2D[newCapacity];
        for(int i = 0; i < this->size; i++)
        {
            newArr[i] = array[i];
        }
        delete[] this->array;
        this->array = newArr;
        this->capacity = newCapacity;
    }
};

Vector2D Array::ERROR(INT_MIN, INT_MIN);