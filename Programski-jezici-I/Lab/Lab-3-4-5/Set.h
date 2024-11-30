#pragma once
#include "Vector.h"
#include <iostream>

class Set
{
private:
    int capacity;
    int size;
    Vector *array;
public:
    Set(int initialCapacity=1):capacity(initialCapacity), size(0), array(new Vector[capacity]){}
    Set(const Set& other):Set(other.capacity){
        size = other.size;
        for(int i=0;i<size;i++){
            array[i] = other.array[i];
        }
    }
    Set(Set&& other){
        capacity = other.capacity;
        size = other.size;
        array = other.array;
        other.array = nullptr;
        other.capacity = other.size =0;
    }

    Set& operator=(const Set& other){
        if(this != &other){
            delete[] array;
            capacity = other.capacity;
            size = other.size; 
            array=new Vector[capacity];
            for(int i=0;i<size;i++){
                array[i] = other.array[i];
            }
        }
        return *this;
    }

    Set& operator=(Set&& other){
        if(this != &other){
            delete[] array;
            capacity = other.capacity;
            size = other.size;
            array = other.array;
            other.array = nullptr;
            other.capacity = other.size =0;
        }
        return *this;
    }

    ~Set(){
        delete[] this->array;
    }
    void add(const Vector& v){
        for(int i=0;i<size;i++){
            if(array[i]==v)
                return;
        }
        if(size==capacity){
            capacity*=2;
            Vector *newArray = new Vector[capacity];
            for(int i=0;i<size;i++){
                newArray[i] = array[i];
            }
            delete[] array;
            array = newArray;
            newArray = nullptr;
        }
        array[size++] = v;
    }

    Set operator+(const Set& other)const{
        Set s(size+other.size);
        for(int i=0;i<size;i++)
            s.add(array[i]);
        for(int i=0;i<other.size;i++){
            s.add(other.array[i]);
        }
        return s;
    }

    Set& operator+=(const Set& other){
         for(int i=0;i<other.size;i++){
            this->add(other.array[i]);
        }
         return *this;
    }

    bool equals(const Set& other)const{
        if(size != other.size)
            return false;
        for(int i=0;i<size;i++){
            if(array[i] != other.array[i])
                return false;
        }
        return true;
    }

    int getSize()const{return size;}
    //Pretpostaviti da korisnik brine o korektnosti podataka koje prosljedjuje u funkciju
    Vector& getElement(int index)const{return array[index];}
};

std::ostream& operator<<(std::ostream& out, const Set& s){
    for(int i=0;i<s.getSize();i++){
        out<<s.getElement(i);
    }
    return out;
}
