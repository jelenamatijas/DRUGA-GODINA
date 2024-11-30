#pragma once
#include "Vector3D.h"
#include <climits>

class Stack
{
private:
    int capacity;
    int size;
    Vector3D *array;
public:
    static Vector3D ERROR;

    Stack(int initialCapacity=1):capacity(initialCapacity), size(0), array(new Vector3D[capacity]){}

    Stack(const Stack& other):Stack(other.capacity){
        while(size<other.size){
            array[size] = other.array[size];
            size++;
        }
    }

    Stack(Stack&& other){
        capacity = other.capacity;
        size = other.size;
        array = other.array;
        other.array = nullptr;
        other.capacity= other.size=0;
    }

    Stack& operator=(const Stack& other){
        delete[] array;
        capacity = other.capacity;
        array = new Vector3D[capacity];
        while(size<other.size){
            array[size] = other.array[size];
            size++;
        }
        return *this;
    }

    Stack& operator=(Stack&& other){
        delete[] array;
        capacity = other.capacity;
        size = other.size;
        array = other.array;
        other.array = nullptr;
        other.capacity= other.size=0;
        return *this;
    }

    ~Stack(){
        delete[] array;
    }

    void push(Vector3D v){
        if(size == capacity){
            resize(capacity*2);
        }
        array[size++] = v;
    }

    bool pop(Vector3D& v){
        if(size <=0){
            v= ERROR;
            return false;
        }
        v = array[--size];
        return true;
    }

    void forEach(void (*f)(const Vector3D& vec)){
        Vector3D pom;
        while(pop(pom)){
            f(pom);
        }
    }

private:
    void resize(int newCapacity){
        Vector3D *newArray = new Vector3D[newCapacity];
        for(int i=0;i<size;i++){
            newArray[i] = array[i];
        }
        capacity = newCapacity;
        delete[] array;
        array = newArray;
        newArray = nullptr;
    }
};

Vector3D Stack ::ERROR = Vector3D(INT_MIN, INT_MIN, INT_MIN);
