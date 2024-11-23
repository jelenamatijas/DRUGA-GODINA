#include "Stack.h"
#include <climits>

Point Stack::EMPTY(Point(INT_MIN, INT_MIN));

Stack::Stack(int initialCapacity):capacity(initialCapacity), topIndex(-1){
    if(initialCapacity>0) 
        data = new Point[initialCapacity];
    else{
        data = nullptr;
        capacity = 0;
    }
}
Stack::~Stack(){
    delete[] data;
    capacity = 0;
    topIndex = -1;
}

void Stack::push(Point &value){
    if(isFull())
        resize(capacity*2);
    data[++topIndex] = value;
}

bool Stack::isFull(){
    return capacity == (topIndex+1);
}

Point Stack::pop(){
    if(!isEmpty())
        return data[topIndex--];
    return EMPTY;
}

Point& Stack::top(){
    if(!isEmpty())
        return data[topIndex];
    return EMPTY;
}
bool Stack::isEmpty(){
    return topIndex == -1;
}
int Stack::size(){
    return topIndex+1;
}

void Stack::resize(int newCapacity){
    Point *newData = new Point[newCapacity];
    capacity = newCapacity;
    for(int i=0;i<size();i++)
        newData[i] = data[i];
    delete[] data;
    data = newData;
    newData = nullptr;
}