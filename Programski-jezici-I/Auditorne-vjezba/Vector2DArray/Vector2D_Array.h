#pragma once
#include "Vector2D.h"

class Vector2D_Array {
	int capacity;
	int size;
	Vector2D* array;
public:
	Vector2D static ERROR;
	Vector2D_Array(int capacity=0);
	~Vector2D_Array();

	void add_element(const Vector2D& vector);
	bool delete_element(int index);

	Vector2D& at(int index, bool& success);
	const Vector2D& at(int index, bool& success)const;

	void forEach_side(void(*f)(Vector2D&));
	void forEach(void(*f)(const Vector2D&))const;

	int sizeOf()const;
private:
	void resize();
	void downsize();
};