#include "Vector.h"

Vector::Vector(int N):capacity(N){
    if(capacity>0){
        array = new Complex[capacity];
        index = 0;
    }else{
        capacity = 0;
        index = 0 ;
        array = nullptr;
    }
}

Vector& Vector::operator=(const Vector& v){
    if(!this->array)delete[] this->array;
    this->capacity = v.capacity;
    this->index = v.index;
    this->array = new Complex[capacity];
    for(int i=0;i<index;i++){
        this->array[i] = v.array[i];
    }
    return *this;
}

Vector::~Vector(){
    delete[] array;
}

bool Vector::equalVectors(const Vector& v)const{
    if(this->index!=v.index)return false;
    else{
        for(int i=0;i<v.index;i++){
            if(this->array[i].equalCoordinates(v.array[i]) == false)return false;
        }
        return true;
    }
}

int Vector::get_capacity()const{
    return capacity;
}
int Vector::get_index()const{
    return index;
}
bool Vector::push(const Complex& c){
    if(index == capacity){
        return false;
    }else{
        array[index++] = c;
        return true;
    }
}