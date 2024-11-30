#pragma once
#include "Complex.h"
#include <iostream>

class Vector
{
private:
    int dimension;
    int size;
    Complex *array;
public:
    static Complex ERROR_COMPLEX;
    Vector(int n=1):dimension(n),size(0), array(new Complex[dimension]){}
    Vector(const Vector& other):Vector(other.dimension){
        size = other.size;
        for(int i=0;i<dimension;i++)
            array[i] = other.array[i];
    }
    Vector(Vector&& other){
        dimension = other.dimension;
        size = other.size;
        array = other.array;
        other.array = nullptr;
        other.dimension = other.size = 0;
    }
    Vector& operator=(const Vector& other){
        if(this != &other){
            delete []array;
            dimension = other.dimension;
            size = other.size;
            array = new Complex[dimension];
            for(int i=0;i<dimension;i++)
                array[i] = other.array[i];
        }
        return *this;
    }
    Vector& operator=(Vector&& other){
        if(this != &other){
            delete []array;
            dimension = other.dimension;
            size = other.size;
            array = other.array;
            other.array = nullptr;
            other.dimension = other.size = 0;
        }
        return *this;
    }
    ~Vector(){delete[] this->array;}
    
    bool operator==(const Vector& other)const{
        if(dimension!= other.dimension)
            return false;
        for(int i=0;i<dimension;i++){
            if(other.array[i] != array[i])
                return false;
        }
        return true;
    }

    Vector operator+(const Vector& other)const{
        if(dimension!=other.dimension){
            Vector v(1);
            v.addElement(ERROR_COMPLEX);
            return v;
        }
        Vector v(dimension);
        for(int i=0;i<dimension;i++){
            v.array[i] = array[i]+other.array[i];
        }
        return v;
    }

    Vector& operator+=(const Vector& other){
        if(dimension==other.dimension){
            for(int i=0;i<dimension;i++){
                array[i]+= other.array[i];
            }
        }
        return *this;
    }

    Vector operator*(double scalar)const{
        Vector v(dimension);
        for(int i=0;i<dimension;i++){
            v.array[i] = array[i]*scalar;
        }
        return v;
    }

    void addElement(const Complex& c){
        array[size++] = c;
    }

    bool operator!=(const Vector& other)const{
        return !(*this==other);
    }

    //Pretpostaviti da korisnik pravilno koristi funkciju
    Complex getElement(int index)const{return array[index];}
    int getDimension()const{return dimension;}
};

Vector operator*(double scalar, const Vector& v){
    return v*scalar;
}

std::ostream& operator<<(std::ostream& out, const Vector& v){
    for(int i=0;i<v.getDimension();i++){
        out<<v.getElement(i);
    }
    return out;
}

Complex Vector::ERROR_COMPLEX(INT_MIN, INT_MIN);