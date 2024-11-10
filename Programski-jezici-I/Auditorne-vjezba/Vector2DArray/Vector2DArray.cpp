#include <iostream>
#include "Vector2D.h"
#include "Vector2D_Array.h"
#include "Vector2DStack.h"

using namespace std;

void printVector(const Vector2D& vector) {
    vector.printVector2D();
}

void increaseCoordinates(Vector2D& vector) {
    Vector2D v(1, 1);
    vector.add_side(v);
}

int main()
{
    //Vector2D checked
    Vector2D vector1(1.3, 2.2);
    Vector2D vector2(1.0,1.0);
    Vector2D sum = vector1.add(vector2);
    sum.printVector2D();
    vector1.add_side(sum);
    vector1.printVector2D();
    Vector2D subtraction = vector1.subtruct(sum);
    subtraction.printVector2D();
    vector2.subtruct_side(vector2);
    vector2.printVector2D();

    //Vectors checkup
    cout << "--------------------------------" << endl;
    vector1.printVector2D();
    vector2.printVector2D();
    sum.printVector2D();
    subtraction.printVector2D();
    cout << "--------------------------------" << endl;

    //TODO Vector2D_Array check
    Vector2D_Array vectorArray(2);
    vectorArray.add_element(vector1);
    vectorArray.add_element(vector2);
    vectorArray.add_element(sum);
    vectorArray.add_element(subtraction);

    vectorArray.forEach(printVector);
    vectorArray.forEach_side(increaseCoordinates);
    cout << vectorArray.delete_element(1)<<endl;
    cout << vectorArray.delete_element(0) << endl;
    cout << vectorArray.delete_element(0) << endl;

    bool success = false;
    Vector2D vectorAt = vectorArray.at(0, success);

    if (success)
        vectorAt.printVector2D();
    else
        cout << "Vector not found." << endl;

    vectorAt = vectorArray.at(5, success);

    if (success)
        vectorAt.printVector2D();
    else {
        cout << "Vector not found." << endl;
        vectorAt.printVector2D();
    }

    //TODO Vector2DStack check

    Vector2DStack stack;
    stack.push(Vector2D(5.0, 2.5));
    stack.push(Vector2D(7.5, 3.5));
    stack.push(Vector2D(6.4, 2.8));
    stack.push(vector1);

    
    Vector2D res;

    cout << "Popping from stack..." << endl;
    while (stack.pop(res))
    {
        res.printVector2D();
    }

    return 0;
}

