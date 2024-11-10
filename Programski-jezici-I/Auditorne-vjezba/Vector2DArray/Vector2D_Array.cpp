#include <iostream>
#include "Vector2D_Array.h"
#include "climits";

using namespace std;

Vector2D Vector2D_Array::ERROR(INT32_MIN, INT32_MIN);

Vector2D_Array::Vector2D_Array(int initialCapacity) :capacity(initialCapacity) {
	if (capacity > 0) {
		array = new Vector2D[capacity];
	}
	else {
		array = nullptr;
	}
	size = -1;
}
Vector2D_Array::~Vector2D_Array() {
	delete[] array;
	array = nullptr;
}

void Vector2D_Array::add_element(const Vector2D& vector) {
	if (size >= capacity-1) {
		resize();
	}
	array[++size] = Vector2D(vector);
}
bool Vector2D_Array::delete_element(int index) {
	if (size < index) {
		return false;
	}
	for (int i = index; i < size; i++) {
		array[i] = array[i + 1];
	}
	size--;
	return true;
}

Vector2D& Vector2D_Array::at(int index, bool& success) {
	if (size<0 || index > size) {
		success = false;
		return ERROR;
	}
	else {
		success = true;
		return array[index];
	}
}
const Vector2D& Vector2D_Array::at(int index, bool& success)const {
	if (index > size) {
		success = false;
		return ERROR;
	}
	else {
		success = true;
		return array[index];
	}
}

void Vector2D_Array::forEach_side(void(*f)(Vector2D&)) {
	for (int i = 0; i <= size; i++) {
		f(array[i]);
	}
}
void Vector2D_Array::forEach(void(*f)(const Vector2D&))const {
	for (int i = 0; i <= size; i++) {
		f(array[i]);
	}
}

void Vector2D_Array::resize() {
	Vector2D* newArray = new Vector2D[capacity?capacity*2:1];
	for (int i = 0; i <= size; i++)
	{
		newArray[i] = array[i];
	}
	delete[] array;
	array = newArray;
	capacity ? (capacity *=2) : (capacity = 1);
}

void Vector2D_Array::downsize() {
	Vector2D* newArray = new Vector2D[capacity / 2];
	for (int i = 0; i <= size; i++)
	{
		newArray[i] = array[i];
	}
	delete[] array;
	array = newArray;
	capacity /= 2;
}

int Vector2D_Array::sizeOf()const 
{ 
	return size; 
}