#include "Vector2DStack.h"

void Vector2DStack::push(const Vector2D &v)
{
    stack.addElement(v);
}

Vector2D Vector2DStack::pop()
{
    return stack.deleteElement(stack.getSize()-1);
}

int Vector2DStack::getSize() const
{
    return stack.getSize()-1;
}
