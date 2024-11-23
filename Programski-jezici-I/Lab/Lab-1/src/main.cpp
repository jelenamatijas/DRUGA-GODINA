#include <iostream>
//#include "Point.h"
#include "Stack.h"
using namespace std;

int main() {

  Stack s;

  Point p1(3, 4);
  Point p2(1, 2);
  s.push(p1);
  s.push(p2);

  auto topPoint = s.top();

  cout<<topPoint.getX()<<" "<<topPoint.getY()<<endl;

  return 0;
}
