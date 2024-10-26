#include <stdlib.h>
#include "IntArray.h"

IntArray::IntArray()
{
    this->length = 0;
    this->capacity = 1;
    this->array = (int *)malloc(sizeof(int) * this->capacity);
}

void IntArray::append(int value)
{
    if (this->length == this->capacity)
    {
        this->capacity *= 2;
        this->array = (int *)realloc(this->array, sizeof(int) * this->capacity);
    }

    this->array[this->length] = value;
    this->length++;
}

void IntArray::dispose()
{
    free(this->array);
}

int IntArray::getLength() const
{
    return this->length;
}

int IntArray::at(int index) const
{
    return this->array[index];
}