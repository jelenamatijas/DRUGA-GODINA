#include "Vector2D.h"

class Array{
    int capacity;
    int size;
    Vector2D *data;
public:
    static Vector2D ERROR;
    Array(int initialCapacity=5);
    Array(const Array&);
    Array(Array&&);
    ~Array();
    Array& operator=(const Array&)=delete;
    Array& operator=(Array&&)=delete;

    void add(const Vector2D&);
    Vector2D& at(int);
    Vector2D& at(const int)const;
    Array transform(Vector2D(*f)(Vector2D))const;
    int getSize()const;
private:
    void resize(int newCapacity);
};