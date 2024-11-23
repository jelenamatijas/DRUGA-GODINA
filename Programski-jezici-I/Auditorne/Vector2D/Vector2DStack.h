#include "Vector2DArray.h"

class Vector2DStack{
    Vector2DArray stack;
public:
    void push(const Vector2D&);
    Vector2D pop();
    int getSize()const;
};