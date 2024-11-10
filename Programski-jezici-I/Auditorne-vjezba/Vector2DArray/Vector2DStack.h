#pragma once

#include "Vector2D.h";
#include "Vector2D_Array.h";

class Vector2DStack {
	Vector2D_Array stack;
public:
	void push(const Vector2D& vector);
	bool pop(Vector2D& vector);
};