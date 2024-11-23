#include "Vector2DArray.h"
#include <climits>

Vector2D Vector2DArray::EMPTY(INT_MIN,INT_MIN);

Vector2DArray::Vector2DArray(int capacity):capacity(capacity),size(0),data(new Vector2D[capacity]){}

int Vector2DArray::getSize() const
{
    return size;
}

bool Vector2DArray::isFull() const
{
    return (size+1)==capacity;
}

bool Vector2DArray::isEmpty() const
{
    return size==0;
}

void Vector2DArray::resize(int newCapacity)
{
    Vector2D *newData = new Vector2D[newCapacity];
    capacity = newCapacity;
    for(int i=0;i<size;i++)
        newData[i] = data[i];
    delete[] data;
    data = newData;
    newData = nullptr;
}

void Vector2DArray::addElement(const Vector2D &v)
{
    if(isFull())
        resize(capacity*2);
    data[size++] = v;
}

Vector2D Vector2DArray::deleteElement(int index)
{
    if(isEmpty()){
        return EMPTY; 
    }
        
    Vector2D toReturn = at(index);
    
    if(!toReturn.equals(EMPTY)){
        for(int i=index;i+1<size;i++)
            data[i] = data[i+1];
        size--;
    }
    return toReturn;
}

Vector2D &Vector2DArray::at(int index) const
{
    if(index < size && index>=0)
        return data[index];
    return EMPTY;
}

void Vector2DArray::forEach(void (*f)(Vector2D& v))
{
    for(int i=0;i<size;i++)
        (*f)(data[i]);
}

void Vector2DArray::forEach_(void (*f)(const Vector2D &v)) const
{
    for(int i=0;i<size;i++)
        (*f)(data[i]);
}
