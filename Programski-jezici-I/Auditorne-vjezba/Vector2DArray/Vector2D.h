#pragma once

class Vector2D {
	double x, y;
public:
	Vector2D(double x=0.0, double y=0.0);

	bool equals(const Vector2D& other)const;

	Vector2D add(const Vector2D& other)const;
	void add_side(const Vector2D& other);

	Vector2D subtruct(const Vector2D& other)const;
	void subtruct_side(const Vector2D& other);

	void printVector2D()const;
};