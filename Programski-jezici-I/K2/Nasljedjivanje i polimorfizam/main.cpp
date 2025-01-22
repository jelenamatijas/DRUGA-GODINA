#include <iostream>
#include <typeinfo>
#include "Shape2D.h"
#include "Circle.h"
#include "Square.h"
#include "Rectangle.h"

int main()
{
	const size_t numShapes = 10;
	Shape2D* shapes2D[numShapes];

	// Nemoguće imati kolekciju 
	// Shape2D* shapes2D = new Shape2D [numShapes]; // pogrešno

	// Punjenje niza oblicima različitih tipova.
	for (int i = 0; i < numShapes; i++)
	{
		switch (i % 3)
		{
		case 0:
			shapes2D[i] = new Circle((double)i + 1.0);
			break;
		case 1:
			shapes2D[i] = new Square((double)i + 1.0);
			break;
		case 2:
			shapes2D[i] = new Rectangle((double)i + 1.0, (2 * ((double)i) + 1.0));
			break;
		}
	}

	// Ispis podataka o oblicima
	for (int i = 0; i < numShapes; i++)
	{
		auto& shape = *(shapes2D[i]);
		std::cout << "Shape " << i+1 << ": " << shape.type()
			<< " (area=" << shape.calculateArea()
			<< ", perimeter=" << shape.calculatePerimeter()
			<< ")" << std::endl;
	}

	for (int i = 0; i < numShapes; ++i)
	{
		// Brisanje sa virtuelnim destruktorom u odnosu na
		// brisanje sa nevirtuelnim destruktorom?
		delete shapes2D[i];
	}
}