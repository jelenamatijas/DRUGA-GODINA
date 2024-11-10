#include <iostream>
#include "Vector2DStack.h"

using namespace std;

void Vector2DStack::push(const Vector2D& vector) {
	stack.add_element(vector);
}
bool Vector2DStack::pop(Vector2D& vector) {
	bool success = true;
	Vector2D out = stack.at(stack.sizeOf(), success);
	if (success) {
		vector = out;
		stack.delete_element(stack.sizeOf());
	}
	return success;
}