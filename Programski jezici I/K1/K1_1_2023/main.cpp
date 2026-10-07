#include <iostream>
#include "Array.h"

using namespace std;

void print(const Vector2D& v){
    cout<<"X: "<<v.getX()<<"Y: "<<v.getY()<<endl;
}

int main() {

    Vector2D v1(1,2);
    Vector2D v2(2,3);
    Vector2D v3 = v1+v2;
    Vector2D v4 = v2;

    print(v1);
    print(v2);
    print(v3);
    print(v4);

    v1+=Vector2D(10,10);
    print(v1);

    Vector2D v5 = (v1+=Vector2D(1,2));
    Array a1;
    a1.add(v1);
    a1.add(v2);
    a1.add(v3);
    a1.add(v4);

    Array a2(1);
    a2.add(v5);

    cout<<"A1:"<<endl;
    for(int i=0;i<a1.getSize();i++){
        print(a1.at(i));
    }
    
    cout<<"A2:"<<endl;
    for(int i=0;i<a2.getSize();i++){
        print(a2.at(i));
    }

    Array a3(a1);
    Array a4 = a2;

    cout<<"A3:"<<endl;
    for(int i=0;i<a3.getSize();i++){
        print(a3.at(i));
    }

    cout<<"A4:"<<endl;
    for(int i=0;i<a4.getSize();i++){
        print(a4.at(i));
    }

    Array a5 = Array(a2);
    cout<<"A5:"<<endl;
    for(int i=0;i<a5.getSize();i++){
        print(a5.at(i));
    }

    Array a6(a5.transform([](Vector2D v) {return Vector2D(v.getX()*2,v.getY());} ));
    cout<<"A6:"<<endl;
    for(int i=0;i<a6.getSize();i++){
        print(a6.at(i));
    }

    return 0;
}