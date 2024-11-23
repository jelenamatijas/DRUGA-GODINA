#include "Vector2D.h"

class Vector2DArray{
    int capacity;
    int size;
    Vector2D *data;
public:
    static Vector2D EMPTY;
    Vector2DArray(int capacity=10);
    void addElement(const Vector2D&);
    Vector2D deleteElement(int index);
    Vector2D& at(int index)const;
    void forEach(void (*f)(Vector2D& v));
    void forEach_(void (*f)(const Vector2D& v))const;

    int getSize()const;
    bool isFull()const;
    bool isEmpty()const;
private:
    void resize(int newCapacity);
};