#include <iostream>

#include "Point.h"
#include "Stack.h"
#include <cstdio>

using namespace std;

int main() {

	Stack s(3);

	Point p1(3, 4);
	Point p2(1, 2);
	s.push(p1);
	s.push(p2);

	auto topPoint = s.top();

	printf("%d %d\n", topPoint.getX(), topPoint.getY());

	topPoint = s.pop();
	printf("%d %d\n", topPoint.getX(), topPoint.getY());

	topPoint = s.pop();
	printf("%d %d\n", topPoint.getX(), topPoint.getY());

	return 0;
}
