#pragma once

#include "Point.h"

class Stack {
private:
  Point *data;
  int capacity;
  int topIndex;
public:
  static Point EMPTY;

  Stack(int initialCapacity = 10);
  ~Stack();

  void push(Point &value);
  Point pop();
  Point &top();
  bool isEmpty();
  bool isFull();
  int size();

private:
  void resize(int newCapacity);
};
