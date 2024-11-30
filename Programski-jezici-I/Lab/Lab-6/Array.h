#pragma once
#include "Complex.h"
#include <iostream>
#include <climits>

class Array
{
private:
    int capacity;
    int size;
    Complex *array;
public:
    Array(int capacity=1):capacity(capacity), size(0), array(new Complex[capacity]){}
    Array(const Array& other)=delete;
    Array(Array&& other):Array(other.capacity){
        while(size<other.size){
            array[size] = other.array[size];
            size++;
        }
        other.array = nullptr;
        other.capacity = other.size = 0;
    }

    Array& operator=(const Array& other) = delete;
    Array& operator=(Array&& other){
        capacity = other.capacity;
        size = other.size;
        array = other.array;
        other.array = nullptr;
        other.capacity = other.size = 0;
        return *this;
    } 

    static Complex ERROR;

    int getSize()const{return size;}
    Complex getElement(int index)const{
        if(index<size && index>=0)
            return array[index];
        return ERROR;
    }

    void append(const Complex& c){
        if(size==capacity){
            Complex *newArray = new Complex[capacity*2];
            capacity*=2;
            for(int i=0;i<size;i++){
                newArray[i] = array[i];
            }
            delete[] array;
            array = newArray;
            newArray = nullptr;
        }
        array[size++] = c;
    }
    //Korisnik je odgovoran za pravilno koristenje funkcije
    Complex& at(int index){
        return array[index];
    }

    const Complex& at(int index)const{
        return array[index];
    }

    Array transform(Complex (*f)(Complex c))const{
        Array newArray(this->capacity);
        newArray.size = this->size;
        for(int i=0;i<size;i++)
            newArray.array[i] = f(array[i]);
        return newArray;
    }

    ~Array(){
        delete[] array;
    }
};

Complex Array::ERROR(INT_MIN, INT_MIN);

std::ostream& operator<<(std::ostream& out, const Array& arr){
    for(int i=0;i<arr.getSize();i++)
        out<<arr.getElement(i);
    out<<std::endl;
    return out;
}
