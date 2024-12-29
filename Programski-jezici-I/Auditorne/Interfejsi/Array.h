#pragma once
#include "Object.h"

class Array:virtual public Object
{
private:
    Object **array;
    size_t lenght;
public:
    Array():array(nullptr), lenght(0){}
    Array(const Array& ) = delete;
    Array& operator=(const Array& ) = delete;

    Array(Array&& other):array(other.array), lenght(other.lenght){
        other.array = nullptr;
        other.lenght = 0;
    }

    Array& operator=(Array&& other){
        if(this!=&other){
            for(int i=0;i<lenght;i++){
                delete array[i];
            }
            delete[] array;
            array = other.array;
            lenght = other.lenght;
            other.array = nullptr;
            other.lenght = 0;
        }
        return *this;
    }

    void apend(Object *object){
        if(!array){
            array = new Object*[1];
        }else{
            Object **newArray = new Object*[lenght+1];
            for(int i=0;i<lenght+1;i++){
                newArray[i] = array[i];
            }
            delete[] array;
            array = newArray;
        }
        array[lenght++] = object;
    }

    size_t getLenght()const{
        return lenght;
    }

    Object* operator[](size_t index){
        return array[index];
    }
    const Object* operator[](size_t index)const{
        return array[index];
    }

    ~Array() {
        for(int i=0;i<lenght;i++){
            delete array[i];
        }
        delete[] array;
    }
};