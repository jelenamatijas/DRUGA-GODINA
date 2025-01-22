#pragma once
#include "Object.h"

class Array
{
private:
    int size;
    int capacity;
    Object** objects;

    void resize(int newCapacity){
        Object** newObjects = new Object*[newCapacity];
        for(int i=0;i<size;i++){
            newObjects[i] = objects[i];
        }
        delete[] objects;
        objects = newObjects;
        newObjects = nullptr;
    }
public:
    Array(): size(0), capacity(0), objects(nullptr){}

    Array(const Array& other):size(other.size), capacity(other.capacity){
        objects = new Object*[capacity];
        for(int i=0;i<size;i++){
            objects[i] = other.objects[i]->clone();
        }
    }
    Array& operator=(const Array& other){
        if(this != &other){
            for(int i=0;i<size;i++)
                delete objects[i];
            delete []objects;

            size = other.size;
            capacity = other.capacity;
            objects = new Object*[capacity];
            for(int i=0;i<size;i++){
                objects[i] = other.objects[i]->clone();
            }
        }
        return *this;
    }

    Array(Array&& other):size(size), capacity(other.capacity), objects(objects){
        other.objects = nullptr;
        other.size = other.capacity = 0;
    }

    Array& operator=(Array&& other){
        if(this!= &other){
            objects = new Object*[other.capacity];
            size = other.size;
            capacity = other.capacity;
            for(int i=0;i<size;i++){
                objects[i] = other.objects[i];
            }
            other.objects = nullptr;
            other.size = other.capacity = 0;
        }
        return *this;
    }

    virtual void append(Object* obj){
        if(capacity == 0){
            resize(capacity++);
        }else if(size == capacity){
            capacity*=2;
            resize(capacity);
        }
        objects[size] = obj; 
    }

    Object* operator[](int index)const{
        return objects[index];
    }

    int getSize()const{
        return size;
    }

    ~Array() {
        for(int i=0;i<size;i++){
            delete objects[i];
        }
        delete []objects;
    }
};