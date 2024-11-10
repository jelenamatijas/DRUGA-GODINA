#include "Stack.h"

Point Stack::EMPTY = Point(INT_MIN, INT_MIN);

Stack::Stack(int initialCapacity) : capacity(initialCapacity),
                                    topIndex(-1),
                                    data(new Point[initialCapacity]) {}

Stack::~Stack() {
    delete[] this->data;
}

bool Stack::isEmpty() {
    return topIndex == -1 ? true : false;
}
int Stack::size() {
    return topIndex + 1;
}

void Stack::push(Point& value) {
    if (size() == capacity) {
        resize(capacity * 2);
    }
    data[++topIndex] = value;

}
Point& Stack::pop() {
    if (isEmpty()) {
        return EMPTY;
    }
    return data[topIndex--];
}

Point& Stack::top() {
    if (isEmpty()) {
        return EMPTY;
    }
    return data[topIndex];
}

void Stack::resize(int newCapacity) {
    Point* dataNew = new Point[newCapacity];
    for (int i = 0; i < size(); i++) 
        dataNew[i] = Point(data[i].getX(), data[i].getY());
    delete[] this->data;
    this->data = dataNew;
    this->capacity = newCapacity;
}