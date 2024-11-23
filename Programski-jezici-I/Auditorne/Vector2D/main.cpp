#include <iostream>
#include "Vector2DStack.h"
using namespace std;

void printVector(const Vector2D& v){
    cout<<"X: "<<v.getX()<<" Y: "<<v.getY()<<endl;
}

void increaseCoordinates(Vector2D& v){
    v.setX(v.getX()+1);
    v.setY(v.getY()+1);
}

int main(){

    Vector2D v1(1,2), v2(2,4);
    v1.print();
    v2.print();
    Vector2D v3 = v1.add_(v2);
    v3.print();
    Vector2D v4 = v1.subtract_(v2);
    v4.print();
    v2.add(v2);
    v2.print();
    v2.subtract(v2);
    v2.print();

    cout<<"****************************************************"<<endl;

    //********************************************************************** */

    Vector2DArray array(2);
    array.addElement(v1);
    array.addElement(v2);
    array.addElement(v3);
    array.addElement(v4);

    array.forEach_(printVector);
    cout<<"--------------------"<<endl;
    array.forEach(increaseCoordinates);
    array.forEach_(printVector);
    cout<<"--------------------"<<endl;
    Vector2D v5 = array.deleteElement(1);
    v5.print();
    cout<<"--------------------"<<endl;
    array.forEach_(printVector);
    cout<<"--------------------"<<endl;
    v5 = array.deleteElement(4);
    v5.print();
    cout<<"--------------------"<<endl;
    array.forEach_(printVector);
    cout<<"--------------------"<<endl;
    v5 = array.at(0);
    v5.print();
    cout<<"--------------------"<<endl;

    cout<<"****************************************************"<<endl;

    //********************************************************************** */

    Vector2DStack stack;
    stack.push(v1);
    stack.push(v2);
    stack.push(v3);
    stack.push(v4);

    while(stack.getSize()>=0)
        stack.pop().print();

}