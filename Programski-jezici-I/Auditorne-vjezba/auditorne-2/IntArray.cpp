#include <stdlib.h>
#include "IntArray.h"

IntArray::IntArray():length(0), capacity(1), array(new int[capacity]){}

IntArray::~IntArray(){
    delete[] this->array;
}

void IntArray::append(int value)
{
    if (this->length == this->capacity)
    {
        this->capacity *= 2;
        int *newArray = new int[capacity];
        for(int i=0;i<length;i++){
            newArray[i] =array[i];
        }
        delete[] array;
        array = newArray;
    }

    this->array[this->length] = value;
    this->length++;
}

void IntArray::dispose()
{
    delete[] array;
}

int IntArray::getLength() const
{
    return this->length;
}

int IntArray::at(int index) const
{
    return this->array[index];
}