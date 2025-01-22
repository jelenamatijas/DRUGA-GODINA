#include "Array.h"
#include <climits>

Vector2D Array::ERROR = Vector2D(INT_MIN, INT_MIN);

Array::Array(int initialCapacity):capacity(initialCapacity),size(0),data(new Vector2D[capacity]){}

Array::Array(const Array &other):capacity(other.capacity),size(other.capacity),data(new Vector2D[capacity])
{
    for(int i=0;i<size;i++)
        data[i] = other.data[i];
}

Array::Array(Array &&other)
{
    size = other.size;
    capacity = other.capacity;
    data = other.data;
    other.data = nullptr;
}

Array::~Array()
{
    delete[] data;
}

/**Array &Array::operator=(const Array &other)
{
    if(this!=&other){
        delete[] data;
        capacity = other.capacity;
        size = other.size;
        data = new Vector2D[capacity];
        for(int i=0;i<size;i++)
            data[i] = other.data[i];
    }
    return *this;
}

Array &Array::operator=(Array &&other)
{
    if(this!=&other){
        delete[] data;
        capacity = other.capacity;
        size = other.size;
        data = other.data;
        other.data = nullptr;
    }
    return *this;
}*/

void Array::add(const Vector2D &v)
{
    if(size == capacity)
        resize(2*capacity);
    data[size++] = v;
}


Vector2D& Array::at(int index)
{
    if(index<0 || index>size-1)
        return ERROR;
    return data[index];
}

Vector2D& Array::at(const int index) const
{
    if(index<0 || index>size-1)
        return ERROR;
    return data[index];
}

Array Array::transform(Vector2D (*f)(Vector2D))const
{
    Array newArray(capacity);
    newArray.size = size;
    for(int i=0;i<size;i++)
        newArray.data[i] = f(data[i]);
    return newArray;
}

int Array::getSize() const
{
    return size;
}

void Array::resize(int newCapacity)
{
    capacity = newCapacity;
    Vector2D *newData = new Vector2D[capacity];
    for(int i=0;i<size;i++)
        newData[i] = data[i];
    delete[] data;
    data = newData;
    newData = nullptr;
}
