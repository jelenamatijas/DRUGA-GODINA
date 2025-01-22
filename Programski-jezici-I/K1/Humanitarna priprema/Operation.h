#pragma once
#include "Vector2D.h"

class Operation
{
private:
    const Vector2D& lv;
    const Vector2D& rv;
    Vector2D(*operation)(const Vector2D& lv, const Vector2D& rv);

public:
    Operation(const Vector2D& lv, const Vector2D& rv,
             Vector2D(*operation)(const Vector2D& lv, const Vector2D& rv)) 
             : lv(lv),
               rv(rv),
               operation(operation) {}

    Vector2D execute() const
    {
        return this->operation(lv, rv);
    }
};