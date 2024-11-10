#include <iostream>
#include "Vector2D.h"

using namespace std;

Vector2D::Vector2D(double x, double y) :x(x), y(y) {}

bool Vector2D::equals(const Vector2D& other)const {
	return this->x == other.x && this->y == other.y ? true : false;
}

Vector2D Vector2D::add(const Vector2D& other)const {
	Vector2D newVector(this->x + other.x, this->y + other.y);
	return newVector;
}

void Vector2D::add_side(const Vector2D& other) {
	this->x += other.x;
	this->y += other.y;
}

Vector2D Vector2D::subtruct(const Vector2D& other)const {
	Vector2D newVector(this->x - other.x, this->y - other.y);
	return newVector;
}
void Vector2D::subtruct_side(const Vector2D& other) {
	this->x -= other.x;
	this->y -= other.y;
}

void Vector2D::printVector2D()const {
	cout << "(" << this->x << "," << this->y << ")" << endl;
}