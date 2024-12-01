#include <iostream>
#include "BoundedStack.h"

int main()
{
    Vector2D vec1(1, 2);
    Vector2D vec2(3, 4);
    Vector2D vec3(5, 6);
    Vector2D vec4(7, 1);
    Vector2D vec5(3, 6);

    /*
    std::cout << "Vector 1: " << std::endl;
    std::cout << vec1 << std::endl;
    std::cout << "Vector 2: " << std::endl;
    std::cout << vec2 << std::endl;
    std::cout << "Vector 3: " << std::endl;
    std::cout << vec3 << std::endl;
    */

    Operation add(vec1, vec2, [](const Vector2D& lv, const Vector2D& rv)
    {
        return lv + rv;
    });
    
    Operation subtract(vec3, vec2, [](const Vector2D& lv, const Vector2D& rv)
    {
        return lv - rv;
    });

    Operation max(vec1, vec2, [](const Vector2D& lv, const Vector2D& rv)
    {
        return lv.max(rv);
    });

    /*
    std::cout << "Operation1" << std::endl;
    std::cout << add.execute() << std::endl;
    
    std::cout << "Operation2" << std::endl;
    std::cout << subtract.execute() << std::endl;
    
    std::cout << "Operation3" << std::endl;
    std::cout << max.execute() << std::endl;
    */
    BoundedStack bStack(4);
    bStack.push(vec1);
    bStack.push(vec2);
    bStack.push(vec3);
    bStack.push(vec4);
    bStack.push(vec5);

    Vector2D vec;
    bStack.pop(vec);
    std::cout << vec << std::endl;
    bStack.push(vec5);
}